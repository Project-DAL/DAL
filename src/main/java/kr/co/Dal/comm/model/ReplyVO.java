package kr.co.Dal.comm.model;
import lombok.Data;
import org.springframework.data.relational.core.sql.In;

import java.sql.Date;

@Data
public class ReplyVO{
    private Integer replyId;
    private Integer bardId;
    private String replyUserId;
    private String replyCn;
    private Date replyRdate;
    private Integer replyStts;
    private Integer replyGp;
    private Integer replyGpSeq;
    private Integer replyDepth;
    private Integer totalDataCnt;
}
