/*
    파일명        : MyController
    최초 작성자    : 박제형
    최초 작성날짜   : 2023.11.28
*/

package kr.co.Dal.my.web;


import kr.co.Dal.my.model.*;
import kr.co.Dal.my.service.*;
import kr.co.Dal.user.config.auth.PrincipalDetails;
import kr.co.Dal.util.SearchCondition;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MyController {

    private final MyCouponService myCouponService;
    private final MyPointService myPointService;
    private final MyBoardService myBoardService;
    private final MyAnsService myAnsService;
    private final MyInfoService myInfoService;
    private final MyWishService myWishService;
    private final MyWithdrawService myWithdrawService;
    private final MyMainService myMainService;
    private final MyRankService myRankService;


    // My Page Main
    @GetMapping("/my/MyPageMain")
    public String mypagemain(Model model, MyMainVO myMainVO) {

        List<MyMainVO> orderViewList = myMainService.orderViewList(myMainVO);

        int pointGross = myMainService.pointGross(myMainVO);
        int couponGross = myMainService.couponGross(myMainVO);
        int wishGross = myMainService.wishGross(myMainVO);
        String rankView = myMainService.rankView(myMainVO);

        model.addAttribute("pointGross", pointGross);
        model.addAttribute("couponGross", couponGross);
        model.addAttribute("wishGross", wishGross);
        model.addAttribute("rankView", rankView);
        model.addAttribute("orderViewList", orderViewList);

        return "my/MyPageMain";
    }

    // MY Board
    @GetMapping("/my/MyBoard")
    public String myboard(Model model,
                          @RequestParam Map map,
                          MyBoardVO myBoardVO,
                          @ModelAttribute SearchCondition sc,
                          @AuthenticationPrincipal PrincipalDetails principalDetails){

        // 검색어
        sc.setMap(map);


        int userId;
        if(principalDetails != null){
            userId = principalDetails.getUserId();
            sc.setUserId(userId);
            log.warn("userId1 : " + userId);
        }


        myBoardService.selectBoardList(model, myBoardVO, sc);


        return "my/MyBoard";
    }


    // MY Ans
    @GetMapping("/my/MyAns")
    public String myAns(Model model,
                        @RequestParam Map map,
                        MyAnsVO myAnsVO,
                        @ModelAttribute SearchCondition sc,
                        @AuthenticationPrincipal PrincipalDetails principalDetails){

        // 검색어
        sc.setMap(map);

        int userId;
        if(principalDetails != null){
            userId = principalDetails.getUserId();
            sc.setUserId(userId);
            log.warn("userId1 : " + userId);
        }


        myAnsService.selectAnsList(model, myAnsVO, sc);


        return "my/MyAns";
    }




    // My Point
    @GetMapping("/my/MyPoint")
    public String mypoint(Model model,
                          MyPointVO myPointVO,
                          @ModelAttribute SearchCondition sc,
                          @AuthenticationPrincipal PrincipalDetails principalDetails){

        int userId;
        if(principalDetails != null){
            userId = principalDetails.getUserId();
            sc.setUserId(userId);
            myPointVO.setUserId(userId);
            log.warn("userId1 : " + userId);
        }

        int pointGross = myPointService.pointGross(myPointVO);
        int pointGross30 = myPointService.pointGross30(myPointVO);

        model.addAttribute("pointGross", pointGross);
        model.addAttribute("pointGross30", pointGross30);

        myPointService.selectPointList(model, myPointVO, sc);

        return "my/MyPoint";
    }

    // My Coupon
    @GetMapping("/my/MyCoupon")
    public String mycoupon(Model model, MyCouponVO mycouponVO,
                           @AuthenticationPrincipal PrincipalDetails principalDetails){


        int userId;
        int userGrade;
        if(principalDetails != null){
            userId = principalDetails.getUserId();
            userGrade = principalDetails.getUserGrade();
            mycouponVO.setUserId(userId);
            mycouponVO.setUserGrade(userGrade);
            log.warn("userId1 : " + userId);
        }


        List<MyCouponVO> couponList = myCouponService.selectCouponList(mycouponVO);
        List<MyCouponVO> getCouponList = myCouponService.getCouponList(mycouponVO);


        model.addAttribute("couponList", couponList);
        model.addAttribute("getCouponList", getCouponList);


        return "my/MyCoupon";
    }



    // My Info
    @GetMapping("/my/MyInfo")
    public String myinfo(Model model, MyInfoVO myInfoVO, @AuthenticationPrincipal PrincipalDetails principalDetails){

        log.warn("myinfo");

        int userId;
        if(principalDetails != null){
            userId = principalDetails.getUserId();
            myInfoVO.setUserId(userId);
            log.warn("userId1 : " + userId);
        }

        List<MyInfoVO> infoList = myInfoService.selectMyInfoList(myInfoVO);

        String hp1 = infoList.get(0).getUserHp().substring(0,3);
        String hp2 = infoList.get(0).getUserHp().substring(4,8);
        String hp3 = infoList.get(0).getUserHp().substring(9);


        model.addAttribute("infoList", infoList);
        model.addAttribute("hp1", hp1);
        model.addAttribute("hp2", hp2);
        model.addAttribute("hp3", hp3);

        return "my/MyInfo";
    }



    // My Order
    @GetMapping("/my/MyOrder")
    public String myorder(){

        return "my/MyOrder";
    }




    // My Wish
    @GetMapping("/my/MyWish")
    public String mywish(Model model, MyWishVO myWishVO,
                         @AuthenticationPrincipal PrincipalDetails principalDetails){

        int userId;
        if(principalDetails != null){
            userId = principalDetails.getUserId();
            myWishVO.setUserId(userId);
            log.warn("userId1 : " + userId);
        }

        List<MyWishVO> wishList = myWishService.selectWishList(myWishVO);
        model.addAttribute("wishList", wishList);
        return "my/MyWish";
    }



    // My Withdraw
    @GetMapping("/my/MyWithdraw")
    public String mywithdraw(Model model, MyWithdrawVO myWithdrawVO, @AuthenticationPrincipal PrincipalDetails principalDetails){

        int userId;
        if(principalDetails != null){
            userId = principalDetails.getUserId();
            myWithdrawVO.setUserId(userId);
            log.warn("userId1 : " + userId);
        }

        int pointGross = myWithdrawService.pointGross(myWithdrawVO);
        int couponGross = myWithdrawService.couponGross(myWithdrawVO);

        model.addAttribute("pointGross", pointGross);
        model.addAttribute("couponGross", couponGross);

        return "my/MyWithdraw";
    }



    /* My rank*/
    @GetMapping("/my/MyRank")
    public String myrank(Model model, MyRankVO myRankVO, @AuthenticationPrincipal PrincipalDetails principalDetails){


        int userId;
        if(principalDetails != null){
            userId = principalDetails.getUserId();
            myRankVO.setUserId(userId);
            log.warn("userId1 : " + userId);
        }

        List<MyRankVO> rankList = myRankService.viewRank(myRankVO);
        model.addAttribute("rankList", rankList);
        return "my/MyRank";

    }


    /* My Ask */
    @GetMapping("/my/MyAsk")
    public String myask(){
        return "my/MyAsk";
    }


}