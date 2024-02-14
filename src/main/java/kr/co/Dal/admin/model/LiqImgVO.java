package kr.co.Dal.admin.model;

import lombok.Data;

import java.util.Date;

@Data
public class LiqImgVO {
    private int imgId; // 이미지 번호
    private int liqId; // 술아이디
    private String imgPath; // 이미지 파일 경로
    private String originalName; // 원본 파일 이름
    private String saveName; // 저장된 파일 이름
    private Date uploadDate; // 업로드 날짜
}
