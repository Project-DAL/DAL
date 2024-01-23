package kr.co.Dal.main.mapper;

import kr.co.Dal.main.model.MainVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MainMapper {

    /** 방법1) 반경 300km이내 검색 (Point type 사용 X) */
    List<MainVO> selectStoreList(MainVO mainVO);

    /** 방법2) 지도 각 모서리 좌표를 통해 데이터 검색 (Point type 사용) */
    // 남서경도 남서위도 북동경도 북동위도
    List<MainVO> selectStoreList2(MainVO mainVO);

    /** 셀렉트: 지역 가져오기 (location_tb) */
    List<MainVO> selectProvinceList(MainVO mainVO);

}
