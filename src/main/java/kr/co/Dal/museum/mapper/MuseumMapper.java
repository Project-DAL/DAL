package kr.co.Dal.museum.mapper;

import kr.co.Dal.cmmn.model.CmmnVO;
import kr.co.Dal.museum.model.MuseumVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MuseumMapper {

    /** 분류 선택에 따른 술 목록 가져오기 */
    List<MuseumVO> selectLiqList(MuseumVO museumVO);

    /** 술 정보 가져오기 */
    List<MuseumVO> selectLiq(MuseumVO museumVO);

}
