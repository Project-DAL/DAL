package kr.co.Dal.store.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
@Builder
public class StoreVO {
    
    private int prodId;                 // 상품번호(pk)
    private String prodTit;             // 상품명
    private int prodType;               // 술 종류
    private int prodCnt;                // 수량
    private int prodOriPrice;           // 정상 가격
    private int prodSalePrice;          // 세일 가격
    private String prodImg;             // 상품 이미지
    private int prodStts;               // 상품상태
    private int prodLike;               // 좋아요 수
    private String prodRdate;           // 수정일
    private String prodUdate;           // 등록일
    private String prodCnFile;          // 상세설명 이미지
}
