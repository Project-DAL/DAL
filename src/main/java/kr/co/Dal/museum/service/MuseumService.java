package kr.co.Dal.museum.service;

import kr.co.Dal.museum.mapper.MuseumMapper;
import kr.co.Dal.museum.model.MuseumVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MuseumService {

    private final MuseumMapper museumMapper;

    /* 분류 선택에 따른 술 목록 가져오기 */
    public List<MuseumVO> selectLiqList(MuseumVO museumVO){
        return museumMapper.selectLiqList(museumVO);
    }

    /* 술 정보 가져오기 */
    public List<MuseumVO> selectLiq(MuseumVO museumVO) {
        return museumMapper.selectLiq(museumVO);
    }
}
