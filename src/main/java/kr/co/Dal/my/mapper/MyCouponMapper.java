/*
    파일명        : MyCouponMapper.java
    최초 작성자    : 박제형
    최초 작성날짜   : 2023.12.01
*/

package kr.co.Dal.my.mapper;

import kr.co.Dal.my.model.MyBoardVO;
import kr.co.Dal.my.model.MyCouponVO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface MyCouponMapper {


    /* 1. 쿠폰 리스트 조회 */
    List<MyCouponVO> selectCouponList(MyCouponVO mycouponVO);

    /* 2. 수령한 쿠폰 조회 */
    List<MyCouponVO> getCouponList(MyCouponVO mycouponVO);

    /* 3. 쿠폰 받기 버튼 */
    public void insertCoupon(MyCouponVO mycouponVO);


}
