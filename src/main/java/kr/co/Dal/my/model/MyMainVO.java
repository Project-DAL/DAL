/*
    파일명        : MyMainVO
    최초 작성자    : 박제형
    최초 작성날짜   : 2024.02.05
*/

package kr.co.Dal.my.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MyMainVO{


    private String user_grade;


    /*order 상세 VO*/
    private int od_id; // 주문상세번호
    private int prod_id; // 술 번호
    private int order_id; // 주문번호
    private int od_cnt; //  수량
    private int od_ori_price; //가격
    private int od_stts; // 처리 상태
    private int od_sell_price; // 최종가격


    /* coupon VO */
    /* cp_user_tb */
    private int cp_id; // 쿠폰id
    private int user_id; // 회원id
    private String edate; // 사용시작일
    private String bdate; // 사용종료일
    private String use_stat; // 사용상태
    private String cp_user_rdat; // 적립일

    /* cp_tb */
    private String cp_name; // 쿠폰이름
    private String cp_code; // 쿠폰코드
    private String cp_type; // 쿠폰유형
    private String cp_disct; // 할인금액비율
    private String cp_grade; // 허용등급
    private String cp_cnt; // 발급갯수
    private String cp_disct_type; // 쿠폰할인유형
    private String cp_min_price; // 최소주문금액
    private String cp_wdate; // 만료날짜
    private String cp_rdate; // 생성날짜


    /*wish VO*/
    private int bard_id;
    private String[] prodidArray;


    /* pointVO*/
    private String point_id;             // 적립금ID
    private String point_price;          // 적립 금액
    private String point_type;           // 적립 유형 (1:등급 / 2:사용)
    private int point_stat;              // 적립 상태 (0:무효 / 1:적립 / 2:사용)
    private String point_edate;          // 적립금 사용일

}
