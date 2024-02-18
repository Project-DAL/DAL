/*
File Name      : storeQnaWrite.js
Program Name   : 상품 문의글 작성 js
Draft Author   : 김석진
Draft Date     : 2024.01.23

Revision History
Ver.  Date          Revised By   Description
------------------------------------------------------------------------------------------------------------------------
0.1   2024.02.09    김석진       최초개발
----------------------------------------------------------------------------------------------------------------------->
*/
"use strict";

let bg;

function modal(id) {
    let zIndex = 9999;
    let modal = document.getElementById(id);

    // 모달창 뒷배경 어둡게 처리
    bg = document.createElement('div');
    bg.setStyle({
        position: 'fixed',
        zIndex: zIndex,
        left: '0px',
        top: '0px',
        width: '100%',
        height: '100%',
        overflow: 'auto',
        // 레이어 색갈은 여기서 바꾸면 됨
        backgroundColor: 'rgba(0,0,0,0.4)'
    });
    document.body.append(bg);
    // 모달 동작 시 뒷배경 스크롤 이동 방지
    document.body.style.overflow = 'hidden';

    // 닫기 버튼 처리, 시꺼먼 레이어와 모달 div 지우기
    modal.querySelector('.modal_close_btn').addEventListener('click', function() {
        bg.remove();
        modal.style.display = 'none';
        document.body.style.overflow = 'auto';
    });

    var modalContent = modal.querySelector('.ct_qna-mid');
    modalContent.setStyle({
        overflowY: 'auto',
        maxHeight: '500px'
    })

    modal.setStyle({
        position: 'fixed',
        display: 'block',
        boxShadow: '0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19)',

        // 시꺼먼 레이어 보다 한칸 위에 보이기
        zIndex: zIndex + 1,

        // div center 정렬
        top: '50%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        msTransform: 'translate(-50%, -50%)',
        webkitTransform: 'translate(-50%, -50%)',
        width: '800px',
        height: '562px'
    });

}

// Element 에 style 한번에 오브젝트로 설정하는 함수 추가
Element.prototype.setStyle = function(styles) {
    for (var k in styles) this.style[k] = styles[k];
    return this;
};

document.addEventListener('DOMContentLoaded', function(){
    addEventListenerBtn();
})

function addEventListenerBtn(){
    let qnaSave = document.getElementById('qnaSave');
    qnaSave.addEventListener('click', fnInsertQna);
}

function fnInsertQna() {
    let qnaTit = document.getElementById("qnaTit").value;
    let qnaCn = document.getElementById("qnaCn").value;
    let qnaType = document.getElementById("qna-cate");

    // 유효성 체크
    if(qnaType.selectedIndex == 0) {
        alert('문의유형을 선택해주세요.');
        return false;
    }

    // 유효성 체크
    if(qnaTit == "") {
        document.getElementById("qnaTit").focus();
        alert('제목을 입력해주세요.');
        return false;
    }

    // 유효성 체크
    if(qnaCn == "") {
        document.getElementById("qnaCn").focus();
        alert('내용을 입력해주세요.');
        return false;
    }

    if(confirm("저장하시겠습니까?")) {
        let qnaType = document.getElementById("qna-cate").value;
        let qnaSecret;
        let secretYn = document.getElementById("secret-box");

        // 비밀글 여부 체크
        if (secretYn.checked) {
            qnaSecret = 2;
            console.log(secretYn + ", " + qnaSecret);
        } else {
            qnaSecret = 1;
            console.log(secretYn + ", " + qnaSecret);
        }

        let jsonData = {
            qnaType : qnaType,
            qnaSecret : qnaSecret,
            qnaTit: qnaTit,
            qnaCn: qnaCn
        };

        ajaxAPI("/store/insertQna", jsonData, "POST").then(response => {
            bg.remove();
            document.getElementById('ct_qna').style.display = 'none';
            document.body.style.overflow = 'auto';
            alert("저장되었습니다.");
        })
    }
}

