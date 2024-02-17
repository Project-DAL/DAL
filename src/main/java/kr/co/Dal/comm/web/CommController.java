package kr.co.Dal.comm.web;
/*

 */

import kr.co.Dal.comm.model.CommVO;
import kr.co.Dal.comm.model.ReplyVO;
import kr.co.Dal.comm.service.CommAjaxService;
import kr.co.Dal.comm.service.CommService;
import kr.co.Dal.my.model.MyBoardVO;
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
@RequestMapping("/comm")
@RequiredArgsConstructor
public class CommController {

    @Autowired
    private final CommService commService;

    /**
     *  게시판 페이지, 목록
     */
    @RequestMapping("/commList")
    public String commList(Model model,
                           @RequestParam Map map,
                           CommVO commVO,
                           @ModelAttribute SearchCondition sc) {

        // 검색어
        sc.setMap(map);

        commService.commList(model, commVO, sc);

        return "/comm/commList";
    }

    /**
     * 게시판 상세 페이지
     */
    @RequestMapping("/commView")
    public String commView(Model model,
                           @RequestParam(name = "bardId", required = false) String bardId,
                           @RequestParam Map map,
                           CommVO commVO,
                           ReplyVO replyVO,
                           @ModelAttribute SearchCondition sc) {

        model.addAttribute("bardId", bardId);

        //게시판 상세
        commService.commViewSelect(model, commVO);

        //조회수
        commService.commUpdateLike(commVO);

        // 검색어
        sc.setMap(map);
        
        //게시판 상세 댓글
        commService.commReplyView(model, replyVO, sc);

        return "/comm/commView";
    }

    /**
     * 게시판 등록/수정 페이지
     */
    @RequestMapping("/commWrite")
    public String commWrite(Model model, @RequestParam(name = "bardId", required = false) String bardId) {
        model.addAttribute("bardId", bardId);
        return "/comm/commWrite";
    }

}
