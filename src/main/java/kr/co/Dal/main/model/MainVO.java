package kr.co.Dal.main.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/*
File Name      : MainVO.java
Program Name   : 메인 검색 서비스 VO
Draft Author   : 이원정
Draft Date     : 2024.01.22

Revision History
Ver.  Date          Revised By   Description
————————————————————————————————————————————————————————————
0.1   2024.01.22    이원정       최초개발
———————————————————————————————————————————————————————————>
*/

@Data
@EqualsAndHashCode(callSuper = false)
public class MainVO {

    /* st_tb */
    private int stId;              // 스토어 ID
    private int locationId;        // 지역 ID
    private int st_typeId;         // 스토어 유형
    private String stNm;           // 스토어명
    private String stCity;         // (필요여부의논)
    private String stAddr;         // 주소
    private String stStts;         // 상태 (0:유효/1:무효)
    private String stLongtitude;   // 경도
    private String stLattitude;    // 위도
    private String stXy;           // 포인트(경도, 위도)
    private String stInfo;         // 정보
    private String stAddrDetail;  // 상세주소
    private String stTel;          // 전화번호
    private String stThumbs;       // 사진
    private String stRdate;        // 등록일자
    private String stUdate;        // 수정일자
    private String stWdate;        // 삭제일자
    private String stHoursOpen;    // 영업시간
    private String stHoursClosed;  // 영업시간
    private String stLiquorNo;     // 판매주류

    /* location_tb */
    private String provinceId;     // 시/도 코드
    private String provinceNm;     // 시/도명
    private String cityId;         // 시/군/구 코드
    private String cityNm;         // 시/군/구명
    private String townId;         // 읍/면/동 코드
    private String townNm;         // 읍/면/동명

    /* prod_tb */
    private String prodTit;        // 주류명

    /* prod_img_tb */
    private int imgId;          // 이미지 번호
    private int prodId;         // 상품번호
    private String imgPath;     // 이미지 파일 경로
    private String originalName; // 원본 파일 이름
    private String saveName;    // 저장된 파일 이름
    private Date uploadDate;    // 업로드 날짜

    /* 추가: 영역정보에 따른 모서리 좌표 */
    private String swLat;           // 영역정보의 남서위도
    private String swLng;           // 영역정보의 남서경도
    private String neLat;           // 영역정보의 북동위도
    private String neLng;           // 영역정보의 북동경도



}
