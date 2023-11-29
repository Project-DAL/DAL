package kr.co.Dal.my.web;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MyController {

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
    @GetMapping("/my/myCoupon")
    public String mycoupon(){
        return "my/MyCoupon";
    }

    // My Info
    @GetMapping("/my/myInfo")
    public String myinfo(){
        return "my/MyInfo";
    }

    // My Order
    @GetMapping("/my/myOrder")
    public String myorder(){
        return "my/MyOrder";
    }

    // My Point
    @GetMapping("/my/myPoint")
    public String mypoint(){
        return "my/MyPoint";
    }

    // My Wish
    @GetMapping("/my/myWish")
    public String mywish(){
        return "my/MyWish";
    }

    // My Withdraw
    @GetMapping("/my/myWithdraw")
    public String mywithdraw(){
        return "my/MyWithdraw";
    }








}

