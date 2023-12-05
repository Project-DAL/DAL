/*
    파일명        : MyController
    최초 작성자    : 박제형
    최초 작성날짜   : 2023.11.28
*/

package kr.co.Dal.my.web;


import kr.co.Dal.my.model.MyCouponVO;
import kr.co.Dal.my.service.MyCouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MyController {

    private final MyCouponService myCouponService;

    // My Page Main
    @GetMapping("/my/MyPageMain")
    public String mypagemain() {

        return "my/MyPageMain";
    }

    // MY Board
    @GetMapping("/my/MyBoard")
    public String myboard(){

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
    public String myinfo(){

        return "my/MyInfo";
    }

    // My Order
    @GetMapping("/my/MyOrder")
    public String myorder(){

        return "my/MyOrder";
    }

    // My Point
    @GetMapping("/my/MyPoint")
    public String mypoint(){

        return "my/MyPoint";
    }

    // My Wish
    @GetMapping("/my/MyWish")
    public String mywish(){

        return "my/MyWish";
    }

    // My Withdraw
    @GetMapping("/my/MyWithdraw")
    public String mywithdraw(){

        return "my/MyWithdraw";
    }








}

