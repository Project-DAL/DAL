package kr.co.Dal.main.web;

import kr.co.Dal.main.model.MainVO;
import kr.co.Dal.main.service.MainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;

    /* 셀렉트: 지역 가져오기 (location_tb) */

    @GetMapping("/provinceList")
    public String selectProvinceList(MainVO mainVO) {
        log.warn("selectProvinceList controller");
        List<MainVO> provinceList = mainService.selectProvinceList(mainVO);
        log.warn("provinceList: " + provinceList);
        return "/index";
    }
}
