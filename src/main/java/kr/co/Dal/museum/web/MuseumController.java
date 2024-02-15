package kr.co.Dal.museum.web;

import kr.co.Dal.museum.model.MuseumVO;
import kr.co.Dal.museum.service.MuseumService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

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
    public ResponseEntity<List<MuseumVO>> selectLiq(MuseumVO museumVO,
                                                    @RequestParam(name = "liqId") int liqId){

        log.warn("selectLiq Controller");
        FileInputStream fis = null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        museumVO.setLiqId(liqId);
        List<MuseumVO> liq = museumService.selectLiq(museumVO);

        for (MuseumVO vo : liq) {
            log.warn("--------------------------------");
            log.warn("liqId: " + vo.getLiqId());
            log.warn("liqNm: " + vo.getLiqNm());
            log.warn("liqNm: " + vo.getImgId());
            log.warn("uploadDate: " + vo.getUploadDate());
            log.warn("uploadDate format: " + sdf.format(vo.getUploadDate()));
            log.warn("saveName: " + vo.getSaveName());

            String filePath = uploadPath + "/" + sdf.format(vo.getUploadDate()) + "/" + vo.getSaveName();
            Path file = Paths.get(filePath);
            log.warn("File path: " + file.toString());

            try {
                String contentType = Files.probeContentType(file);  // MIME content type 결정 (ex: png)

                HttpHeaders headers = new HttpHeaders();
                headers.add("Content-Type", contentType);

                fis = new FileInputStream(filePath);

            } catch (Exception e) {
                e.printStackTrace();
            }

            //Path rootLocation = Paths.get(uploadPath + "/" + sdf.format(vo.getUploadDate()), vo.getSaveName());

        }

        return ResponseEntity.ok().body(liq);
    }

}
