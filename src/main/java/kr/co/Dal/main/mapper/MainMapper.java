package kr.co.Dal.main.mapper;

import kr.co.Dal.main.model.MainVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface MainMapper {

    /** 위치+주류 검색에 따른 검색 - 지도 각 모서리 좌표를 통해 데이터 검색 (Point type 사용) */
    List<MainVO> selectStoreList(@Param("swLat") String swLat, @Param("swLng") String swLng, @Param("neLat") String neLat, @Param("neLng") String neLng, @Param("prod_tit") String prod_tit);

    /** 셀렉트: 지역 가져오기 (location_tb) */
    List<MainVO> selectProvinceList(MainVO mainVO);
    List<MainVO> selectCityList(String province_id);
    List<MainVO> selectTownList(@Param("province_id") String province_id, @Param("city_id") String city_id);

}
