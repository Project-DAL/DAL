package kr.co.Dal.admin.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
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


}
