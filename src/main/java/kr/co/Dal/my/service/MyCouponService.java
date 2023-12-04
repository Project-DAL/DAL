/*
    파일명        : MyCouponService
    최초 작성자    : 박제형
    최초 작성날짜   : 2023.12.01
*/

package kr.co.Dal.my.service;


import kr.co.Dal.my.mapper.MyCouponMapper;
import kr.co.Dal.my.model.MyCouponVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MyCouponService {

    private final MyCouponMapper myCouponMapper;

    /* 1. 쿠폰 리스트 조회 1*/
    public List<MyCouponVO> selectCouponList(MyCouponVO mycouponVO) {
        return myCouponMapper.selectCouponList(mycouponVO);
    }

}
