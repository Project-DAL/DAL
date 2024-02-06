package kr.co.Dal.comm.mapper;

import kr.co.Dal.comm.model.CommVO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface CommMapper {

    List<CommVO> commList(CommVO commVO);

    void commInsert(CommVO commVO) throws Exception;		//게시판 쓰기

}
