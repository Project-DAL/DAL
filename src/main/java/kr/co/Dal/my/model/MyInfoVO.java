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

    private int user_id;
    private String user_lgin_id;
    private String user_pw;
    private String user_nick;
    private int user_type;
    private String user_role;
    private int user_grade;
    private int user_point;
    private int user_stts;
    private int user_lc_yn;
    private int user_priv_yn;
    private String user_rdate;
    private String user_udate;
    private String user_wdate;
    private int user_gender;
    private String user_hp;
    private String user_email;
    private String zip;
    private String addr1;
    private String addr2;

}
