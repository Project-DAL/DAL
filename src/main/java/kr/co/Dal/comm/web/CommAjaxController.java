package kr.co.Dal.comm.web;

import kr.co.Dal.comm.model.CommVO;
import kr.co.Dal.comm.model.ReplyVO;
import kr.co.Dal.comm.service.CommAjaxService;
import kr.co.Dal.util.SearchCondition;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
        return "redirect:/comm/commList";	//게시글 리스트로 이동
    }

    /**
     * 게시판 상세 수정 조회
     */
    @RequestMapping("/comm/commAjaxView")
    public ResponseEntity<CommVO> commView(@RequestBody CommVO commVO) throws Exception{
        return ResponseEntity.ok(commAjaxService.commViewSelect(commVO));
    }

    /**
     * 게시판 삭제
     */
    @RequestMapping("/comm/commAjaxWriteDelete")
    public String commWriteDelete(CommVO commVO) throws Exception{
        commAjaxService.commDelete(commVO);
        return "redirect:/comm/commList";	//게시글 리스트로 이동
    }

    /**
     * 게시판 댓글 등록, 수정
     */
    @RequestMapping("/comm/commAjaxReplyInsert")
    public String commReplyInsert(ReplyVO replyVO) throws Exception {
        commAjaxService.commReplyInsert(replyVO);
        return "redirect:/comm/commView?bardId="+replyVO.getBardId();
    }

    /**
     * 게시판 댓글 삭제
     */
    @RequestMapping("/comm/commAjaxWriteReplyDelete")
    public String commReplyDelete(ReplyVO replyVO) throws Exception {
        commAjaxService.commReplyDelete(replyVO);
        return "redirect:/comm/commView?bardId=" + replyVO.getBardId();
    }

    /**
     * 게시판 replyGpSeqMax
     */
    @RequestMapping("/comm/commAjaxWriteReplyGpSeqMax")
    public ResponseEntity<ReplyVO> commWriteReplyGpSeqMax(ReplyVO replyVO) throws Exception {
        return ResponseEntity.ok(commAjaxService.commWriteReplyGpSeqMax(replyVO));
    }




}

