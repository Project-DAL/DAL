/*
    파일명        : MyPointService
    최초 작성자    : 박제형
    최초 작성날짜   : 2024.01.22
*/

package kr.co.Dal.my.service;

import kr.co.Dal.my.mapper.MyPointMapper;
import kr.co.Dal.my.model.MyPointVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPointService {

    private final MyPointMapper myPointMapper;


    /* 포인트 리스트 조회 */
    public List<MyPointVO> selectPointList(MyPointVO myPointVO){
        return myPointMapper.selectPointList(myPointVO);
    }


    /* 사용가능한 적립금*/
    public int pointGross(MyPointVO myPointVO){
        return myPointMapper.pointGross(myPointVO);
    }

    /* 30일 이내 소멸 예정 적립금 */
    public int pointGross30(MyPointVO myPointVO){
        return myPointMapper.pointGross30(myPointVO);
    }

}