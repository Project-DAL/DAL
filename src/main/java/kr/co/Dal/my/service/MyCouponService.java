/*
    파일명        : MyCouponService
    최초 작성자    : 박제형
    최초 작성날짜   : 2023.12.01
*/

package kr.co.Dal.my.service;


import kr.co.Dal.my.mapper.MyCouponMapper;
import kr.co.Dal.my.model.MyBoardVO;
import kr.co.Dal.my.model.MyCouponVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyCouponService {

    private final MyCouponMapper myCouponMapper;

    /* 1. 쿠폰 리스트 조회 */
    public List<MyCouponVO> selectCouponList(MyCouponVO mycouponVO) {

        return myCouponMapper.selectCouponList(mycouponVO);
    }


    /* 2. 수령한 쿠폰 조회 */
    public List<MyCouponVO> getCouponList(MyCouponVO mycouponVO) {
        return myCouponMapper.getCouponList(mycouponVO);
    }


    /* 3. 쿠폰 받기 버튼 */
    public void insertCoupon(MyCouponVO mycouponVO){

        log.warn("Received cpId2: {}" , mycouponVO.getCp_id());
        myCouponMapper.insertCoupon(mycouponVO);
        }


    }


