package kr.co.Dal.museum.web;

import kr.co.Dal.museum.model.MuseumVO;
import kr.co.Dal.museum.service.MuseumService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MuseumController {

    private final MuseumService museumService;

    @GetMapping("/museum/MuseumMain")
    public String selectLiquorList() {
        return "/museum/MuseumMain";
    }

    /* 분류 선택에 따른 술 목록 가져오기 */
    @GetMapping("/museum/liqList")
    @ResponseBody
    public ResponseEntity<List<MuseumVO>> selectLiqList(MuseumVO museumVO,
                                                        @RequestParam(name = "liqType") String liqType) {

        log.warn("liqType: " + liqType);

        museumVO.setLiq_type(liqType);

        List<MuseumVO> liqList = museumService.selectLiqList(museumVO);
        return ResponseEntity.ok().body(liqList);
    }

}
