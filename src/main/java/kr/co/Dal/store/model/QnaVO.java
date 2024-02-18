package kr.co.Dal.store.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class QnaVO {

    public int qnaId;                   // 문의글번호(pk)
    public int userId;                  // 작성자id
    public String qnaType;              // 문의글유형
    public int qnaSecret;               // 공개여부
    public String qnaTit;               // 제목
    public String qnaCn;                // 내용
    public Date qnaRdate;               // 등록일
    public Date qnaUdate;               // 수정일
    public int qnaStts;                 // 게시글상태
    public int qnaAnsYn;                // 답변여부
    public String qnaAns;               // 관리자 답글
    public int prodId;                  // 상품id
}
