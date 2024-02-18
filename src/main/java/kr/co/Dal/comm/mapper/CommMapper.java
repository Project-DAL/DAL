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
    void commInsert(CommVO commVO);

    /**
     * 게시판 삭제
     */
    void commDelete(CommVO commVO);

    /**
     * 게시판 조회수
     */
    int commUpdateLike(CommVO commVO);

    /**
     * 게시판 상세 수정 조회
     */
    CommVO commViewSelect(CommVO commVO);

    /**
     * 게시판 댓글 등록, 수정
     */
    void commReplyInsert(ReplyVO replyVO);

    /**
     * 게시판 목록
     */
    List<ReplyVO> commReplyView(SearchCondition sc);

    /**
     * 게시판 댓글 개수
     */
    int replyCnt(ReplyVO replyVO);

    /**
     * 게시판 댓글그룹 MAX
     */
    ReplyVO replyMax(ReplyVO replyVO);

    /**
     * 게시판 댓글 MAX
     */
    ReplyVO replyGpSeqMaxSelect(ReplyVO replyVO);


    /**
     * 게시판 댓글 삭제
     */
    void commReplyDelete(ReplyVO replyVO);


}
