package kr.co.Dal.preferTest.service;

import kr.co.Dal.preferTest.mapper.PreferTestMapper;
import kr.co.Dal.preferTest.model.SrvQaVO;
import kr.co.Dal.preferTest.model.SrvResVO;
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
     * 술 MBTI 문제
     */
    public List<SrvQaVO> preferList(Model model, SrvQaVO srvQaVO) {

        // model 전송
        List<SrvQaVO> preferList = preferTestMapper.preferList(srvQaVO);

        model.addAttribute("preferList", preferList);

        return preferList;
    }

    /**
     * 술 MBTI 결과
     */
    public SrvResVO preferResult(Model model, SrvResVO srvResVO) {

        // model 전송
        SrvResVO preferResult = preferTestMapper.preferResult(srvResVO);

        model.addAttribute("preferResult", preferResult);

        return preferResult;
    }


}
