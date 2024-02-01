package kr.co.Dal.admin.service;

/*
File Name      : AdminAjaxService.js
Program Name   : 비동기방식 관리자 서비스(비즈니스 로직)
Draft Author   : 김진형
Draft Date     : 2024.01.17

Revision History
Ver.  Date          Revised By   Description
————————————————————————————————————————————————————————————
0.1   2024.01.17    김진형       최초개발
———————————————————————————————————————————————————————————>
*/

import kr.co.Dal.admin.mapper.AdminAjaxMapper;
import kr.co.Dal.admin.model.AdminStoreVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminAjaxService {

    private final AdminAjaxMapper adminAjaxMapper;
    public List<AdminStoreVO> findStoreList() {
        return adminAjaxMapper.selectStoreList();
    }
}
