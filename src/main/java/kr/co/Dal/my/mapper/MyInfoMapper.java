/*
    파일명        : MyInfoMapper.java
    최초 작성자    : 박제형
    최초 작성날짜   : 2024.01.25
*/

package kr.co.Dal.my.mapper;


import kr.co.Dal.my.model.MyInfoVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyInfoMapper {

    /* 회원 정보 */
    List<MyInfoVO> selectMyInfoList(MyInfoVO myInfoVO);


    /* 회원 정보 수정 */
    public void updateMyInfoList(MyInfoVO myInfoVO);




}
