/*
    파일명        : MyPointMapper.java
    최초 작성자    : 박제형
    최초 작성날짜   : 2024.01.22
*/

package kr.co.Dal.my.mapper;

import kr.co.Dal.my.model.MyPointVO;
import kr.co.Dal.util.SearchCondition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyPointMapper {

    /* 포인트 리스트 조회 */
    List<MyPointVO> selectPointList(SearchCondition sc);

    /*  내 게시글 목록 개수 */
    int countPointList(SearchCondition sc);

    /* 사용가능한 적립금*/
    int pointGross(MyPointVO myPointVO);


    /* 30일 이내 소멸 예정 적립금 */
    int pointGross30(MyPointVO myPointVO);


}