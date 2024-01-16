package kr.co.Dal.comm.web;
/*

 */

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/comm")
@RequiredArgsConstructor
public class CommController {

    /**
     *  게시판 목록
     */
    @GetMapping("/commList")
    public String commList() {
        return "comm/commList";
    }


    /**
     * 게시판 등록
     */
    @GetMapping("/commWrite")
    public String commWrite() {
        return "comm/commWrite";
    }

}
