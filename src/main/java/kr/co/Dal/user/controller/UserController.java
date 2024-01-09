package kr.co.Dal.user.controller;

import kr.co.Dal.user.model.User;
import kr.co.Dal.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

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


}
