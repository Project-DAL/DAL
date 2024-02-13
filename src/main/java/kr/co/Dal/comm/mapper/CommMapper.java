package kr.co.Dal.comm.mapper;

import kr.co.Dal.comm.model.CommVO;
import kr.co.Dal.comm.model.ReplyVO;
import kr.co.Dal.util.SearchCondition;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface CommMapper {

    /**
     * 게시판 목록
     */
    List<CommVO> commList(SearchCondition sc);

    /**
     * 게시판 목록 개수
     */
    int commCnt(SearchCondition sc);
    
    /**
     * 게시판 등록, 수정
     */
    void commInsert(CommVO commVO) throws Exception;

    /**
     * 게시판 삭제
     */
    void commDelete(CommVO commVO) throws Exception;

    /**
     * 게시판 조회수
     */
    int commUpdateLike(CommVO commVO);

    /**
     * 게시판 상세
     */
    CommVO commViewSelect(CommVO commVO);

    /**
     * 게시판 댓글
     */
    void commReplyInsert(ReplyVO replyVO);

    /**
     * 게시판 목록
     */
    List<ReplyVO> commReplyView(ReplyVO replyVO);





}
