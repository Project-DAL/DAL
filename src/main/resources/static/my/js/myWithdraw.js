/*
    파일명        : MyWithdraw.js
    최초 작성자    : 박제형
    최초 작성날짜   : 2024.02.03
*/

"use strict";

let btnWidthDraw = document.getElementById('btnWidthDraw'); // 탈퇴 버튼



document.addEventListener('DOMContentLoaded', function(){
    addEventListenerBtn();
})



function addEventListenerBtn(){
    btnWidthDraw.addEventListener('click', fnWidthDraw);
}



function fnWidthDraw(){

/*    let jsonData = {

    }*/

    ajaxAPI("/my/MyWithdraw/deleteUser", null, "POST").then(response => {
        if(confirm("정말 삭제 하시겠습니까?")) {
            window.location.href = '/';
        }
    });
}