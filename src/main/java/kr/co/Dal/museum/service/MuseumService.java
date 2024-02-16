package kr.co.Dal.museum.service;

import kr.co.Dal.museum.mapper.MuseumMapper;
import kr.co.Dal.museum.model.MuseumVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/*
File Name      : MuseumService.java
Program Name   : 주류 박물관 Service
Draft Author   : 이원정
Draft Date     : 2024.01.22

Revision History
Ver.  Date          Revised By   Description
————————————————————————————————————————————————————————————
0.1   2024.01.22    이원정       최초개발
————————————————————————————————————————————————————————————
*/

@Slf4j
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
