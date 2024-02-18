package kr.co.Dal.my.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MyWithdrawVO extends MyInfoVO{



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

    private int pointCalu;

    /* point VO */

    private String pointId;             // 적립금ID
    private String pointPrice;          // 적립 금액
    private String pointType;           // 적립 유형 (1:등급 / 2:사용)
    private int pointStat;              // 적립 상태 (0:무효 / 1:적립 / 2:사용)
    private String pointEdate;          // 적립금 사용일




}

