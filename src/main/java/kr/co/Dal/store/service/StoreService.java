package kr.co.Dal.store.service;

import kr.co.Dal.store.mapper.StoreMapper;
import kr.co.Dal.store.model.StoreVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreMapper storeMapper;

    public List<StoreVO> selectStoreList(StoreVO storeVO) {
        return storeMapper.selectStoreList(storeVO);
    }

    public void insertStoreQna(StoreVO storeVO) {
        storeMapper.insertStoreQna(storeVO);
    }


}
