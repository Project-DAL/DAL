package kr.co.Dal.user.service;

import kr.co.Dal.user.model.User;
import kr.co.Dal.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private  UserRepository userRepository;

    public boolean userEmailCheck(String email) {

        User user = userRepository.findByEmail(email);
        if(user!=null) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean userUsernameCheck(String username) {

        User user = userRepository.findByUsername(username);
        if(user!=null) {
            return true;
        }
        else {
            return false;
        }
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
