package kr.co.Dal.my.service;


import kr.co.Dal.my.mapper.MyMainMapper;
import kr.co.Dal.my.model.MyMainVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyMainService {


    private final MyMainMapper myMainMapper;


    /* 쿠폰 개수 */
    public int couponGross(MyMainVO myMainVO){
        return myMainMapper.couponGross(myMainVO);
    }

    /* 사용가능한 적립금 */
    public int pointGross(MyMainVO myMainVO){
        return myMainMapper.pointGross(myMainVO);
    }

    /* 위시리스트 개수 */
    public int wishGross(MyMainVO myMainVO){
        return myMainMapper.wishGross(myMainVO);
    }

    /* 등급 보기 */
    public String rankView(MyMainVO myMainVO){
        return myMainMapper.rankView(myMainVO);
    }

    /* 주문 배송 목록 */
    public List<MyMainVO> orderViewList(MyMainVO myMainVO) {
        log.warn("myMainVO: " + myMainVO);
        return myMainMapper.orderViewList(myMainVO);
    }



}
