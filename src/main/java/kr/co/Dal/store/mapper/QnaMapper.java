package kr.co.Dal.store.mapper;

import kr.co.Dal.store.model.QnaVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QnaMapper {

    void insertQna(QnaVO qnaVO);
}
