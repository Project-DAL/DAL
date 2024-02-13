package kr.co.Dal.comm.web;

import kr.co.Dal.comm.model.CommVO;
import kr.co.Dal.comm.model.ReplyVO;
import kr.co.Dal.comm.service.CommAjaxService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@Slf4j
@RequiredArgsConstructor
public class CommAjaxController {

    @Autowired
    private final CommAjaxService commAjaxService;

    /**
     * 게시판 등록, 수정
     */
    @RequestMapping("/comm/commAjaxWriteInsert")
    public String commWriteInsert(CommVO commVO) throws Exception{
        commAjaxService.commInsert(commVO);
        return "/comm/commList";	//게시글 리스트로 이동
    }

    /**
     * 게시판 삭제
     */
    @RequestMapping("/comm/commAjaxWriteDelete")
    public String commWriteDelete(CommVO commVO) throws Exception{
        commAjaxService.commDelete(commVO);
        return "/comm/commList";	//게시글 리스트로 이동
    }

    /**
     * 게시판 조회수
     */
    @RequestMapping("/comm/commAjaxViewLike")
    public String commViewLike(CommVO commVO) throws Exception{
        commAjaxService.commUpdateLike(commVO);
        return "/comm/commList";
    }

    /**
     * 게시판 상세
     */
    @RequestMapping("/comm/commAjaxView")
    public ResponseEntity<CommVO> commView(@RequestBody CommVO commVO) throws Exception{
        return ResponseEntity.ok(commAjaxService.commViewSelect(commVO));
    }

    /**
     * 게시판 댓글 등록
     */
    @RequestMapping("/comm/commAjaxReplyInsert")
    public String commReplyInsert(ReplyVO replyVO) throws Exception{
        commAjaxService.commReplyInsert(replyVO);
        return "redirect:/comm/commView?bardId="+replyVO.getBardId();
    }

    /**
     * 게시판 댓글 목록
     */
    @RequestMapping("/comm/commAjaxReplyView")
    public ResponseEntity<List<ReplyVO>> commReplyView(@RequestBody ReplyVO replyVO) throws Exception {
        return ResponseEntity.ok(commAjaxService.commReplyView(replyVO));
    }



}

