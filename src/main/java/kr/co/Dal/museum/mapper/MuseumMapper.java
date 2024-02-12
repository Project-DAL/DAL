package kr.co.Dal.museum.mapper;

import kr.co.Dal.cmmn.model.CmmnVO;
import kr.co.Dal.museum.model.MuseumVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/*
File Name      : MuseumMapper.java
Program Name   : 주류 박물관 Mapper
Draft Author   : 이원정
Draft Date     : 2024.01.22

Revision History
Ver.  Date          Revised By   Description
————————————————————————————————————————————————————————————
0.1   2024.01.22    이원정       최초개발
————————————————————————————————————————————————————————————
*/

@Mapper
public interface MuseumMapper {

    /** 분류 선택에 따른 술 목록 가져오기 */
    List<MuseumVO> selectLiqList(MuseumVO museumVO);

    /** 술 정보 가져오기 */
    List<MuseumVO> selectLiq(MuseumVO museumVO);

}
