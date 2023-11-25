package kr.co.Dal.my.web;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MyController {

    // My Page Main
    @GetMapping("/my/MyPageMain")
    public String mypagemain() {
        return "my/MyPageMain";
    }
}

