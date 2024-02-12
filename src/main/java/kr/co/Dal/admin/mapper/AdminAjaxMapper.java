package kr.co.Dal.admin.mapper;

import kr.co.Dal.admin.model.AdminStoreVO;
import kr.co.Dal.admin.model.ProdImgVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminAjaxMapper {

    List<AdminStoreVO> selectStoreList();

    List<AdminStoreVO> selectCategoryList(@Param("prodType") String prodType);

    void insertStore(AdminStoreVO adminStoreVO);

    void insertProdImg(ProdImgVO prodImgVO);

    List<ProdImgVO> selectProdImagesByProdId(int prodId);

    void deleteProdImagesByProdId(int prodId);

    void deleteStore(int prodId);

    AdminStoreVO selectStoreById(int prodId);
}
