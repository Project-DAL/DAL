package kr.co.Dal.comm.model;

import kr.co.Dal.cmmn.model.CmmnVO;
import lombok.*;

import java.sql.Date;


@Data
public class CommVO {
    private int bardId;        // 게시글ID
    private int userId;        // 회원ID
    private int bardType;      // 게시글 유형
    private String bardTit;        // 제목
    private String bardCn;         // 내용
    private Date bardRdate;        // 등록일
    private int bardCnt;       // 조회수
    private int bardLike_cnt;  // 좋아요수
    private int bardStts;      // 게시글상태(1:유효/0:무효)
    private String searchKeyword;
    private String searchValue;

}
