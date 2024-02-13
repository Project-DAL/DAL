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

    /* coupon VO */

    /* cp_user_tb */
    private int cpId; // 쿠폰id
    private int userId; // 회원id
    private String edate; // 사용시작일
    private String bdate; // 사용종료일
    private String useStat; // 사용상태
    private String cpUserRdat; // 적립일

    /* cp_tb */
    private String cpName; // 쿠폰이름
    private String cpCode; // 쿠폰코드
    private String cpType; // 쿠폰유형
    private String cpDisct; // 할인금액비율
    private String cpGrade; // 허용등급
    private String cpCnt; // 발급갯수
    private String cpDisctType; // 쿠폰할인유형
    private String cpMinPrice; // 최소주문금액
    private String cpWdate; // 만료날짜
    private String cpRdate; // 생성날짜


}
