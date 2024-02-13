package kr.co.Dal.store.service;

import kr.co.Dal.store.mapper.QnaMapper;
import kr.co.Dal.store.model.QnaVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QnaService {

    private final QnaMapper qnaMapper;

    public void insertQna(QnaVO qnaVO) {
        qnaMapper.insertQna(qnaVO);
    }
}
