package kr.co.Dal.admin.web;

/*
File Name      : AdminController.js
Program Name   : 동기방식 관리자 컨트롤러
Draft Author   : 김진형
Draft Date     : 2024.01.17

Revision History
Ver.  Date          Revised By   Description
------------------------------------------------------------------------------------------------------------------------
0.1   2024.01.17    김진형       최초개발
----------------------------------------------------------------------------------------------------------------------->
*/

import kr.co.Dal.admin.model.AdminStoreVO;
import kr.co.Dal.admin.service.AdminAjaxService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminAjaxService adminAjaxService;

    /**
     * 관리자 메인 페이지
     */
    @GetMapping("/adminMain")
    public String adminMain() {
        return "admin/adminMain";
    }

    /**
     * 관리자 스토어 목록
     */
    @GetMapping("/store/storeList")
    public String storeList() {
        return "admin/store/storeList";
    }

    /**
     * 관리자 스토어 등록
     */
    @GetMapping("/store/storeWrite")
    public String storeWrite() {
        return "/admin/store/storeWrite";
    }


    /**
     * 관리자 스토어 주문 목록
     */
    @GetMapping("/store/storeOrder")
    public String storeOrder() {
        return "admin/store/storeOrder";
    }

    /**
     * 관리자 주류 목록
     */
    @GetMapping("/liquor/liquorList")
    public String liquorList() {
        return "admin/liquor/liquorList";
    }

    /**
     * 관리자 주류 등록
     */
    @GetMapping("/liquor/liquorWrite")
    public String liquorWrite() {
        return "admin/liquor/liquorWrite";
    }

    /**
     * 관리자 고객 목록
     */
    @GetMapping("/user/userList")
    public String userList() {
        return "admin/user/userList";
    }

    /**
     * 관리자 통계 메인
     */
    @GetMapping("/stas/stasMain")
    public String stasMain() {
        return "admin/stas/stasMain";
    }

    @GetMapping("/starter")
    public String starter() {
        return "starter";
    }

    /**
     * 상품 수정 페이지로 이동
     * @param prodId 상품 ID
     * @param model 모델 객체
     * @return 상품 수정 페이지 경로
     */
    @GetMapping("/storeModify/{prodId}")
    public String modifyProduct(@PathVariable("prodId") int prodId, Model model) {
        try {
            AdminStoreVO product = adminAjaxService.findProductById(prodId);
            model.addAttribute("product", product);
            return "/admin/store/storeModify"; // 상품 수정 페이지 경로
        } catch (Exception e) {
            log.error("Error loading product for modification: {}", prodId, e);
            return "redirect:/errorPage"; // 오류 발생 시 리다이렉트할 경로
        }
    }

}
