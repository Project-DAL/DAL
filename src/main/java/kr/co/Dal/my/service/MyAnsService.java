package kr.co.Dal.my.service;


import groovy.util.logging.Slf4j;
import kr.co.Dal.my.mapper.MyAnsMapper;
import kr.co.Dal.my.mapper.MyBoardMapper;
import kr.co.Dal.my.model.MyAnsVO;
import kr.co.Dal.util.PageHandler;
import kr.co.Dal.util.SearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyAnsService {

    private final MyAnsMapper myAnsMapper;




    /* 내가 쓴 댓글 조회 */
    public List<MyAnsVO> selectAnsList(Model model, MyAnsVO myAnsVO, SearchCondition sc){

        // pagination
        int totalCnt = myAnsMapper.countAns(sc);


        int totalPage = (int)Math.ceil(totalCnt / (double)sc.getPageSize());
        if(sc.getPage() > totalPage) sc.setPage(totalPage);


        // 한 페이지에 보일 건수
        int naviSize = 10;

        PageHandler pageHandler = new PageHandler(totalCnt, sc, naviSize);

        // model 전송
        List<MyAnsVO> ansList = myAnsMapper.selectAnsList(sc);

        model.addAttribute("ansList", ansList);
        model.addAttribute("phAns", pageHandler);


        return ansList;
    }



    /* 댓글 삭제 */
    public void deleteAns(MyAnsVO myAnsVO) {


        for (int i = 0; i < myAnsVO.getAnsidArray().length; i++) {

            int ansId = Integer.parseInt(myAnsVO.getAnsidArray()[i]);
            myAnsVO.setReplyId(ansId);
            myAnsMapper.deleteAns(myAnsVO);
        }

    }


}
