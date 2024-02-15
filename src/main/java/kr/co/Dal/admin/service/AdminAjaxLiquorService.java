package kr.co.Dal.admin.service;

import kr.co.Dal.admin.mapper.AdminAjaxLiquorMapper;
import kr.co.Dal.admin.mapper.AdminAjaxMapper;
import kr.co.Dal.admin.model.AdminStoreVO;
import kr.co.Dal.admin.model.LiqImgVO;
import kr.co.Dal.admin.model.LiquorVO;
import kr.co.Dal.admin.model.ProdImgVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/*
File Name      : AdminAjaxLiquorService.js
Program Name   : 비동기방식 관리자 주류 서비스(비즈니스 로직)
Draft Author   : 김진형
Draft Date     : 2024.02.15

Revision History
Ver.  Date          Revised By   Description
————————————————————————————————————————————————————————————
0.1   2024.02.15    김진형       최초개발
———————————————————————————————————————————————————————————>
*/

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminAjaxLiquorService {

    private final AdminAjaxLiquorMapper adminAjaxLiquorMapper;

    public List<LiquorVO> findLiquorList() {
        return adminAjaxLiquorMapper.selectLiquorList();
    }

    public List<LiquorVO> findLiquorCategoryList(String liqType) {
        return adminAjaxLiquorMapper.selectLiquorCategoryList(liqType);
    }

    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;


    public int insertLiquor(LiquorVO liquorVO) {
        adminAjaxLiquorMapper.insertLiquor(liquorVO);
        return liquorVO.getLiqId();
    }

    /**
     * 상품 이미지 파일 업로드 및 정보 저장
     * @param liqId 상품 ID
     * @param files 업로드할 파일 목록
     */
    @Transactional
    public void uploadProductFiles(int liqId, List<MultipartFile> files) {
        log.warn("uploadPath1: " + uploadPath);

        files.forEach(file -> {

            log.warn("uploadPath2: " + uploadPath);

            // 오늘 날짜를 기준으로 폴더 경로 생성
            String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            Path rootLocation = Paths.get(uploadPath, today);

            // 해당 경로가 존재하지 않으면 생성
            try {
                Files.createDirectories(rootLocation);
            } catch (IOException e) {
                throw new RuntimeException("Could not create directory at " + rootLocation, e);
            }

            // 파일 저장 로직
            String originalFilename = file.getOriginalFilename();
            String filename = System.currentTimeMillis() + "_" + originalFilename;
            try (InputStream inputStream = file.getInputStream()) { // try-with-resources 구문 사용
                Files.copy(inputStream, rootLocation.resolve(filename));

                // 파일 메타데이터 DB 저장
                LiqImgVO liqImgVO = new LiqImgVO();
                liqImgVO.setLiqId(liqId);
                liqImgVO.setImgPath(rootLocation.resolve(filename).toString());
                liqImgVO.setOriginalName(originalFilename);
                liqImgVO.setSaveName(filename);

                adminAjaxLiquorMapper.insertLiquorImg(liqImgVO);
            } catch (IOException e) {
                throw new RuntimeException("Failed to store file " + filename, e);
            }
        });
    }
    @Transactional
    public void deleteLiquorAndRelatedFiles(int liqId) {
        // 상품과 연관된 이미지 정보 조회
        List<LiqImgVO> images = adminAjaxLiquorMapper.selectLiquorImagesByLiqId(liqId);
        // 파일 시스템에서 이미지 파일들 삭제
        for (LiqImgVO image : images) {
            Path imagePath = Paths.get(image.getImgPath());
            try {
                Files.deleteIfExists(imagePath);
            } catch (IOException e) {
                throw new RuntimeException("Failed to delete image file: " + imagePath, e);
            }
        }
        // 데이터베이스에서 상품 이미지 정보 삭제
        adminAjaxLiquorMapper.deleteLiquorImagesByProdId(liqId);
        // 데이터베이스에서 상품 정보 삭제
        adminAjaxLiquorMapper.deleteLiquor(liqId);
    }
}
