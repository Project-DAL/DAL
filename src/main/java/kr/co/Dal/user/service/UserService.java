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

    public boolean userEmailCheck(String userName,String userLginId) {
        Optional<User> user = userRepository.findByUserNameAndUserLginId(userName,userLginId);
        return user.isPresent(); // Java 8에서는 isPresent 사용
        // Java 11 이상에서는 return user.isPresent();
    }

    public boolean userUsernameCheck(String userName) {

        User user = userRepository.findByUserName(userName);
        if(user!=null) {
            return true;
        }
        else {
            return false;
        }
    }

    public User findUserByUsername(String userName) {
        return userRepository.findByUserName(userName);
    }

}
