/*
    파일명        : MyMainMapper.java
    최초 작성자    : 박제형
    최초 작성날짜   : 2024.02.05
*/

package kr.co.Dal.my.mapper;


import kr.co.Dal.my.model.MyMainVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyMainMapper {

    /* 쿠폰 개수 */
    int couponGross(MyMainVO myMainVO);

    /* 사용가능한 적립금 */
    int pointGross(MyMainVO myMainVO);

    /* 위시리스트 개수 */
    int wishGross(MyMainVO myMainVO);

    /* 등급 보기 */
    String rankView(MyMainVO myMainVO);

    /* 주문 배송 목록 */
    List<MyMainVO> orderViewList(MyMainVO myMainVO);



}
