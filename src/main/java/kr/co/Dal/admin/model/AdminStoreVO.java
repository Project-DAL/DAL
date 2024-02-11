package kr.co.Dal.admin.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class AdminStoreVO {
    private int prodId;
    private String prodTit;
    private int prodType;
    private int prodCnt;
    private int prodOriPrice;
    private int prodSalePrice;
    private int prodStts;
    private int prodLike;
    private String prodRdate;
    private String prodUdate;
    private String prodCnFile;

    private List<ProdImgVO> images;
}
