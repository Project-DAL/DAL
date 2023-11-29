package kr.co.Dal.comm.service;

import kr.co.Dal.comm.model.CommVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommService {

    // ex
    /*
    public void updateComm(CommVO commVO) {
        CommVO comm = CommVO.builder().bardCn(commVO.getBardCn()).build();
        commVO.setBardRdate(comm.getBardRdate());
    }
    */
}
