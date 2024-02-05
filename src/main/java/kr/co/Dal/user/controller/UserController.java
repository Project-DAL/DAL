package kr.co.Dal.user.controller;

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

    @GetMapping("/joinForm")
    public String joinForm(){
        return "user/join";
    }

    @PostMapping("/join")
    public  String join(User user){
        System.out.println(user);
        user.setRole("ROLE_USER");
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
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
    public @ResponseBody Map<String, Boolean> pw_find(String email){
        Map<String,Boolean> json = new HashMap<>();
        boolean pwFindCheck = userService.userEmailCheck(email);

        System.out.println(pwFindCheck);
        json.put("check", pwFindCheck);
        return json;
    }

    @GetMapping("/check/findId")
    public @ResponseBody Map<String, Boolean> id_find(String username){
        Map<String,Boolean> json = new HashMap<>();
        boolean idFindCheck = userService.userUsernameCheck(username);

        System.out.println(idFindCheck);
        json.put("check", idFindCheck);
        return json;
    }
@PostMapping("/check/findId/successId")
    public ResponseEntity<?> getSuccessId(@RequestParam String username) {
        User user = userService.findUserByUsername(username);

        if (user != null) {
            Map<String, Object> result = new HashMap<>();
            result.put("email", user.getEmail());
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
