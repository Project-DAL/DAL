package kr.co.Dal.store.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class StoreVO {

    private int prod_id;                 // 상품번호(pk)
    private String prod_tit;             // 상품명
    private int prod_type;               // 술 종류
    private int prod_cnt;                // 수량
    private int prod_ori_price;          // 정상 가격
    private int prod_sale_price;         // 세일 가격
    private String prod_img;             // 상품 이미지
    private int prod_stts;               // 상품상태
    private int prod_like;               // 좋아요 수
    private String prod_rdate;           // 수정일
    private String prod_udate;           // 등록일
    private String prod_cn_file;         // 상세설명 이미지

    // 기본 생성자 추가
    public StoreVO() {
        // 기본 생성자 내용이 필요하다면 추가
    }
}