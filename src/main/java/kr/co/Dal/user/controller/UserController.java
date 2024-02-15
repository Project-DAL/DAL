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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "exception", required = false) String exception,
                        Model model){
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "user/login";
    }

    @GetMapping("/termsForm")
    public String termsForm(){
        return "user/terms";
    }

    @GetMapping("/oauth2termsForm")
    public String oauth2termsForm(){
        return "user/sns/oauth2terms";
    }




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

    @PostMapping("/oauth2joinForm")
    public String oauth2joinForm(int agreeValueChk1, int agreeValueChk2, Model model) {
        // 여기에 회원가입 페이지로 넘어가기 전에 수행할 로직을 추가할 수 있습니다.
        System.out.println("Agree Value from Client: " + agreeValueChk1 + agreeValueChk2);

        // 모델에 값 추가
        model.addAttribute("agreeValueChk1", agreeValueChk1);
        model.addAttribute("agreeValueChk2", agreeValueChk2);

        // 회원가입 페이지로 이동
        return "user/sns/oauth2join";
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
        user.setUserType(1);
        user.setUserStts(1);
        user.setUserGrade(1);
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

    @PostMapping("/oauth2join")
    public String oauth2join(@RequestParam(name = "userName") String userName,
                             @RequestParam(name = "userNick") String userNick,
                            @RequestParam(name = "agreeValueChk1") int agreeValueChk1,
                             @RequestParam(name = "agreeValueChk2") int agreeValueChk2,
                            @RequestParam(name = "mobile1") String mobile1,
                             @RequestParam(name = "mobile2") String mobile2,
                             @RequestParam(name = "mobile3") String mobile3,
                             @RequestParam(name = "gender") int gender,
                             @RequestParam(name = "zip_code") String zip_code,
                             @RequestParam(name = "addr") String addr,
                             @RequestParam(name = "addr_dtl") String addr_dtl,
                             HttpSession session) {
        // 세션에서 OAuth2 정보 가져오기
        String provider = (String) session.getAttribute("oauth2Provider");
        String providerId = (String) session.getAttribute("oauth2ProviderId");

        // 세션 값이 정상적으로 설정되었는지 확인
        if (provider == null || providerId == null) {
            // 세션 값이 없으면 처리 로직 추가
            return "redirect:/oauth2joinForm"; // 또는 다른 경로로 리다이렉트
        }

        // 기존에 생성된 OAuth2 계정이 있는지 확인
        Optional<User> existingUserOptional = userRepository.findByProviderAndProviderId(provider, providerId);

        if (existingUserOptional.isPresent()) {
            // 기존 계정이 있다면 업데이트
            User existingUser = existingUserOptional.get();
            existingUser.setUserName(userName);
            existingUser.setUserNick(userNick);
            existingUser.setUserPrivYn(agreeValueChk1);
            existingUser.setUserLcYn(agreeValueChk2);
            String userHp = mobile1 + "-" + mobile2 + "-" + mobile3;
            existingUser.setUserHp(userHp);
            existingUser.setUserGender(gender);
            existingUser.setZip(zip_code);
            existingUser.setAddr1(addr);
            existingUser.setAddr2(addr_dtl);

            // Save the existing user
            userRepository.save(existingUser);
        }

        return "redirect:/";
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
