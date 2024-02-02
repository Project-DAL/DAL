package kr.co.Dal.my.model;

/*
    파일명        : MyBoardVO
    최초 작성자    : 박제형
    최초 작성날짜   : 2024.01.22
*/

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MyBoardVO {

    private int bard_id;
    private int user_id;
    private int bard_type;
    private String bard_tit;
    private String bard_cn;
    private String bard_rdate;
    private int bard_cnt;
    private int bard_like_cn;
    private int bard_stts;

    private String[] bardidArray;
    private String[] bardtypeArray;
}