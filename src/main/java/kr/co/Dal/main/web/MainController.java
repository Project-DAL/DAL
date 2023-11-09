package kr.co.Dal.main.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    // Main Page
    @GetMapping("/index")
    public String index(){
        return "/index";
    }

}
