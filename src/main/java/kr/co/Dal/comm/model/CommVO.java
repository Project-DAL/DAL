package kr.co.Dal.comm.model;

import kr.co.Dal.cmmn.model.CmmnVO;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommVO extends CmmnVO {
    private Integer bard_id;        // 게시글ID
    private Integer user_id;        // 회원ID
    private Integer bard_type;      // 게시글 유형
    private String bard_tit;    // 제목
    private String bard_cn;     // 내용
    private Date bard_rdate;  // 등록일
    private Integer bard_cnt;       // 조회수
    private Integer bard_like_cnt;   // 좋아요수
    private Integer bard_stts;      // 게시글상태(1:유효/0:무효)
    private Integer cate_type;      // 카테고리 유형

}
