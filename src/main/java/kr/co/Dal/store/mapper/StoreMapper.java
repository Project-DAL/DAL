package kr.co.Dal.store.mapper;

import kr.co.Dal.store.model.QnaVO;
import kr.co.Dal.store.model.StoreVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StoreMapper {

    // 메인페이지 목록조회
    List<StoreVO> selectMainList(StoreVO storeVO);

    // 리스트페이지 목록조회
    List<StoreVO> selectProdList(StoreVO storeVO);

    // 상세페이지 제품조회
    StoreVO selectProdOne(StoreVO storeVO);

    void insertStoreQna(QnaVO qnaVO);

//    QnaVO selectProdQna(QnaVO qnaVO);
}
