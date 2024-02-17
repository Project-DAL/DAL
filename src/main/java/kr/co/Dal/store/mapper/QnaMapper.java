package kr.co.Dal.store.mapper;

import kr.co.Dal.store.model.QnaVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QnaMapper {

    void insertQna(QnaVO qnaVO);

    List<QnaVO> selectQna(QnaVO qnaVO);
}
