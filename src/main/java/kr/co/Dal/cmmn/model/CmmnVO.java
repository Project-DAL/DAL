package kr.co.Dal.cmmn.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/* 공통 VO */
@Data
@EqualsAndHashCode(callSuper = false)
public class CmmnVO {

    /* liq_tb (술) */
    private int liq_id;          // 술 아이디
    private String liq_nm;       // 술 이름
    private String liq_type;     // 술 종류
    private String liq_place;    // 생산지
    private String liq_cp;       // 제조사
    private String liq_alc;      // 도수
    private String liq_hash;     // 해시태그
    private String liq_pic;      // 술 사진
    private String liq_stts;     // 삭제여부 (0:무효/1:유효)
    private String liq_memo;     // 간략 설명
    private String liq_cn;       // 상세 설명

}
