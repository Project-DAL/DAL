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


    private String orderRdates;
    private String orderSttss;
    private String prodIds;
    private String prodTits;
    private String odSellPrices;
    private String odCnts;
    private String odSttss;

    private String odDateValue;
    private String odSttsValue;

    private String userGrade;
    private String orderRdate;
    private int orderStts;
    private String prodTit;

    /*order 상세 VO*/
    private int odId; // 주문상세번호
    private int prodId; // 술 번호
    private int orderId; // 주문번호
    private int odCnt; //  수량
    private int odOriPrice; //가격
    private int odStts; // 처리 상태
    private int odSellPrice; // 최종가격


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


    /*wish VO*/
    private int bardId;
    private String[] prodidArray;


    /* pointVO*/
    private String pointId;             // 적립금ID
    private String pointPrice;          // 적립 금액
    private String pointType;           // 적립 유형 (1:등급 / 2:사용)
    private int pointStat;              // 적립 상태 (0:무효 / 1:적립 / 2:사용)
    private String pointEdate;          // 적립금 사용일

}
