package kr.co.Dal.admin.mapper;

import kr.co.Dal.admin.model.AdminStoreVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminAjaxMapper {

    List<AdminStoreVO> selectStoreList();
}
