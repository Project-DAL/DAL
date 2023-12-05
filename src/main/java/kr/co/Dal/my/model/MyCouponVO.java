/*
    파일명        : MyCouponVO
    최초 작성자    : 박제형
    최초 작성날짜   : 2023.12.01
*/

package kr.co.Dal.my.model;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MyCouponVO {

    private String cp_id;             // 쿠폰ID
    private String cp_name;           // 쿠폰명
    private String cp_code;           // 쿠폰코드
    private String cp_disct;          // 할인율 또는 할인금
    private String cp_grade;          // 사용등급
    private String cp_cnt;            // 발급개수(선착순 쿠폰)
    private String cp_min_price;      // 최소주문금액
    private String cp_type;           // 쿠폰유형(1:등급 / 2:이벤트)
    private String cp_disct_type;      // 쿠폰할인유형(1:할인율 / 2:할인금액)
    private String cp_wdate;          // 만료날짜
    private String cp_rdate;          // 생성날짜




}
