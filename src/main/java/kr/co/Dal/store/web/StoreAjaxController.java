/*
File Name      : StoreAjaxController.java
Program Name   : 스토어 ajax 컨트롤러
Draft Author   : 김석진
Draft Date     : 2024.02.04

Revision History
Ver.  Date          Revised By   Description
------------------------------------------------------------------------------------------------------------------------
0.1   2024.01.14    김석진       최초개발
----------------------------------------------------------------------------------------------------------------------->
*/
package kr.co.Dal.store.web;

import kr.co.Dal.store.model.QnaVO;
import kr.co.Dal.store.service.QnaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreAjaxController {

    private final QnaService qnaService;

    @PostMapping("/insertQna")
    public ResponseEntity<QnaVO> insertQna(@RequestBody QnaVO qnaVO) {
        qnaService.insertQna(qnaVO);

        return ResponseEntity.ok(qnaVO);
    }

    @GetMapping("/selectQna")
    public ResponseEntity<QnaVO> selectQna(@RequestBody QnaVO qnaVO) {
        qnaService.selectQna(qnaVO);

        return ResponseEntity.ok(qnaVO);
    }
}
