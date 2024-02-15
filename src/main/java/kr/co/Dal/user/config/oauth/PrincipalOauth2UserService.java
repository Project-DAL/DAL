package kr.co.Dal.user.config.oauth;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import kr.co.Dal.user.config.auth.PrincipalDetails;
import kr.co.Dal.user.config.oauth.provider.KaKaoUserInfo;
import kr.co.Dal.user.config.oauth.provider.GoogleUserInfo;
import kr.co.Dal.user.config.oauth.provider.NaverUserInfo;
import kr.co.Dal.user.config.oauth.provider.OAuth2UserInfo;
import kr.co.Dal.user.model.User;
import kr.co.Dal.user.repository.UserRepository;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository userRepository;


    // 구글로 부터 받은 userRequest 데이터에 대한 후처리되는 함수
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest); // google의 회원 프로필 조회

        // code를 통해 구성한 정보
        System.out.println("userRequest clientRegistration : " + userRequest.getClientRegistration());
        // token을 통해 응답받은 회원정보
        System.out.println("oAuth2User : " + oAuth2User);

        return processOAuth2User(userRequest, oAuth2User);
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {

        // Attribute를 파싱해서 공통 객체로 묶는다. 관리가 편함.
        OAuth2UserInfo oAuth2UserInfo = null;
        if (userRequest.getClientRegistration().getRegistrationId().equals("google")) {
            System.out.println("구글 로그인 요청~~");
            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        } else if (userRequest.getClientRegistration().getRegistrationId().equals("kakao")) {
            System.out.println("카카오 로그인 요청~~");
            oAuth2UserInfo = new KaKaoUserInfo(oAuth2User.getAttributes());
        } else if (userRequest.getClientRegistration().getRegistrationId().equals("naver")){
            System.out.println("네이버 로그인 요청~~");
            oAuth2UserInfo = new NaverUserInfo((Map)oAuth2User.getAttributes().get("response"));
        } else {
            System.out.println("우리는 구글과 네이버와 카카오만 지원해요");
        }

        //System.out.println("oAuth2UserInfo.getProvider() : " + oAuth2UserInfo.getProvider());
        //System.out.println("oAuth2UserInfo.getProviderId() : " + oAuth2UserInfo.getProviderId());
        Optional<User> userOptional =
                userRepository.findByProviderAndProviderId(oAuth2UserInfo.getProvider(), oAuth2UserInfo.getProviderId());

        User user;
        if (userOptional.isPresent()) {
            user = userOptional.get();
            // user가 존재하면 update 해주기
            user.setUserLginId(oAuth2UserInfo.getEmail());


            userRepository.save(user);
        } else {
            // user의 패스워드가 null이기 때문에 OAuth 유저는 일반적인 로그인을 할 수 없음.
            user = User.builder()
                    .userName(oAuth2UserInfo.getProvider() + "_" + oAuth2UserInfo.getProviderId())
                    .userLginId(oAuth2UserInfo.getEmail())
                    .userType(2)
                    .userStts(1)
                    .userGrade(1)
                    .userRole("ROLE_USER")
                    .provider(oAuth2UserInfo.getProvider())
                    .providerId(oAuth2UserInfo.getProviderId())
                    .build();
            userRepository.save(user);
        }
// user_stts가 0인 경우에는 로그인을 막는다.
        if (user.getUserStts() == 0) {
            throw new DisabledException("사용자가 비활성화되어 로그인할 수 없습니다: " + user.getUserLginId());
        }
        return new PrincipalDetails(user, oAuth2User.getAttributes());
    }
}
