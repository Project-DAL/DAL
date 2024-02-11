package kr.co.Dal.admin.service;

/*
File Name      : AdminAjaxService.js
Program Name   : 비동기방식 관리자 서비스(비즈니스 로직)
Draft Author   : 김진형
Draft Date     : 2024.01.17

Revision History
Ver.  Date          Revised By   Description
————————————————————————————————————————————————————————————
0.1   2024.01.17    김진형       최초개발
———————————————————————————————————————————————————————————>
*/

import kr.co.Dal.admin.mapper.AdminAjaxMapper;
import kr.co.Dal.admin.model.AdminStoreVO;
import kr.co.Dal.admin.model.ProdImgVO;
import lombok.RequiredArgsConstructor;
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

@Service
@RequiredArgsConstructor
public class AdminAjaxService {

    private final AdminAjaxMapper adminAjaxMapper;
    public List<AdminStoreVO> findStoreList() {
        return adminAjaxMapper.selectStoreList();
    }

    public List<AdminStoreVO> findStoreCategoryList(String prodType) {
        return adminAjaxMapper.selectCategoryList(prodType);
    }

    /**
     * 상품 정보 등록 및 상품 ID 반환
     */
    @Transactional
    public int insertStore(AdminStoreVO adminStoreVO) {
        adminAjaxMapper.insertStore(adminStoreVO);
        return adminStoreVO.getProdId(); // MyBatis의 selectKey를 사용하여 설정된 ID 반환
    }

    /**
     * 상품 이미지 파일 업로드 및 정보 저장
     * @param prodId 상품 ID
     * @param files 업로드할 파일 목록
     */
    @Transactional
    public void uploadProductFiles(int prodId, List<MultipartFile> files) {
        files.forEach(file -> {
            // 오늘 날짜를 기준으로 폴더 경로 생성
            String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            Path rootLocation = Paths.get("D:/upload", today);

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
                ProdImgVO prodImgVO = new ProdImgVO();
                prodImgVO.setProdId(prodId);
                prodImgVO.setImgPath(rootLocation.resolve(filename).toString());
                prodImgVO.setOriginalName(originalFilename);
                prodImgVO.setSaveName(filename);

                adminAjaxMapper.insertProdImg(prodImgVO);
            } catch (IOException e) {
                throw new RuntimeException("Failed to store file " + filename, e);
            }
        });
    }
}
