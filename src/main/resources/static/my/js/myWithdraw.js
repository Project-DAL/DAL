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
    if(confirm("정말 탈퇴 하시겠습니까?")) {
        ajaxAPI("/my/MyWithdraw/deleteUser", null, "GET").then(response => {


            // Ajax를 통해 로그아웃을 요청
            ajaxAPI("/logout", null, "POST").then(logoutResponse => {
                window.location.href = "http://localhost:8282/loginForm"
            })

        });
    }


}
