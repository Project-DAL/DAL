package kr.co.Dal.comm.model;

import kr.co.Dal.cmmn.CmmnVO;
import lombok.*;

import java.sql.Date;

@Getter
@EqualsAndHashCode(callSuper = false)
@Builder
public class CommVO extends CmmnVO {
    private int bardId;        // 게시글ID
    private int userId;        // 회원ID
    private int bardType;      // 게시글 유형
    private String bardTit;    // 제목
    private String bardCn;     // 내용
    private Date bardRdate;  // 등록일
    private int bardCnt;       // 조회수
    private int bardLikeCnt;   // 좋아요수
    private int bardStts;      // 게시글상태(1:유효/0:무효)
    private int cateType;      // 카테고리 유형

    // bardId, bardTit, bardRdate 필드만을 포함한 toString() 메서드 직접 작성
    @Override
    public String toString() {
        return "CommVO{" +
                "bardId=" + bardId +
                ", bardTit='" + bardTit + '\'' +
                ", bardRdate=" + bardRdate +
                '}';
    }
}
