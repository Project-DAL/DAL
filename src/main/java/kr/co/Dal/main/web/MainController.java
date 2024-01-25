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

@Slf4j
@Controller
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;

    /* 위치+주류 검색에 따른 검색 */
    @GetMapping("/rest/storeList")
    @ResponseBody
    public ResponseEntity<List<MainVO>> selectStoreList(@Param("swLat") String swLat, @Param("swLng") String swLng,
                                                        @Param("neLat") String neLat, @Param("neLng") String neLng) {

        log.warn("selectStoreList controller");

        log.warn("param : " + swLat + "," + swLng + "," + neLat + "," + neLng);

        List<MainVO> storeList = mainService.selectStoreList(swLat, swLng, neLat, neLng);

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
    public ResponseEntity<List<MainVO>> selectCityList(@RequestParam("province_id") String province_id) {
        List<MainVO> cityList = mainService.selectCityList(province_id);
        return ResponseEntity.ok().body(cityList);
    }
    @GetMapping("/rest/townList")
    @ResponseBody
    public ResponseEntity<List<MainVO>> selectTownList(@RequestParam("province_id") String province_id,
                                                       @RequestParam("city_id") String city_id) {
        List<MainVO> townList = mainService.selectTownList(province_id, city_id);
        return ResponseEntity.ok().body(townList);
    }
}
