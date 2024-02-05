package kr.co.Dal.user.repository;

import kr.co.Dal.user.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

// CRUD 함수를 JpaRepository가 들고 있음.
// @Repository라는 어노테이션이 없어도 IOC되고, 이유는 JpaRepository를 상속했기 때문이다.
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // findBy규칙 -> Username문법
    // select * from user where  username =? 조회한다.
     User findByEmail(String email);

     User findByUsername(String username);


    // SELECT * FROM user WHERE provider = ?1 and providerId = ?2
    Optional<User> findByProviderAndProviderId(String provider, String providerId);

    // 비밀번호찾기 임시 비밀번호로 변경되는 쿼리문
    @Modifying
    @Transactional
    @Query(value = "UPDATE User u SET u.password = :password WHERE u.email = :email" )
    void updatePw(@Param("password") String password,@Param("email") String email);

}
