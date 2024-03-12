package kr.co.Dal.user.config.auth;

import kr.co.Dal.user.model.User;
import kr.co.Dal.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


// 시큐리티 설정에서 loginProcessingUrl("/login");
// /login 요청이 오면 자동으로 UserDetailsService 타입으로 IoC되어 있는 loadUserByUsername 함수가 실행
@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // 시큐리티 session = Authentication = UserDetails
    @Override
    public UserDetails loadUserByUsername(String userLoginId) throws UsernameNotFoundException {
        Optional<User> userEntity = userRepository.findByUserLginId(userLoginId);
        if (userEntity.isPresent()) {
            return new PrincipalDetails(userEntity.get());
        }
        return null;
    }

}
