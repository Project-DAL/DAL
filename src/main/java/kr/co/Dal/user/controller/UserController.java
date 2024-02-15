package kr.co.Dal.user.controller;

import kr.co.Dal.user.config.auth.PrincipalDetails;
import kr.co.Dal.user.config.auth.PrincipalDetailsService;
import kr.co.Dal.user.model.MailDto;
import kr.co.Dal.user.model.User;
import kr.co.Dal.user.repository.UserRepository;
import kr.co.Dal.user.service.SendEmailService;
import kr.co.Dal.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private SendEmailService sendEmailService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;





    @GetMapping("/loginForm")
    public String login(){
        return "user/login";
    }

    @GetMapping("/termsForm")
    public String termsForm(){
        return "user/terms";
    }


//    @GetMapping("/joinForm")
//    public String joinForm(){
//        return "user/join";
//    }

    @PostMapping("/joinForm")
    public String handleJoinFormPost(int agreeValueChk1, int agreeValueChk2, Model model) {
        // 여기에 회원가입 페이지로 넘어가기 전에 수행할 로직을 추가할 수 있습니다.
        System.out.println("Agree Value from Client: " + agreeValueChk1 + agreeValueChk2);

        // 모델에 값 추가
        model.addAttribute("agreeValueChk1", agreeValueChk1);
        model.addAttribute("agreeValueChk2", agreeValueChk2);

        // 회원가입 페이지로 이동
        return "user/join";
    }


    @PostMapping("/join")
    public  String join(User user,
                        @RequestParam(name = "agreeValueChk1") int agreeValueChk1,
                        @RequestParam(name = "agreeValueChk2") int agreeValueChk2,
                        @RequestParam(name = "mobile1") String mobile1,
                        @RequestParam(name = "mobile2") String mobile2,
                        @RequestParam(name = "mobile3") String mobile3,
                        @RequestParam(name = "gender") int gender,
                        @RequestParam(name = "zip_code") String zip_code,
                        @RequestParam(name = "addr") String addr,
                        @RequestParam(name = "addr_dtl") String addr_dtl){
        System.out.println(user);
        user.setUserRole("ROLE_USER");
        user.setUserType(0);
        String rawPassword = user.getUserPw();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setUserPw(encPassword);
        user.setUserPrivYn(agreeValueChk1);
        user.setUserLcYn(agreeValueChk2);
        String userHp = mobile1 + "-" + mobile2 + "-" + mobile3;
        user.setUserHp(userHp);
        user.setUserGender(gender);
        user.setZip(zip_code);
        user.setAddr1(addr);
        user.setAddr2(addr_dtl);
        userRepository.save(user);// 회원가입 잘됨. 비밀번호: 1234=> 시큐리티로 로그인을 할 수 없음. 이유는 패스워드가 암호화가 안되있어서
        return "redirect:/loginForm";
    }

    @GetMapping("/findIdForm")
    public  String findIdForm(){
        return "user/findId";
    }

    @GetMapping("/findPwForm")
    public  String findPwForm(){
        return "user/findPw";
    }


    //Email과 name의 일치여부를 check하는 컨트롤러
    @GetMapping("/check/findPw")
    public @ResponseBody Map<String, Boolean> pw_find(String userName,String userLginId){
        Map<String,Boolean> json = new HashMap<>();
        boolean pwFindCheck = userService.userEmailCheck(userName,userLginId);

        System.out.println(pwFindCheck);
        json.put("check", pwFindCheck);
        return json;
    }

    @GetMapping("/check/findId")
    public @ResponseBody Map<String, Boolean> id_find(String userName){
        Map<String,Boolean> json = new HashMap<>();
        boolean idFindCheck = userService.userUsernameCheck(userName);

        System.out.println(idFindCheck);
        json.put("check", idFindCheck);
        return json;
    }
@PostMapping("/check/findId/successId")
    public ResponseEntity<?> getSuccessId(@RequestParam String userName) {
        User user = userService.findUserByUsername(userName);

        if (user != null) {
            Map<String, Object> result = new HashMap<>();
            result.put("userLginId", user.getUserLginId());
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    //등록된 이메일로 임시비밀번호를 발송하고 발송된 임시비밀번호로 사용자의 pw를 변경하는 컨트롤러
    @PostMapping("/check/findPw/sendEmail")
    public @ResponseBody void sendEmail(User user){
        MailDto dto = sendEmailService.createMailAndChangePassword(user);
        sendEmailService.mailSend(dto);

    }


}
