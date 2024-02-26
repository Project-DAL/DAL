package kr.co.Dal.preferTest.model;

import lombok.Data;

@Data
public class SrvResVO {
    private int srvResId;      // 결과 유형
    private String srvResImg;  // 결과 이미지
    private String srvResType; // 결과 이름
    private String srvResCn;   // 결과 내용
}
