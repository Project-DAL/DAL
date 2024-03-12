package kr.co.Dal.store.service;

import kr.co.Dal.admin.model.LiquorVO;
import kr.co.Dal.store.mapper.StoreMapper;
import kr.co.Dal.store.model.QnaVO;
import kr.co.Dal.store.model.StoreVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.mail.Store;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class StoreService {

    // 술 종류 ID와 타이틀을 매핑
//    private static final Map<String, String> PROD_TYPES = Map.of(
//            "4", "탁주",
//            "5", "증류주",
//            "6", "과실주",
//            "7", "와인"
//            // 추가 매핑 필요시 여기에 추가
//    );
//
//      prodType를 int로 선언해서 아래 변환하는 메소드에서 에러남.
//    // 술 종류 ID를 타이틀로 변환하는 메소드
//    private void convertLiquorType(StoreVO storeVO) {
//        String prodTypeTitle = PROD_TYPES.getOrDefault(storeVO.getProdType(), "알 수 없음");
//        storeVO.setProdType(prodTypeTitle);
//    }

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

    public ResponseEntity<StoreVO> selectStoreListAjax(StoreVO storeVO) {
        return storeMapper.selectProdListAjax(storeVO);
    }


}
