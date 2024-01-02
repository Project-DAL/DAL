package kr.co.Dal.user.config.oauth.provider;

import java.util.Map;

public class KaKaoUserInfo implements OAuth2UserInfo{

    private Map<String,Object> attributes;

    public KaKaoUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getProviderId() {
        Long id = (Long) attributes.get("id");
        if (id != null) {
            return String.valueOf(id); // Long 값을 String으로 변환하여 반환
        } else {
            return null; // 혹은 다른 기본값 반환
        }
    }


    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }
}
