package kr.co.Dal.comm.model;

import kr.co.Dal.cmmn.model.CmmnVO;
import lombok.*;

import java.sql.Date;


@Data
public class CommVO {
    private Integer bardId;        // 게시글ID
    private Integer userId;        // 회원ID
    private Integer bardType;      // 게시글 유형
    private String bardTit;        // 제목
    private String bardCn;         // 내용
    private Date bardRdate;        // 등록일
    private Integer bardCnt;       // 조회수
    private Integer bardLike_cnt;  // 좋아요수
    private Integer bardStts;      // 게시글상태(1:유효/0:무효)
    private String searchKeyword1;
    private String searchKeyword2;
    private String searchValue;

}
