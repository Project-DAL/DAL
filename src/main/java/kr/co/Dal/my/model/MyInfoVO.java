/*
    파일명        : MyInfoVO
    최초 작성자    : 박제형
    최초 작성날짜   : 2024.01.25
*/

package kr.co.Dal.my.model;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MyInfoVO {

    private int userId;
    private String userLginId;
    private String userPw;
    private String userNick;
    private int userType;
    private String userRole;
    private int userGrade;
    private int userPoint;
    private int userStts;
    private int userLcYn;
    private int userPrivYn;
    private String userRdate;
    private String userUdate;
    private String userWdate;
    private int userGender;
    private String userHp;
    private String userEmail;
    private String zip;
    private String addr1;
    private String addr2;
    private String provider;
    private String providerId;
    private String userName;


}
