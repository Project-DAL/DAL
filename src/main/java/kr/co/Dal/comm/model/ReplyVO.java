package kr.co.Dal.comm.model;
import lombok.Data;
import org.springframework.data.relational.core.sql.In;

import java.sql.Date;

@Data
public class ReplyVO{
    private int replyId;
    private int bardId;
    private String replyUserId;
    private String replyCn;
    private Date replyRdate;
    private int replyStts;
    private int replyGp;
    private int replyDepth;
    private int totalDataCnt;
    private int replyGpMax;
    private int replyIdMax;
}
