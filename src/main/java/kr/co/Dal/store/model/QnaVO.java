package kr.co.Dal.store.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class QnaVO {

    public int qna_id;                   // 문의글번호(pk)
    public int user_id;                  // 작성자id
    public String qna_type;              // 문의글유형
    public int qna_secret;               // 공개여부
    public String qna_tit;               // 제목
    public String qna_cn;                // 내용
    public Date qna_rdate;               // 등록일
    public Date qna_udate;               // 수정일
    public int qna_stts;                 // 게시글상태
    public int qna_ans_yn;                // 답변여부
}
