package kr.co.Dal.user.config;

import kr.co.Dal.user.config.oauth.PrincipalOauth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// 1.코드받기(인증), 2.액세스토큰(권한), 3.사용자프로필정보를 가져오고 4.그 정보를 토대로 회원가입을  자동으로 진행시키기도 함

@Configuration
@EnableWebSecurity // 스프링시큐리티 필터가 스프링 필터체인에 등록이 됩니다.
public class SecurityConfig {

    @Autowired
    private PrincipalOauth2UserService principalOauth2UserService;
    // 해당 메서드의 리턴되는 오브젝트를 IOC로 등록해준다.
    @Bean
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/user/**","/common/**","/main/**","/test/**").permitAll()
                .antMatchers("/joinForm","/loginForm","/auth/**", "/oauth2/**","/my/**","/join").permitAll() // 회원가입 접근 가능
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/loginForm")
                .loginProcessingUrl("/login") // login 주소가 호출이 되면 시큐리티가 낚아채서 대신 로그인을 진행해준다.
                .usernameParameter("email")
                .defaultSuccessUrl("/index")
                .and()
                .oauth2Login()
                .loginPage("/loginForm")
                .userInfoEndpoint()
                .userService(principalOauth2UserService);
        // 구글 로그인이 완료된 뒤의 후처리가 필요함. Tip. 코드X(액세스토큰+사용자프로필정보 O)

        return http.build();
    }
}
