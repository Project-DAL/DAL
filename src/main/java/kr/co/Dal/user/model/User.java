package kr.co.Dal.user.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

// ORM - Object Relation Mapping

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "user_lgin_id")
    private String userLginId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_pw")
    private String userPw;

    @Column(name = "user_nick")
    private String userNick;

    @Column(name = "user_type")
    private int userType;

    @Column(name = "user_grade")
    private int userGrade;

    @Column(name = "user_point")
    private int userPoint;

    @Column(name = "user_stts")
    private int userStts;

    @Column(name = "user_lc_yn")
    private int userLcYn;

    @Column(name = "user_priv_yn")
    private int userPrivYn;


    @Column(name = "user_rdate")
    @CreationTimestamp
    private Timestamp  userRdate;

    @Column(name = "user_udate")
    private LocalDate userUdate;

    @Column(name = "user_wdate")
    private LocalDate userWdate;

    @Column(name = "user_gender")
    private int userGender;

    @Column(name = "user_hp")
    private String userHp;

    private String zip;
    private String addr1;
    private String addr2;

//    private String email;
    @Column(name="user_role")
    private String userRole; //ROLE_USER, ROLE_ADMIN
    // OAuth를 위해 구성한 추가 필드 2개
    private String provider;
    private String providerId;

}