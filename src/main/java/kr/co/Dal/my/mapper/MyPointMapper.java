/*
    파일명        : MyPointMapper.java
    최초 작성자    : 박제형
    최초 작성날짜   : 2024.01.22
*/

package kr.co.Dal.my.mapper;

import kr.co.Dal.my.model.MyPointVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyPointMapper {

    /* 포인트 리스트 조회 */
    List<MyPointVO> selectPointList(MyPointVO myPointVO);
}