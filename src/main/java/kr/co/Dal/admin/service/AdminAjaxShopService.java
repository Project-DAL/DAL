package kr.co.Dal.admin.service;

import kr.co.Dal.admin.mapper.AdminAjaxShopMapper;
import kr.co.Dal.admin.model.ShopVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminAjaxShopService {

    private final AdminAjaxShopMapper adminAjaxShopMapper;

    public List<ShopVO> findShopList() {
        return adminAjaxShopMapper.selectShopList();
    }
}
