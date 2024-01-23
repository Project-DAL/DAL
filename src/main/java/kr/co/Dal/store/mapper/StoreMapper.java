package kr.co.Dal.store.mapper;

import kr.co.Dal.store.model.StoreVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StoreMapper {

    List<StoreVO> selectStoreList(StoreVO storeVO);

    void insertStoreQna(StoreVO storeVO);
}
