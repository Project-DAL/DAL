package kr.co.Dal.cmmn.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/* 공통 VO */
@Data
@EqualsAndHashCode(callSuper = false)
public class CmmnVO {

    /* liq_tb (술) */
    private int liqId;          // 술 아이디
    private String liqNm;       // 술 이름
    private String liqType;     // 술 종류
    private String liqPlace;    // 생산지
    private String liqCp;       // 제조사
    private String liqAlc;      // 도수
    private String liqHash;     // 해시태그
    private String liqStts;     // 삭제여부 (0:무효/1:유효)
    private String liqMemo;     // 간략 설명
    private String liqCn;       // 상세 설명
    private int liqAroma;       // 향
    private int liqSmooth;      // 목넘김
    private int liqBody;        // 바디감

    /* 술 이미지 */
    private int imgId; // 이미지 번호
    private String imgPath; // 이미지 파일 경로
    private String originalName; // 원본 파일 이름
    private String saveName; // 저장된 파일 이름
    private Date uploadDate; // 업로드 날짜




}
