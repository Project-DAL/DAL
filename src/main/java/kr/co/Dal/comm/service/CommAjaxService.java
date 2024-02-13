package kr.co.Dal.comm.service;

import kr.co.Dal.comm.mapper.CommMapper;
import kr.co.Dal.comm.model.CommVO;
import kr.co.Dal.comm.model.ReplyVO;
import kr.co.Dal.my.model.MyAnsVO;
import kr.co.Dal.util.PageHandler;
import kr.co.Dal.util.SearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommAjaxService {
    @Autowired
    private final CommMapper commMapper;

    /**
     * 게시판 등록, 수정
     */
    public void commInsert(CommVO commVO) throws Exception {
        commMapper.commInsert(commVO);
    }

    /**
     * 게시판 삭제
     */
    public void commDelete(CommVO commVO) throws Exception {
        commMapper.commDelete(commVO);
    }

    /**
     * 게시판 조회수
     */
    public int commUpdateLike(CommVO commVO) {
        return commMapper.commUpdateLike(commVO);
    }

    /**
     * 게시판 상세
     */
    public CommVO commViewSelect(CommVO commVO) {
        return commMapper.commViewSelect(commVO);
    }

    /**
     * 게시판 댓글
     */
    public void commReplyInsert(ReplyVO replyVO) {
        commMapper.commReplyInsert(replyVO);
    }

    /**
     * 게시판 댓글 목록
     */
    public List<ReplyVO> commReplyView(ReplyVO replyVO) {
        return commMapper.commReplyView(replyVO);
    }

}
