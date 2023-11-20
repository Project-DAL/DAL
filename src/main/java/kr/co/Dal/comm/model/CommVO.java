package kr.co.Dal.comm.model;

import kr.co.Dal.cmmn.CmmnVO;
import lombok.*;

import java.sql.Date;

@Getter
@EqualsAndHashCode(callSuper = false)
@Builder
public class CommVO extends CmmnVO {
    private int bardId;
    private int userId;
    private int bardType;
    private String bardTit;
    private String bardCn;
    private int bardCnt;
    private int bardLikeCnt;
    private int bardStts;
    private int cateType;

    @Setter
    private Date bardRdate;

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
