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

/*
File Name      : MuseumController.java
Program Name   : 주류 박물관 Controller
Draft Author   : 이원정
Draft Date     : 2024.01.22

Revision History
Ver.  Date          Revised By   Description
————————————————————————————————————————————————————————————
0.1   2024.01.22    이원정       최초개발
————————————————————————————————————————————————————————————
*/

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

        museumVO.setLiqType(liqType);

        List<MuseumVO> liqList = museumService.selectLiqList(museumVO);
        return ResponseEntity.ok().body(liqList);
    }

    /* 술 정보 가져오기 */
    @GetMapping("/museum/liq")
    @ResponseBody
    public ResponseEntity<List<MuseumVO>> selectLiq(MuseumVO museumVO,
                                                    @RequestParam(name = "liqId") int liqId){
        museumVO.setLiqId(liqId);
        List<MuseumVO> liq = museumService.selectLiq(museumVO);
        return ResponseEntity.ok().body(liq);
    }

}
