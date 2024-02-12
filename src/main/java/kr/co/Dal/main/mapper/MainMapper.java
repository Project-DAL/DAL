package kr.co.Dal.main.mapper;

import kr.co.Dal.main.model.MainVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/*
File Name      : MainMapper.java
Program Name   : 메인 검색 서비스 매퍼
Draft Author   : 이원정
Draft Date     : 2024.01.22

Revision History
Ver.  Date          Revised By   Description
————————————————————————————————————————————————————————————
0.1   2024.01.22    이원정       최초개발
———————————————————————————————————————————————————————————>
*/


@Mapper
public interface MainMapper {

    /** 위치+주류 검색에 따른 검색 - 지도 각 모서리 좌표를 통해 데이터 검색 (Point type 사용) */
    List<MainVO> selectStoreList(@Param("swLat") String swLat, @Param("swLng") String swLng, @Param("neLat") String neLat, @Param("neLng") String neLng, @Param("prodTit") String prodTit);

    /** 셀렉트: 지역 가져오기 (location_tb) */
    List<MainVO> selectProvinceList(MainVO mainVO);
    List<MainVO> selectCityList(String provinceId);
    List<MainVO> selectTownList(@Param("provinceId") String provinceId, @Param("cityId") String cityId);

}
