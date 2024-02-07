package kr.co.Dal.my.service;


import kr.co.Dal.my.mapper.MyRankMapper;
import kr.co.Dal.my.model.MyRankVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyRankService {

    private final MyRankMapper myRankMapper;


    /* 랭크 관련 */
    public List<MyRankVO> viewRank(MyRankVO myRankvo){
        return myRankMapper.viewRank(myRankvo);
    }





}
