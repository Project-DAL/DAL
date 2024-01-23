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

    private String point_id;             // 적립금ID
    private int user_id;                 // 회원ID
    private String point_price;          // 적립 금액
    private String point_type;           // 적립 유형 (1:등급 / 2:사용)
    private int point_stat;              // 적립 상태 (0:무효 / 1:적립 / 2:사용)
    private String point_edate;          // 적립금 사용일

}