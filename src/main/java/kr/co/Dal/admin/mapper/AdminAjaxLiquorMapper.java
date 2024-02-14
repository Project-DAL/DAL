package kr.co.Dal.admin.mapper;

import kr.co.Dal.admin.model.AdminStoreVO;
import kr.co.Dal.admin.model.LiqImgVO;
import kr.co.Dal.admin.model.LiquorVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminAjaxLiquorMapper {
    List<LiquorVO> selectLiquorList();

    List<LiquorVO> selectLiquorCategoryList(@Param("liqType") String liqType);

    void insertLiquor(LiquorVO liquorVO);

    void insertLiquorImg(LiqImgVO liqImgVO);

    List<LiqImgVO> selectLiquorImagesByLiqId(int liqId);

    void deleteLiquorImagesByProdId(int liqId);

    void deleteLiquor(int liqId);
}
