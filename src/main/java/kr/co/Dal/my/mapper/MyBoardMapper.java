/*
    파일명        : MyBoardMapper.java
    최초 작성자    : 박제형
    최초 작성날짜   : 2024.01.22
*/

package kr.co.Dal.my.mapper;


import kr.co.Dal.my.model.MyAnsVO;
import kr.co.Dal.my.model.MyBoardVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyBoardMapper {

    /* 내가 쓴 게시글 조회 */
    List<MyBoardVO> selectBoardList(MyBoardVO myBoardVO);


    /* 게시글 삭제 */
    public void deleteBoard(MyBoardVO myBoardVO);


    /* 내가 쓴 댓글 조회 */
    List<MyAnsVO> selectAnsList(MyAnsVO myAnsVO);


    /* 댓글 삭제 */
    public void deleteAns(MyAnsVO myAnsVO);







}