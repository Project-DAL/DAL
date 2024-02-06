package kr.co.Dal.comm.web;
/*

 */

import kr.co.Dal.comm.model.CommVO;
import kr.co.Dal.comm.service.CommService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/comm")
@RequiredArgsConstructor
public class CommController {

    /**
     *  게시판 페이지
     */
    @GetMapping("/commList")
    public String commList() {
        return "comm/commList";
    }


    /**
     * 게시판 등록/수정 페이지
     */
    @GetMapping("/commWrite")
    public String commWrite() {
        return "comm/commWrite";
    }

}
