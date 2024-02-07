package kr.co.Dal.comm.web;

import kr.co.Dal.comm.model.CommVO;
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
@ResponseBody
public class CommAjaxController {

    @Autowired
    private final CommAjaxService commAjaxService;

    /**
     * 게시판 목록
     */
    @RequestMapping("/comm/commAjaxList")
    public ResponseEntity<List<CommVO>> commList(CommVO commVO) throws Exception {
        return ResponseEntity.ok(commAjaxService.commList(commVO));
    }


    /**
     * 게시판 등록
     */
    @PostMapping("/comm/commAjaxWrite")
    public String commWrite(CommVO commVO) throws Exception{
        commAjaxService.commInsert(commVO);
        return "redirect:/comm/commList";	//게시글 리스트로 이동
    }

}

