/*
    파일명        : MyPointVO
    최초 작성자    : 박제형
    최초 작성날짜   : 2024.01.22
*/

package kr.co.Dal.my.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MyPointVO {

    private String pointId;             // 적립금ID
    private int userId;                 // 회원ID
    private String pointPrice;          // 적립 금액
    private String pointType;           // 적립 유형 (1:등급 / 2:사용)
    private int pointStat;              // 적립 상태 (0:무효 / 1:적립 / 2:사용)
    private String pointEdate;          // 적립금 사용일
    private String pointRdate;         //적립일



    private String pointTill;           //유효기간 +60일
    private String odUsePoint;
    private String prodTit;
}