/*
    파일명        : MyInfoService
    최초 작성자    : 박제형
    최초 작성날짜   : 2024.01.25
*/


package kr.co.Dal.my.service;


import kr.co.Dal.my.mapper.MyInfoMapper;
import kr.co.Dal.my.model.MyInfoVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyInfoService {

    private final MyInfoMapper myInfoMapper;


    /* 회원 정보 */
    public List<MyInfoVO> selectMyInfoList(MyInfoVO myInfoVO){
        return myInfoMapper.selectMyInfoList(myInfoVO);
    }



    /* 회원 정보 수정 */
    public void updateMyInfoList(MyInfoVO myInfoVO){
        myInfoMapper.updateMyInfoList(myInfoVO);
    }


}
