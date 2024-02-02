/*
    파일명        : MyWishMapper.java
    최초 작성자    : 박제형
    최초 작성날짜   : 2024.02.02
*/


package kr.co.Dal.my.mapper;


import kr.co.Dal.my.model.MyWishVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyWishMapper {

    /* 위시리스트 목록 */
    List<MyWishVO> selectWishList(MyWishVO myWishVO);


    /* 위시리스트 삭제 */
    public void deleteWish(MyWishVO myWish);


}
