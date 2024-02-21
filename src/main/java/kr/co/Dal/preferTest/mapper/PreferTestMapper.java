package kr.co.Dal.preferTest.mapper;

import kr.co.Dal.comm.model.CommVO;
import kr.co.Dal.preferTest.model.SrvQaVO;
import kr.co.Dal.util.SearchCondition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PreferTestMapper {

    /**
     * 문제 및 답변 목록
     */
    List<SrvQaVO> preferList(SrvQaVO srvQaVO);

}
