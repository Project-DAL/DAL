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

    private int bardId;
    private int userId;
    private int bardType;
    private String bardTit;
    private String bardCn;
    private String bardRdate;
    private int bardCnt;
    private int bardLikeCn;
    private int bardStts;

    private String[] bardidArray;
    private String[] bardtypeArray;

    private String searchKeyword;
    private String searchValue;
}