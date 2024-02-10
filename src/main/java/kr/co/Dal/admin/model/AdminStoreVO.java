package kr.co.Dal.admin.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AdminStoreVO {
    private int prodId;
    private String prodTit;
    private int prodType;
    private int prodCnt;
    private int prodOriPrice;
    private int prodSalePrice;
    private String prodImg;
    private int prodStts;
    private int prodLike;
    private String prodRdate;
    private String prodUdate;
    private String prodCnFile;
}
