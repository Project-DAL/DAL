package kr.co.Dal.preferTest.web;
/*

 */

import kr.co.Dal.comm.service.CommService;
import kr.co.Dal.preferTest.model.SrvQaVO;
import kr.co.Dal.preferTest.model.SrvResVO;
import kr.co.Dal.preferTest.service.PreferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/preferTest")
@RequiredArgsConstructor
public class preferTestController {

    @Autowired
    private final PreferService preferService;

    /**
     * 술 MBTI 문제
     */
    @GetMapping("/preferTest")
    public String preferTest(Model model, SrvQaVO srvQaVO) {

        preferService.preferList(model, srvQaVO);

        return "/preferTest/preferTest";
    }

    /**
     * 술 MBTI 결과
     */
    @GetMapping("/preferResult")
    public String preferResult(Model model, SrvResVO srvResVO) {

        preferService.preferResult(model, srvResVO);

        return "/preferTest/preferResult";
    }


}
