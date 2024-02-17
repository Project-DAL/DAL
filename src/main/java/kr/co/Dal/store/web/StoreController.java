package kr.co.Dal.store.web;

import kr.co.Dal.store.model.QnaVO;
import kr.co.Dal.store.model.StoreVO;
import kr.co.Dal.store.service.QnaService;
import kr.co.Dal.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;
    public final QnaService qnaService;

    // 스토어 메인 url 매핑
    @GetMapping("/storeMain")
    public String storeMain() {
        return "store/storeMain";
    }

    // 스토어 메인 목록 조회
    @GetMapping("/selectMainList")
    public ResponseEntity<Map<String, Object>> selectMainList(StoreVO storeVO){
        Map<String, Object> result = new HashMap<>();
        result.put("list", storeService.selectMainList(storeVO));
        return ResponseEntity.ok().body(result);
    }

    // 스토어 리스트 목록 조회
    @GetMapping("/storeList")
    public String storeList(Model model, StoreVO storeVO) {
        List<StoreVO> prodList = storeService.selectProdList(storeVO);
        model.addAttribute("prodList", prodList);
        return "store/storeList";
    }

    // 스토어 상세 조회
    @GetMapping("/storeView")
    public String storeView(Model model, StoreVO storeVO, QnaVO qnaVO) {
        StoreVO prod = storeService.selectProdOne(storeVO);
        List<QnaVO> qnaList = qnaService.selectQna(qnaVO);
        model.addAttribute("qnaList", qnaList);
        model.addAttribute("prod", prod);
        return "store/storeView";
    }

    @GetMapping("/storeCart")
    public String storeCart() {
        return "store/order/storeCart";
    }

    @GetMapping("/storeOrder")
    public String storeOrder() {
        return "store/order/storeOrder";
    }

    @GetMapping("/storeOrderResult")
    public String storeOrderResult() {
        return "store/order/storeOrderResult";
    }

}
