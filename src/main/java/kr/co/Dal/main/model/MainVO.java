package kr.co.Dal.main.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MainVO {

    /** st_tb */
    private int st_id;              // 스토어 ID
    private int location_id;        // 지역 ID
    private int st_type_id;         // 스토어 유형
    private String st_nm;           // 스토어명
    private String st_city;         // (필요여부의논)
    private String st_addr;         // 주소
    private String st_stts;         // 상태 (0:유효/1:무효)
    private String st_longtitude;   // 경도
    private String st_lattitude;    // 위도
    private String st_xy;           // 포인트(경도, 위도)
    private String st_info;         // 정보
    private String st_addr_detail;  // 상세주소
    private String st_tel;          // 전화번호
    private String st_thumbs;       // 사진
    private String st_rdate;        // 등록일자
    private String st_udate;        // 수정일자
    private String st_wdate;        // 삭제일자
    private String st_hours;        // 영업시간
    private String st_liquorNo;     // 판매주류

}
