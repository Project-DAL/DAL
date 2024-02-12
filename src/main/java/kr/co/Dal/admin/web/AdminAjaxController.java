package kr.co.Dal.admin.web;
/*
File Name      : AdminAjaxController.js
Program Name   : 비동기방식 관리자 컨트롤러
Draft Author   : 김진형
Draft Date     : 2024.01.17

Revision History
Ver.  Date          Revised By   Description
————————————————————————————————————————————————————————————
0.1   2024.01.17    김진형       최초개발
———————————————————————————————————————————————————————————>
*/

import kr.co.Dal.admin.model.AdminStoreVO;
import kr.co.Dal.admin.model.AdminVO;
import kr.co.Dal.admin.service.AdminAjaxService;
import kr.co.Dal.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
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
    @GetMapping("/storeList")
    public ResponseEntity<Map<String, Object>> findStoreList() {
        Map<String, Object> result = new HashMap<>();
        result.put("list", adminAjaxService.findStoreList());
        // result에 "list" 키로 저장된 값을 로그로 출력
        log.warn("Store List: {}", result.get("list"));
        return ResponseEntity.ok().body(result);
    }

    /**
     * category 별 조회
     * @param prodType
     * @return
     */
    @GetMapping("/storeCategoryList")
    public ResponseEntity<Map<String, Object>> findStoreCategoryList(String prodType) {
        Map<String, Object> result = new HashMap<>();
        result.put("list", adminAjaxService.findStoreCategoryList(prodType));
        // result에 "list" 키로 저장된 값을 로그로 출력
        log.warn("Store List: {}", result.get("list"));
        return ResponseEntity.ok().body(result);
    }

    /**
     * 상품 정보 등록
     */
    @PostMapping("/storeInsert")
    public ResponseEntity<Map<String, Object>> storeInsert(@RequestBody AdminStoreVO adminStoreVO) {
        Map<String, Object> result = new HashMap<>();
        log.warn("여기확인" + adminStoreVO.getProdTit());
        try {
            // 상품 정보 등록 로직 실행
            int prodId = adminAjaxService.insertStore(adminStoreVO);
            result.put("prodId", prodId);
            result.put("status", "success");
            log.info("Product inserted successfully: {}", prodId);
        } catch (Exception e) {
            log.error("Error inserting product", e);
            result.put("status", "error");
        }
        return ResponseEntity.ok(result);
    }

    /**
     * 상품 이미지 파일 업로드
     */
    @PostMapping("/storeUploadFiles")
    public ResponseEntity<Map<String, Object>> storeUploadFiles(
            @RequestParam("prodId") int prodId,
            @RequestParam("files") List<MultipartFile> files) {
        Map<String, Object> result = new HashMap<>();
        log.warn("여기확인" + files.toString());
        try {
            // 파일 업로드 로직 실행
            adminAjaxService.uploadProductFiles(prodId, files);
            result.put("status", "success");
            log.info("Files uploaded successfully for product: {}", prodId);
        } catch (Exception e) {
            log.error("Error uploading files for product: {}", prodId, e);
            result.put("status", "error");
        }
        return ResponseEntity.ok(result);
    }

    /**
     * 상품 삭제
     * @param prodId 상품 ID
     * @return ResponseEntity 상품 삭제 결과를 담은 응답 엔티티
     */
    @DeleteMapping("/deleteProduct/{prodId}")
    public ResponseEntity<Map<String, Object>> deleteStore(@PathVariable int prodId) {
        Map<String, Object> result = new HashMap<>();
        try {
            // 상품 삭제 로직 실행
            adminAjaxService.deleteStoreAndRelatedFiles(prodId);
            result.put("status", "success");
            log.info("Product deleted successfully: {}", prodId);
        } catch (Exception e) {
            log.error("Error deleting product: {}", prodId, e);
            result.put("status", "error");
        }
        return ResponseEntity.ok(result);
    }

}
