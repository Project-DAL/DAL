package kr.co.Dal.admin.web;

import kr.co.Dal.admin.model.AdminStoreVO;
import kr.co.Dal.admin.model.LiquorVO;
import kr.co.Dal.admin.service.AdminAjaxLiquorService;
import kr.co.Dal.admin.service.AdminAjaxService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/admin")
@RequiredArgsConstructor
@ResponseBody
public class AdminAjaxLiquorController {

    private final AdminAjaxLiquorService adminAjaxLiquorService;

    /**
     * Liquor 목록 조회
     * @return
     */
    @GetMapping("/liquorList")
    public ResponseEntity<Map<String, Object>> findStoreList() {
        Map<String, Object> result = new HashMap<>();
        result.put("list", adminAjaxLiquorService.findLiquorList());
        // result에 "list" 키로 저장된 값을 로그로 출력
        log.warn("Store List: {}", result.get("list"));
        return ResponseEntity.ok().body(result);
    }

    /**
     * category 별 조회
     * @param liqType
     * @return
     */
    @GetMapping("/liquorCategoryList")
    public ResponseEntity<Map<String, Object>> findLiquorCategoryList(String liqType) {
        Map<String, Object> result = new HashMap<>();
        result.put("list", adminAjaxLiquorService.findLiquorCategoryList(liqType));
        // result에 "list" 키로 저장된 값을 로그로 출력
        log.warn("Store List: {}", result.get("list"));
        return ResponseEntity.ok().body(result);
    }

    /**
     * 상품 정보 등록
     */
    @PostMapping("/liquorInsert")
    public ResponseEntity<Map<String, Object>> liquorInsert(@RequestBody LiquorVO liquorVO) {
        Map<String, Object> result = new HashMap<>();
        try {
            // 상품 정보 등록 로직 실행
            int liqId = adminAjaxLiquorService.insertLiquor(liquorVO);
            result.put("liqId", liqId);
            result.put("status", "success");
            log.info("Product inserted successfully: {}", liqId);
        } catch (Exception e) {
            log.error("Error inserting product", e);
            result.put("status", "error");
        }
        return ResponseEntity.ok(result);
    }

    /**
     * 주류 이미지 파일 업로드
     */
    @PostMapping("/liquorUploadFiles")
    public ResponseEntity<Map<String, Object>> liquorUploadFiles(
            @RequestParam("liqId") int liqId,
            @RequestParam("files") List<MultipartFile> files) {
        Map<String, Object> result = new HashMap<>();
        log.warn("liquorUploadFiles Controller");
        log.warn("여기확인" + files.toString());
        try {
            log.warn("try");
            // 파일 업로드 로직 실행
            adminAjaxLiquorService.uploadProductFiles(liqId, files);
            result.put("status", "success");
            log.warn("Files uploaded successfully for product: {}", liqId);
        } catch (Exception e) {
            log.warn("error");
            log.warn("Error uploading files for product: {}", liqId, e);
            result.put("status", "error");
        }
        return ResponseEntity.ok(result);
    }

    /**
     * liquor 삭제
     * @param liqId liquor ID
     * @return ResponseEntity 상품 삭제 결과를 담은 응답 엔티티
     */
    @DeleteMapping("/deleteLiquor/{liqId}")
    public ResponseEntity<Map<String, Object>> deleteLiquor(@PathVariable int liqId) {
        Map<String, Object> result = new HashMap<>();
        try {
            // 상품 삭제 로직 실행
            adminAjaxLiquorService.deleteLiquorAndRelatedFiles(liqId);
            result.put("status", "success");
            log.info("Product deleted successfully: {}", liqId);
        } catch (Exception e) {
            log.error("Error deleting product: {}", liqId, e);
            result.put("status", "error");
        }
        return ResponseEntity.ok(result);
    }

}
