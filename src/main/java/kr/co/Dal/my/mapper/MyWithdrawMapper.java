
/*
    파일명        : MyWithdrawMapper.java
    최초 작성자    : 박제형
    최초 작성날짜   : 2024.02.03
*/


package kr.co.Dal.my.mapper;


import kr.co.Dal.my.model.MyWithdrawVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyWithdrawMapper {


    /* 사용가능한 포인트 */
    //List<MyWithdrawVO> pointGross(MyWithdrawVO myWithdrawVO);
    int pointGross(MyWithdrawVO myWithdrawVO);



    /* 사용가능한 쿠폰 개수 */
    //List<MyWithdrawVO> couponGross(MyWithdrawVO myWithdrawVO);
    int couponGross(MyWithdrawVO myWithdrawVO);



    /* 회원 탈퇴 */
    public void myWithdraw(MyWithdrawVO myWithdrawVO);


}
