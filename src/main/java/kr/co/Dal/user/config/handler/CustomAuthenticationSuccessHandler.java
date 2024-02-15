package kr.co.Dal.user.config.handler;

import kr.co.Dal.user.config.auth.PrincipalDetails;
import kr.co.Dal.user.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        // OAuth2User에서 사용자 정보 추출
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();

        // UserDetails에서 사용자 정보 추출
        String username = principalDetails.getUsername(); // 예시: 사용자명
        String usernick = principalDetails.getUserNick(); // 예시: 사용자 닉네임

        String provider = principalDetails.getProvider();
        String providerid = principalDetails.getProviderId();

        System.out.println("Username: " + username);
        System.out.println("Usernick: " + usernick);

        // username 또는 usernick이 null인 경우 회원가입 폼으로 리다이렉트
        if (username == null || usernick == null) {

            // 세션에 OAuth2 정보 저장
            request.getSession().setAttribute("oauth2Provider", provider);
            request.getSession().setAttribute("oauth2ProviderId", providerid);


            // 회원가입 폼으로 리다이렉트
            getRedirectStrategy().sendRedirect(request, response, "/oauth2termsForm");
        } else {
            // 추가 로직 수행 (예: 로그인 성공 메시지 추가)
            // ...

            // 로그인으로 리다이렉트
            getRedirectStrategy().sendRedirect(request, response, "/");
        }
    }
}