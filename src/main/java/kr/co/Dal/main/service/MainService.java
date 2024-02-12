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

/*
File Name      : MainService.java
Program Name   : 메인 검색 서비스 Service
Draft Author   : 이원정
Draft Date     : 2024.01.22

Revision History
Ver.  Date          Revised By   Description
————————————————————————————————————————————————————————————
0.1   2024.01.22    이원정       최초개발
———————————————————————————————————————————————————————————>
*/


@Slf4j
@Service
@RequiredArgsConstructor
public class MainService {

    private final MainMapper mainMapper;

    /* 위치+주류 검색에 따른 검색 */
    public List<MainVO> selectStoreList(@RequestParam("swLat") String swLat, @RequestParam("swLng") String swLng,
                                        @RequestParam("neLat") String neLat, @RequestParam("neLng") String neLng,
                                        @RequestParam("prodTit") String prodTit) {
        log.warn("selectStoreList service");
        return mainMapper.selectStoreList(swLat, swLng, neLat, neLng, prodTit);
    }



    /* 셀렉트: 지역 가져오기 (location_tb) */
    public List<MainVO> selectProvinceList(MainVO mainVO) {
        return mainMapper.selectProvinceList(mainVO);
    }
    public List<MainVO> selectCityList(String provinceId) {
        return mainMapper.selectCityList(provinceId);
    }
    public List<MainVO> selectTownList(@RequestParam("provinceId") String provinceId, @RequestParam("cityId") String cityId) {

        log.warn("provinceId service: " + provinceId);
        log.warn("cityId service: " + cityId);

        return mainMapper.selectTownList(provinceId, cityId);
    }

}
