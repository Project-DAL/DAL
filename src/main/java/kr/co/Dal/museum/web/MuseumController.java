package kr.co.Dal.museum.web;

import kr.co.Dal.museum.model.MuseumVO;
import kr.co.Dal.museum.service.MuseumService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

/*
File Name      : MuseumController.java
Program Name   : 주류 박물관 Controller
Draft Author   : 이원정
Draft Date     : 2024.01.22

Revision History
Ver.  Date          Revised By   Description
————————————————————————————————————————————————————————————
0.1   2024.01.22    이원정       최초개발
————————————————————————————————————————————————————————————
*/

@Slf4j
@Controller
@RequiredArgsConstructor
public class MuseumController {

    private final MuseumService museumService;


    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;


    @GetMapping("/museum/MuseumMain")
    public String selectLiquorList() {
        return "/museum/MuseumMain";
    }

    /* 분류 선택에 따른 술 목록 가져오기 */
    @GetMapping("/museum/liqList")
    @ResponseBody
    public ResponseEntity<List<MuseumVO>> selectLiqList(MuseumVO museumVO,
                                                        @RequestParam(name = "liqType") String liqType) {

        log.warn("liqType: " + liqType);

        museumVO.setLiqType(liqType);

        List<MuseumVO> liqList = museumService.selectLiqList(museumVO);
        return ResponseEntity.ok().body(liqList);
    }

    /* 술 정보 가져오기 */
    @GetMapping("/museum/liq")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> selectLiq(MuseumVO museumVO,
                                                         @RequestParam(name = "liqId") int liqId) throws Exception {

        log.warn("selectLiq Controller");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        museumVO.setLiqId(liqId);
        List<MuseumVO> liq = museumService.selectLiq(museumVO);

        Map<String, Object> resultMap = new HashMap<>();
        List<String> filePaths = new ArrayList<>();     // 파일 경로 리스트
        List<String> base64Images = new ArrayList<>(); // base64 이미지 리스트
        Resource resource = null;
        //Path path = null;

        for (MuseumVO vo : liq) {
            log.warn("--------------------------------");
            log.warn("liqId: " + vo.getLiqId());
            log.warn("liqNm: " + vo.getLiqNm());
            log.warn("liqNm: " + vo.getImgId());
            log.warn("uploadDate: " + vo.getUploadDate());
            log.warn("uploadDate format: " + sdf.format(vo.getUploadDate()));
            log.warn("saveName: " + vo.getSaveName());

            // 이미지 파일 경로
            String filePath = uploadPath + "/" + sdf.format(vo.getUploadDate()) + "/" + vo.getSaveName();
            Path path = Paths.get(filePath);
            filePaths.add(filePath); // 파일 경로를 리스트에 추가

            try {
                // 이미지를 base64 문자열로 변환하여 리스트에 추가
                byte[] imageBytes = Files.readAllBytes(path);
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                base64Images.add(base64Image);
                resource = new InputStreamResource(Files.newInputStream(path));
            }catch (Exception e) {
                e.printStackTrace();
            }

        }

        resultMap.put("liq", liq);
        resultMap.put("filePaths", filePaths); // 파일 경로 리스트를 resultMap에 추가
        resultMap.put("base64Images", base64Images); // base64 이미지 리스트를 resultMap에 추가

        return ResponseEntity.ok().body(resultMap);
    }

    public byte[] getImage(String filePath) throws Exception {
        try(InputStream inputStream = new FileInputStream(filePath)) {
            byte[] byteArray = inputStream.readAllBytes();
            return byteArray;
        }catch (Exception e) {
            return null;
        }
    }


}
