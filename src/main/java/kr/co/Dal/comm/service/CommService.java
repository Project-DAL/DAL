package kr.co.Dal.comm.service;

import kr.co.Dal.comm.mapper.CommMapper;
import kr.co.Dal.comm.model.CommVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommService {

    @Autowired
    private CommMapper commMapper;

    public void insertComm(CommVO comm) throws Exception {
        return commMapper.insertComm(CommVO comm);
    }
}
