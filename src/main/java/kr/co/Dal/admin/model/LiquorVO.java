package kr.co.Dal.admin.model;

import lombok.Data;

@Data
public class LiquorVO {
    private int liqId; // 술아이디
    private String liqNm; // 술이름
    private String liqType; // 술종류
    private String liqPlace; // 생산지
    private String liqCp; // 제조사
    private String liqAlc; // 도수
    private String liqHash; // 해시태그
    private String liqStts; // 삭제여부
    private String liqMemo; // 간략설명
    private String liqCn; // 자세한설명
}
