package kr.co.Dal.my.web;


import kr.co.Dal.my.model.MyBoardVO;
import kr.co.Dal.my.service.MyBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class MyAjaxController {

    private final MyBoardService myBoardService;


    @PostMapping("/my/MyBoard/delete")
    public ResponseEntity<MyBoardVO> deleteMyBoardList(@RequestBody MyBoardVO myBoardVO){

        myBoardService.deleteBoardList(myBoardVO);
        return ResponseEntity.ok(myBoardVO);
    }



}