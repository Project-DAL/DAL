/*
    파일명        : MyBoardService
    최초 작성자    : 박제형
    최초 작성날짜   : 2024.01.22
*/

package kr.co.Dal.my.service;

import kr.co.Dal.my.mapper.MyBoardMapper;
import kr.co.Dal.my.model.MyAnsVO;
import kr.co.Dal.my.model.MyBoardVO;
import kr.co.Dal.util.PageHandler;
import kr.co.Dal.util.SearchCondition;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyBoardService {

    private final MyBoardMapper myBoardMapper;

    /* 내가 쓴 게시글 조회 */
    public List<MyBoardVO> selectBoardList(Model model, MyBoardVO myBoardVO, SearchCondition sc){

        // pagination
        int totalCnt = myBoardMapper.countBoard(sc);

        log.warn("totalCnt: " + totalCnt);

        int totalPage = (int)Math.ceil(totalCnt / (double)sc.getPageSize());
        if(sc.getPage() > totalPage) sc.setPage(totalPage);


        // 한 페이지에 보일 건수
        int naviSize = 10;

        PageHandler pageHandler = new PageHandler(totalCnt, sc, naviSize);

        // model 전송
        List<MyBoardVO> boardList = myBoardMapper.selectBoardList(sc);

        model.addAttribute("boardList", boardList);
        model.addAttribute("ph", pageHandler);

        log.warn("ph: " + pageHandler);

        return boardList;
    }


    /* 게시글 삭제 */
    public void deleteBoard(MyBoardVO myBoardVO){

        log.warn("bard length: " + myBoardVO.getBardidArray().length);
        log.warn("type length: " + myBoardVO.getBardtypeArray().length);

        for (int i = 0; i < myBoardVO.getBardidArray().length; i++) {
            log.warn("bardId: " + myBoardVO.getBardidArray()[i]);
            log.warn("type: " + myBoardVO.getBardtypeArray()[i]);

            int bardId = Integer.parseInt(myBoardVO.getBardidArray()[i]);
            int bardType = Integer.parseInt(myBoardVO.getBardtypeArray()[i]);

            myBoardVO.setBard_id(bardId);
            myBoardVO.setBard_type(bardType);

            myBoardMapper.deleteBoard(myBoardVO);
        }


    }


}
