package kr.co.Dal.main.service;

import kr.co.Dal.main.mapper.MainMapper;
import kr.co.Dal.main.model.MainVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class MainService {

    private final MainMapper mainMapper;

    /* 위치+주류 검색에 따른 검색 */
    public List<MainVO> selectStoreList(@RequestParam("swLat") String swLat, @RequestParam("swLng") String swLng,
                                        @RequestParam("neLat") String neLat, @RequestParam("neLng") String neLng,
                                        @RequestParam("prod_tit") String prod_tit) {
        log.warn("selectStoreList service");
        return mainMapper.selectStoreList(swLat, swLng, neLat, neLng, prod_tit);
    }



    /* 셀렉트: 지역 가져오기 (location_tb) */
    public List<MainVO> selectProvinceList(MainVO mainVO) {
        return mainMapper.selectProvinceList(mainVO);
    }
    public List<MainVO> selectCityList(String province_id) {
        return mainMapper.selectCityList(province_id);
    }
    public List<MainVO> selectTownList(@RequestParam("province_id") String province_id, @RequestParam("city_id") String city_id) {

        log.warn("province_id service: " + province_id);
        log.warn("city_id service: " + city_id);

        return mainMapper.selectTownList(province_id, city_id);
    }

}
