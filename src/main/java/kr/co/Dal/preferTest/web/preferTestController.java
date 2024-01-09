package kr.co.Dal.preferTest.web;
/*

 */

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/preferTest")
@RequiredArgsConstructor
public class preferTestController {

    /**
     * 술테스트
     */
    @GetMapping("/preferTest")
    public String preferTest() {
        return "preferTest/preferTest";
    }

    @GetMapping("/preferResult")
    public String preferResult() {
        return "preferTest/preferResult";
    }


}
