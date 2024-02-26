package kr.co.Dal.preferTest.model;

import lombok.Data;

import java.sql.Date;


@Data
public class SrvQaVO {
    private int srvQueId;      // 질문ID
    private String srvQue;     // 질문
    private String srvAns1;    // 답변1
    private String srvAns2;    // 답변2

}
