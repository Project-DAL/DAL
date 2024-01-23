package kr.co.Dal.main.service;

import kr.co.Dal.main.mapper.MainMapper;
import kr.co.Dal.main.model.MainVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MainService {

    private final MainMapper mainMapper;

    /* 셀렉트: 지역 가져오기 (location_tb) */
    public List<MainVO> selectProvinceList(MainVO mainVO) {
        return mainMapper.selectProvinceList(mainVO);
    }

}
