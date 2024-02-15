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

let nick = document.getElementById("nick");
let mobile1 = document.getElementById("mobile1");
let mobile2 = document.getElementById("mobile2");
let mobile3 = document.getElementById("mobile3");
let post_code = document.getElementById("post_code");
let regist_addr2 = document.getElementById("regist_addr2");


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

    let userHp = mobile1.value + '-' + mobile2.value + '-' + mobile3.value;

    let jsonData ={
        userNick: nick.value,
        userHp: userHp,
        zip: post_code.value,
        addr1: regist_addr.value,
        addr2: regist_addr2.value
    }

    if(confirm("정말 수정 하시겠습니까?")) {
        ajaxAPI("/my/MyInfo/update", jsonData, "POST").then(response => {
            window.location.href = '/my/MyInfo';
        })
    }
}