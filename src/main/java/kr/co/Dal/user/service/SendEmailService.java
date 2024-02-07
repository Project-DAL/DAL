package kr.co.Dal.user.service;

import kr.co.Dal.user.model.MailDto;
import kr.co.Dal.user.model.User;
import kr.co.Dal.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SendEmailService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "aaa@gmail.com";


    public MailDto createMailAndChangePassword(User user) {
        String str = getTempPassword();
        MailDto dto = new MailDto();
        dto.setAddress(user.getUserLginId());
        dto.setTitle(user.getUserName() + "님의 DAL 임시비밀번호 안내 이메일 입니다.");
        dto.setMessage("안녕하세요.  임시비밀번호 안내 관련 이메일 입니다." + "[" + user.getUserName() + "]" + "님의 임시 비밀번호는 "+str+"입니다.");
       updatePassword(str, user);
        return dto;
    }

    public void updatePassword(String str, User user) {
        String password = bCryptPasswordEncoder.encode(str);
        String email = user.getUserLginId(); // 이 부분 수정
        userRepository.updatePw(password, email);
    }





    public String getTempPassword() {
        char[] charSet = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

        String str = "";

        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
    }


    public void mailSend(MailDto mailDto) {
        System.out.println("이멜 전송 완료!");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailDto.getAddress());
        message.setFrom(SendEmailService.FROM_ADDRESS);
        message.setSubject(mailDto.getTitle());
        message.setText(mailDto.getMessage());

        mailSender.send(message);
    }
}

