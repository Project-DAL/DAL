package kr.co.Dal.user.config.handler;

import com.google.api.client.util.Value;
import kr.co.Dal.user.config.auth.PrincipalDetails;
import kr.co.Dal.user.jwt.service.JwtService;
import kr.co.Dal.user.model.User;
import kr.co.Dal.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Slf4j
@Component
@RequiredArgsConstructor
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final JwtService jwtService;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();

        String provider = principalDetails.getProvider();
        String providerid = principalDetails.getProviderId();

        log.info("OAuth2 Login 성공!");

        try {
            // User의 Role이 GUEST일 경우 처음 요청한 회원이므로 회원가입 페이지로 리다이렉트
            if ("ROLE_GUEST".equals(principalDetails.getUserRole())) {
                log.info("Creating AccessToken");
                String accessToken = jwtService.createAccessToken(principalDetails.getUserLginId());
                log.info("AccessToken created: {}", accessToken);

                log.info("Adding AccessToken to headers");
                response.addHeader(jwtService.getAccessHeader(), "Bearer " + accessToken);
                log.info("Bearer Token added to headers");

                request.getSession().setAttribute("oauth2Provider", provider);
                request.getSession().setAttribute("oauth2ProviderId", providerid);
                jwtService.sendAccessAndRefreshToken(response, accessToken, null);

                // AccessToken, RefreshToken 헤더를 추가한 이후에 updateRefreshToken 메소드를 호출
                jwtService.updateRefreshToken(principalDetails.getUserLginId(), jwtService.createRefreshToken());

                response.sendRedirect("/oauth2termsForm");

            } else {
                // 로그인에 성공한 경우 access, refresh 토큰 생성
                loginSuccess(response, principalDetails);
            }

        } catch (Exception e) {
            throw e;
        }
    }

    // TODO : 소셜 로그인 시에도 무조건 토큰 생성하지 말고 JWT 인증 필터처럼 RefreshToken 유/무에 따라 다르게 처리해보기
    private void loginSuccess(HttpServletResponse response, PrincipalDetails principalDetails) throws IOException {
        String accessToken = jwtService.createAccessToken(principalDetails.getUserLginId());
        String refreshToken = jwtService.createRefreshToken();
        response.addHeader(jwtService.getAccessHeader(), "Bearer " + accessToken);
        response.addHeader(jwtService.getRefreshHeader(), "Bearer " + refreshToken);

        jwtService.sendAccessAndRefreshToken(response, accessToken, refreshToken);
        jwtService.updateRefreshToken(principalDetails.getUserLginId(), refreshToken);

        if ("ROLE_USER".equals(principalDetails.getUserRole())) {
            response.sendRedirect("/");
        }
    }

}