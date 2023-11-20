package kr.co.Dal.comm.model;

import kr.co.Dal.cmmn.CmmnVO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Getter
@EqualsAndHashCode(callSuper = false)
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

}
