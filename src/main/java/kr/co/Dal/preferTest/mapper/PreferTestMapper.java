package kr.co.Dal.preferTest.mapper;

import kr.co.Dal.comm.model.CommVO;
import kr.co.Dal.preferTest.model.SrvQaVO;
import kr.co.Dal.preferTest.model.SrvResVO;
import kr.co.Dal.util.SearchCondition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PreferTestMapper {

    /**
     * 술 MBTI 문제
     */
    List<SrvQaVO> preferList(SrvQaVO srvQaVO);

    /**
     * 술 MBTI 결과
     */
    SrvResVO preferResult(SrvResVO srvResVO);

}
