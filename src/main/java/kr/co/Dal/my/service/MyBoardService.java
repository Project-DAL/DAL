/*
    파일명        : MyBoardService
    최초 작성자    : 박제형
    최초 작성날짜   : 2024.01.22
*/

package kr.co.Dal.my.service;

import kr.co.Dal.my.mapper.MyBoardMapper;
import kr.co.Dal.my.model.MyBoardVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyBoardService {

    private final MyBoardMapper myBoardMapper;

    /* 내가 쓴 게시글 조회 */
    public List<MyBoardVO> selectBoardList(MyBoardVO myBoardVO){
        return myBoardMapper.selectBoardList(myBoardVO);
    }




}
