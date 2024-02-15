package kr.co.Dal.user.config.auth;

// 시큐리티가 /login 주소 요청이 오면  낚아채서 로그인을 진행시킨다.
// 로그인을 진행이 완료가 되면 시큐리티 session을 만들어준다. (Security ContextHolder)
// 오브젝트 => Authentication 타입 객체
// Authentication 안에 User정보가 있어야 된다.
// User오브젝트타입 => UserDetails 타입 객체

// Security Session => Authentication => UserDetails(PrincipalDetails)

import kr.co.Dal.user.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class PrincipalDetails implements UserDetails, OAuth2User {

    private User user; //콤포지션
    private Map<String, Object> attributes;

    private String redirectUrl;


    //일반로그인
    public PrincipalDetails(User user){
        this.user = user;
    }

    //Oauth 로그인
    public PrincipalDetails(User user,Map<String,Object> attributes){
        this.user = user;
        this.attributes = attributes;
    }

    // 해당 User의 권한을 리턴하는 곳
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getUserRole();
            }
        });
        return collect;
    }

    @Override
    public String getPassword() {
        return user.getUserPw();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public int getUserId() {
        return user.getUserId();
    }

    public String getUserNick() {
        return user.getUserNick();
    }

    public String getUserLginId() {
        return user.getUserLginId();
    }

    public String getHp1() {
        return user.getUserHp().split("-")[0];
    }

    public String getHp2() {
        return user.getUserHp().split("-")[1];
    }

    public String getHp3() {
        return user.getUserHp().split("-")[2];
    }

    public String getZip() {
        return user.getZip();
    }

    public String getAddr1() {
        return user.getAddr1();
    }

    public String getAddr2() {
        return user.getAddr2();
    }

    public int getUserGrade() { return user.getUserGrade();}


    // 리소스 서버로 부터 받는 회원정보
    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    // User의 PrimaryKey
    @Override
    public String getName() {
        return user.getUserId()+"";
    }
}
