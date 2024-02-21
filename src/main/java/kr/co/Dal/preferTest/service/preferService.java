package kr.co.Dal.preferTest.service;

import kr.co.Dal.comm.mapper.CommMapper;
import kr.co.Dal.comm.model.CommVO;
import kr.co.Dal.preferTest.mapper.PreferTestMapper;
import kr.co.Dal.preferTest.model.SrvQaVO;
import kr.co.Dal.util.PageHandler;
import kr.co.Dal.util.SearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PreferService {
    @Autowired
    private final PreferTestMapper preferTestMapper;

    /**
     * 게시판 페이지, 목록
     */
    public List<SrvQaVO> preferList(Model model,  SrvQaVO srvQaVO) {

        // model 전송
        List<SrvQaVO> preferList = preferTestMapper.preferList(srvQaVO);

        model.addAttribute("preferList", preferList);

        return preferList;
    }

}
