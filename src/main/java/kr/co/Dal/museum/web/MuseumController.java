package kr.co.Dal.museum.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MuseumController {

    @GetMapping("/museum/MuseumMain")
    public String selectLiquorList() {
        return "/museum/MuseumMain";
    }

}
