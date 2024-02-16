package kr.co.Dal.main.web;

import kr.co.Dal.main.model.MainVO;
import kr.co.Dal.main.service.MainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.jboss.jandex.Main;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

/*
File Name      : MainController.java
Program Name   : 메인 검색 서비스 Controller
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
public class MainController {

    private final MainService mainService;

    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;

    /* 위치+주류 검색에 따른 검색 */
    @GetMapping("/rest/storeList")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> selectStoreList(@Param("swLat") String swLat, @Param("swLng") String swLng,
                                                        @Param("neLat") String neLat, @Param("neLng") String neLng,
                                                        @Param("prodTit") String prodTit) {

        log.warn("param : " + swLat + "," + swLng + "," + neLat + "," + neLng);
        log.warn("prod_tit: " + prodTit);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        List<MainVO> storeList = mainService.selectStoreList(swLat, swLng, neLat, neLng, prodTit);

        Map<String, Object> resultMap = new HashMap<>();
        List<String> base64Images = new ArrayList<>(); // base64 이미지 리스트
        Resource resource = null;

        for (MainVO vo : storeList) {
            // 이미지 파일 경로
            String filePath = uploadPath + "/" + sdf.format(vo.getUploadDate()) + "/" + vo.getSaveName();
            Path path = Paths.get(filePath);

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

        resultMap.put("st", storeList);
        resultMap.put("base64Images", base64Images); // base64 이미지 리스트를 resultMap에 추가

        return ResponseEntity.ok().body(resultMap);
    }

    /* 셀렉트: 지역 가져오기 (location_tb)aa */
    @GetMapping("/rest/provinceList")
    @ResponseBody
    public ResponseEntity<List<MainVO>> selectProvinceList(MainVO mainVO) {
        List<MainVO> provinceList = mainService.selectProvinceList(mainVO);
        return ResponseEntity.ok().body(provinceList);
    }
    @GetMapping("/rest/cityList")
    @ResponseBody
    public ResponseEntity<List<MainVO>> selectCityList(@RequestParam("provinceId") String provinceId) {
        List<MainVO> cityList = mainService.selectCityList(provinceId);
        return ResponseEntity.ok().body(cityList);
    }
    @GetMapping("/rest/townList")
    @ResponseBody
    public ResponseEntity<List<MainVO>> selectTownList(@RequestParam("provinceId") String provinceId,
                                                       @RequestParam("cityId") String cityId) {
        List<MainVO> townList = mainService.selectTownList(provinceId, cityId);
        return ResponseEntity.ok().body(townList);
    }
}
