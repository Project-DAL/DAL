package kr.co.Dal.my.service;

/*
    파일명        : MyWithdrawService
    최초 작성자    : 박제형
    최초 작성날짜   : 2024.02.03
*/

import kr.co.Dal.my.mapper.MyWithdrawMapper;
import kr.co.Dal.my.model.MyWithdrawVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyWithdrawService {

    private final MyWithdrawMapper myWithdrawMapper;

    /* 사용가능한 포인트 */
    public int pointGross(MyWithdrawVO myWithdrawVO){
        return myWithdrawMapper.pointGross(myWithdrawVO);
    }


    /* 사용가능한 쿠폰 개수 */
    public int couponGross(MyWithdrawVO myWithdrawVO){
        return myWithdrawMapper.couponGross(myWithdrawVO);
    }


    /* 회원 탈퇴 */
    public void myWithdraw(MyWithdrawVO myWithdrawVO){ myWithdrawMapper.myWithdraw(myWithdrawVO);
    }




}
