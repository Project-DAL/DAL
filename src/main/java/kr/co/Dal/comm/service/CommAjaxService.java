package kr.co.Dal.comm.service;

import kr.co.Dal.comm.mapper.CommMapper;
import kr.co.Dal.comm.model.CommVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommAjaxService {
    @Autowired
    private final CommMapper commMapper;

    public List<CommVO> commList(CommVO commVO) {
        return commMapper.commList(commVO);
    }

    public void commInsert(CommVO commVO) throws Exception {
        commMapper.commInsert(commVO);
    }
}
