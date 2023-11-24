package kr.co.Dal.test.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class TestController {

    // Test
    @GetMapping("/test/test")
    public String TestPage(){
        return "/test/test";
    }

    // Weather Page 불러오기
    @GetMapping("/test/weather")
    public String WeatherPage() {
        return "/test/weather";
    }

    // Weather API (단기예보조회)


}
