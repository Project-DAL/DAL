/*
    파일명        : MyController
    최초 작성자    : 박제형
    최초 작성날짜   : 2023.11.28
*/

package kr.co.Dal.my.web;


import kr.co.Dal.my.model.*;
import kr.co.Dal.my.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MyController {

    private final MyCouponService myCouponService;
    private final MyPointService myPointService;
    private final MyBoardService myBoardService;
    private final MyInfoService myInfoService;
    private final MyWishService myWishService;


    // My Page Main
    @GetMapping("/my/MyPageMain")
    public String mypagemain() {

        return "my/MyPageMain";
    }

    // MY Board
    @GetMapping("/my/MyBoard")
    public String myboard(Model model, MyBoardVO myBoardVO, MyAnsVO myAnsVO){
        List<MyBoardVO> boardList = myBoardService.selectBoardList(myBoardVO);
        List<MyAnsVO> ansList = myBoardService.selectAnsList(myAnsVO);
        model.addAttribute("boardList", boardList);
        model.addAttribute("ansList", ansList);
        return "my/MyBoard";
    }


    // My Coupon
    @GetMapping("/my/MyCoupon")
    public String mycoupon(Model model, MyCouponVO mycouponVO){
        List<MyCouponVO> couponList = myCouponService.selectCouponList(mycouponVO);
        model.addAttribute("couponList", couponList);

        return "my/MyCoupon";
    }

    // My Info
    @GetMapping("/my/MyInfo")
    public String myinfo(Model model, MyInfoVO myInfoVO){
        List<MyInfoVO> infoList = myInfoService.selectMyInfoList(myInfoVO);
        model.addAttribute("infoList", infoList);
        return "my/MyInfo";
    }

    // My Order
    @GetMapping("/my/MyOrder")
    public String myorder(){

        return "my/MyOrder";
    }

    // My Point
    @GetMapping("/my/MyPoint")
    public String mypoint(Model model, MyPointVO myPointVO){
        List<MyPointVO> pointList = myPointService.selectPointList(myPointVO);
        model.addAttribute("pointList", pointList);
        return "my/MyPoint";
    }


    // My Wish
    @GetMapping("/my/MyWish")
    public String mywish(Model model, MyWishVO myWishVO){
        List<MyWishVO> wishList = myWishService.selectWishList(myWishVO);
        model.addAttribute("wishList", wishList);
        return "my/MyWish";
    }

    // My Withdraw
    @GetMapping("/my/MyWithdraw")
    public String mywithdraw(){

        return "my/MyWithdraw";
    }








}

