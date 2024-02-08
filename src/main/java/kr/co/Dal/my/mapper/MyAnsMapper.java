package kr.co.Dal.my.mapper;

import kr.co.Dal.my.model.MyAnsVO;
import kr.co.Dal.util.SearchCondition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyAnsMapper {


    /* 내가 쓴 댓글 조회 */
    List<MyAnsVO> selectAnsList(SearchCondition sc);

    /*  내가 쓴 목록 개수 */
    int countAns(SearchCondition sc);

    /* 댓글 삭제 */
    public void deleteAns(MyAnsVO myAnsVO);

}
