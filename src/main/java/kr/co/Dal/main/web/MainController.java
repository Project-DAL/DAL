package kr.co.Dal.main.web;

import kr.co.Dal.main.model.MainVO;
import kr.co.Dal.main.service.MainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /* 위치+주류 검색에 따른 검색 */
    @GetMapping("/rest/storeList")
    @ResponseBody
    public ResponseEntity<List<MainVO>> selectStoreList(@Param("swLat") String swLat, @Param("swLng") String swLng,
                                                        @Param("neLat") String neLat, @Param("neLng") String neLng,
                                                        @Param("prodTit") String prodTit) {

        log.warn("selectStoreList controller");

        log.warn("param : " + swLat + "," + swLng + "," + neLat + "," + neLng);
        log.warn("prod_tit: " + prodTit);


        List<MainVO> storeList = mainService.selectStoreList(swLat, swLng, neLat, neLng, prodTit);

        return ResponseEntity.ok().body(storeList);
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
