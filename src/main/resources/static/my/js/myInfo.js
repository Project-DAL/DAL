/*
    파일명        : myInfo.js
    최초 작성자    : 박제형
    최초 작성날짜   : 2024.02.03
*/

"use strict";

let btnUpdate = document.getElementById('btnUpdate'); // 수정버튼
let postcode_btn = document.getElementById('post_code'); // 우편 번호 버튼
let regist_postcode = document.getElementById('regist_postcode');   // 우편번호 적는곳
let regist_addr =document.getElementById("regist_addr");            // 기본주소 적는곳




document.addEventListener('DOMContentLoaded', function(){
    addEventListenerBtn();
})


function addEventListenerBtn(){
    btnUpdate.addEventListener('click', fnUpdate);
    postcode_btn.addEventListener('click', fnPostcode);
}



/* 우편번호 버튼 */
function fnPostcode(){

    console.log("fnPostcode");

    new daum.Postcode({
        oncomplete: function(data) {
            regist_postcode.value = data.zonecode;
            regist_addr.value = data.address;
        }
    }).open();


}


/* 수정 버튼 함수 */
function fnUpdate(){

/*
    let jsonData ={
    }
*/

    ajaxAPI("/my/MyInfo/update", null, "POST").then(response => {
        confirm("정말 수정 하시겠습니까?");
        window.location.href = '/my/MyInfo';
    })
}