package kr.co.Dal.admin.web;
/*
File Name      : AdminAjaxController.js
Program Name   : 비동기방식 관리자 컨트롤러
Draft Author   : 김진형
Draft Date     : 2024.01.17

Revision History
Ver.  Date          Revised By   Description
------------------------------------------------------------------------------------------------------------------------
0.1   2024.01.17    김진형       최초개발
----------------------------------------------------------------------------------------------------------------------->
*/

import kr.co.Dal.admin.model.AdminStoreVO;
import kr.co.Dal.admin.model.AdminVO;
import kr.co.Dal.admin.service.AdminAjaxService;
import kr.co.Dal.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Controller
@Slf4j
@RequestMapping("/admin")
@RequiredArgsConstructor
@ResponseBody
public class AdminAjaxController {

    private final AdminAjaxService adminAjaxService;

    /**
     * Store 목록 조회
     * @return
     */
    public ResponseEntity<Map<String, Object>> findStoreList() {
        Map<String, Object> result = new HashMap<>();
        result.put("storeList", adminAjaxService.findStoreList());
        return ResponseEntity.ok().body(result);
    }



}
