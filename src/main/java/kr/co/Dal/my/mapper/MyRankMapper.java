/*
    파일명        : MyRankMapper.java
    최초 작성자    : 박제형
    최초 작성날짜   : 2024.02.07
*/



package kr.co.Dal.my.mapper;

import kr.co.Dal.my.model.MyRankVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyRankMapper {


    /* 랭크 관련 */
    List<MyRankVO> viewRank(MyRankVO myRankvo);

}
