package kr.co.Dal.main.service;

import kr.co.Dal.main.mapper.MainMapper;
import kr.co.Dal.main.model.MainVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MainService {

    private final MainMapper mainMapper;

    /* 셀렉트: 지역 가져오기 (location_tb) */
    public List<MainVO> selectProvinceList(MainVO mainVO) {
        return mainMapper.selectProvinceList(mainVO);
    }
    public List<MainVO> selectCityList(String province_id) {
        return mainMapper.selectCityList(province_id);
    }
    public List<MainVO> selectTownList(String province_id, String city_id) {

        log.warn("province_id2: " + province_id);
        log.warn("city_id2: " + city_id);

        return mainMapper.selectTownList(province_id, city_id);
    }

}
