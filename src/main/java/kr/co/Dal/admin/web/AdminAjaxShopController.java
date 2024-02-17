package kr.co.Dal.admin.web;

import kr.co.Dal.admin.service.AdminAjaxShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/admin")
@RequiredArgsConstructor
@ResponseBody
public class AdminAjaxShopController {

    private final AdminAjaxShopService adminAjaxShopService;

    /**
     * shop 목록 조회
     */
    @GetMapping("shopList")
    public ResponseEntity<Map<String, Object>> findShopList() {
        Map<String, Object> result = new HashMap<>();
        result.put("list", adminAjaxShopService.findShopList());
        return ResponseEntity.ok().body(result);
    }
}
