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

    private int replyId;
    private int bardId;
    private String replyUserId;
    private String replyCn;
    private String replyRdate;
    private int replyStts;
    private int replyGp;
    private int replyGpSeq;
    private int replyDepth;


    public String[] ansidArray;

}
