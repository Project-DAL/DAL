package kr.co.Dal.comm.service;

import kr.co.Dal.comm.mapper.CommMapper;
import kr.co.Dal.comm.model.CommVO;
import kr.co.Dal.comm.model.ReplyVO;
import kr.co.Dal.my.model.MyBoardVO;
import kr.co.Dal.util.PageHandler;
import kr.co.Dal.util.SearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommService {

    @Autowired
    private final CommMapper commMapper;

    /**
     * 게시판 페이지, 목록
     */
    public List<CommVO> commList(Model model, CommVO commVO, SearchCondition sc) {
        int totalCnt = commMapper.commCnt(sc);

        int totalPage = (int)Math.ceil(totalCnt / (double)sc.getPageSize());
        if(sc.getPage() > totalPage) sc.setPage(totalPage);

        // 한 페이지에 보일 건수
        int naviSize = 5;

        PageHandler pageHandler = new PageHandler(totalCnt, sc, naviSize);

        // model 전송
        List<CommVO> commList = commMapper.commList(sc);

        model.addAttribute("commList", commList);
        model.addAttribute("ph", pageHandler);

        return commList;
    }

    /**
     * 게시판 상세페이지
     */
    public CommVO commViewSelect(Model model, CommVO commVO) {

        CommVO commDetail = commMapper.commViewSelect(commVO);
        model.addAttribute("commDetail", commDetail);

        return commDetail;

    }

    /**
     * 게시판 조회수
     */
    public int commUpdateLike(CommVO commVO) {
        return commMapper.commUpdateLike(commVO);
    }


    /**
     * 게시판 답글형 댓글 페이징
     */
    public List<ReplyVO> commReplyView(Model model, ReplyVO replyVO, SearchCondition sc) {

        ReplyVO replyMax = commMapper.replyMax(replyVO);
        int totalCnt = commMapper.replyCnt(replyVO);

        int totalPage = (int)Math.ceil(totalCnt / (double)sc.getPageSize());
        if(sc.getPage() > totalPage) sc.setPage(totalPage);

        // 한 페이지에 보일 건수
        int naviSize = 5;

        PageHandler pageHandler = new PageHandler(totalCnt, sc, naviSize);

        List<ReplyVO> replyList = commMapper.commReplyView(sc);

        model.addAttribute("replyList", replyList);
        model.addAttribute("ph", pageHandler);
        model.addAttribute("replyMax", replyMax);

        return replyList;
    }


}
