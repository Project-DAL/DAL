/*
    파일명        : MyRankVO
    최초 작성자    : 박제형
    최초 작성날짜   : 2024.02.07
*/


package kr.co.Dal.my.model;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class MyRankVO {

    private int user_id;
    private String od_sell_price;
    private int user_grade;
    private String user_nick;

}
