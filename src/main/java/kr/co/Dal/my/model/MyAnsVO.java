package kr.co.Dal.my.model;


/*
    파일명        : MyBoardVO
    최초 작성자    : 박제형
    최초 작성날짜   : 2024.02.02
*/

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MyAnsVO {

    private int ans_id; // 답글번호
    private int user_id; // 회원아이디
    private String ans_cn; // 내용
    private String ans_rdate; // 등록일
    private String ans_udate; // 수정일
    private int ans_stts; // 삭제 여부
    private int ans_type; // 답글 종류

    public String[] ansidArray;

}
