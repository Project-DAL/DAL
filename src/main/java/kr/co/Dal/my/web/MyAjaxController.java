/*
    파일명        : MyBoardService
    최초 작성자    : 박제형
    최초 작성날짜   : 2024.01.22
*/


package kr.co.Dal.my.web;


import kr.co.Dal.my.model.MyAnsVO;
import kr.co.Dal.my.model.MyBoardVO;
import kr.co.Dal.my.model.MyWishVO;
import kr.co.Dal.my.service.MyBoardService;
import kr.co.Dal.my.service.MyWishService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Slf4j
@Controller
@RequiredArgsConstructor
public class MyAjaxController {

    private final MyBoardService myBoardService;
    private final MyWishService myWishService;


    /* 내 게시글 삭제 */
    @PostMapping("/my/MyBoard/deleteBoard")
    public ResponseEntity<MyBoardVO> deleteBoard(@RequestBody MyBoardVO myBoardVO) {

        myBoardService.deleteBoard(myBoardVO);
        return ResponseEntity.ok(myBoardVO);
    }


    /* 내 댓글 삭제 */
    @PostMapping("/my/MyBoard/deleteAns")
    public ResponseEntity<MyAnsVO> deleteAns(@RequestBody MyAnsVO myAnsVO) {

        myBoardService.deleteAns(myAnsVO);
        return ResponseEntity.ok(myAnsVO);
    }


    /* 위시리스트 삭제 */
    @PostMapping("/my/MyWish/deleteWish")
    public ResponseEntity<MyWishVO> deleteWish(@RequestBody MyWishVO myWishVO) {

        myWishService.deleteWish(myWishVO);
        return ResponseEntity.ok(myWishVO);

    }

}