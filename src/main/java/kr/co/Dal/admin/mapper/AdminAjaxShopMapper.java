package kr.co.Dal.admin.mapper;

import kr.co.Dal.admin.model.ShopVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminAjaxShopMapper {
    List<ShopVO> selectShopList();
}
