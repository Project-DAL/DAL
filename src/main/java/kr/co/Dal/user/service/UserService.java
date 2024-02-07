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

    public boolean userEmailCheck(String userLginId) {

        User user = userRepository.findByUserLginId(userLginId);
        if(user!=null) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean userUsernameCheck(String userNick) {

        User user = userRepository.findByUserNick(userNick);
        if(user!=null) {
            return true;
        }
        else {
            return false;
        }
    }

    public User findUserByUsername(String userNick) {
        return userRepository.findByUserNick(userNick);
    }

}
