package kr.co.Dal.my.model;

/*
    파일명        : MyWishVO
    최초 작성자    : 박제형
    최초 작성날짜   : 2024.02.02
*/


import kr.co.Dal.store.model.StoreVO;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class MyWishVO extends StoreVO {

    private int userId;
    private int prodId;
    private int bardId;

    private int imgId;

    private String[] prodidArray;


}
