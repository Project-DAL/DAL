/*
    파일명        : MyWishService
    최초 작성자    : 박제형
    최초 작성날짜   : 2024.02.02
*/


package kr.co.Dal.my.service;

import kr.co.Dal.my.mapper.MyWishMapper;
import kr.co.Dal.my.model.MyWishVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyWishService {

    private final MyWishMapper myWishMapper;


    /* 위시리스트 목록 */
    public List<MyWishVO> selectWishList(MyWishVO myWishVO) {
        return myWishMapper.selectWishList(myWishVO);
    }


    /* 위시리스트 삭제 */
    public void deleteWish(MyWishVO myWishVO) {

        for (int i = 0; i < myWishVO.getProdidArray().length; i++){
            int prodId = Integer.parseInt(myWishVO.getProdidArray()[i]);
            myWishVO.setProd_id(prodId);
            myWishMapper.deleteWish(myWishVO);
        }
    }

}
