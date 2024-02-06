/*
    파일명        : MyBoardService
    최초 작성자    : 박제형
    최초 작성날짜   : 2024.01.22
*/


package kr.co.Dal.my.web;


import kr.co.Dal.my.model.*;
import kr.co.Dal.my.service.*;
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
    private final MyWithdrawService myWithdrawService;
    private final MyInfoService myInfoService;
    private final MyCouponService myCouponService;


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



    /* 회원 탈퇴 */
    @PostMapping("/my/MyWithdraw/deleteUser")
    public ResponseEntity<MyWithdrawVO> myWithdraw(@RequestBody MyWithdrawVO myWithdrawVO) {

        /* user_id 불러오고 myWithdrawVO에 저장*/

        myWithdrawService.myWithdraw(myWithdrawVO);
        return ResponseEntity.ok(myWithdrawVO);
    }



    /* 회원 정보 수정 */
    @PostMapping("/my/MyInfo/update")
    public ResponseEntity<MyInfoVO> updateMyInfoList(@RequestBody MyInfoVO myInfoVO) {


        /* user_id 불러오고 myInfoVO에 저장*/


        myInfoService.updateMyInfoList(myInfoVO);
        return ResponseEntity.ok(myInfoVO);
    }


    /* 쿠폰 받기 버튼 */
    @PostMapping("/my/MyCoupon/insert")
    public ResponseEntity<MyCouponVO> insertCoupon(@RequestBody MyCouponVO mycouponVO){
        log.warn("Received cpId: {}" , mycouponVO.getCp_id());

        myCouponService.insertCoupon(mycouponVO);
        return ResponseEntity.ok(mycouponVO);
    }

}