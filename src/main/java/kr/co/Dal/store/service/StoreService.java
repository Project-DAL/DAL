package kr.co.Dal.store.service;

import kr.co.Dal.store.mapper.StoreMapper;
import kr.co.Dal.store.model.QnaVO;
import kr.co.Dal.store.model.StoreVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.mail.Store;
import java.util.List;


@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreMapper storeMapper;

    public List<StoreVO> selectMainList(StoreVO storeVO) {
        return storeMapper.selectMainList(storeVO);
    }

    public List<StoreVO> selectProdList(StoreVO storeVO) {
        return storeMapper.selectProdList(storeVO);
    }

    public StoreVO selectProdOne(StoreVO storeVO) {
        return storeMapper.selectProdOne(storeVO);
    }

//    public void insertStoreQna(QnaVO qnaVO) {
//        storeMapper.insertStoreQna(qnaVO);
//    }

//    public QnaVO selectProdQna(QnaVO qnaVO) {
//        storeMapper.selectStoreList(qnaVO);
//    }


}
