/*
    파일명        : myCoupon.js
    최초 작성자    : 박제형
    최초 작성날짜   : 2024.02.05
*/



"use strict";
let btnGet = document.querySelectorAll('.ct-middle-get'); // 받기 버튼


document.addEventListener('DOMContentLoaded', function(){
    addEventListenerBtn();
})



function addEventListenerBtn(){
    btnGet.forEach(function(btnGet) {
        btnGet.addEventListener('click', fnBtnGet);
    });
}


function fnBtnGet() {

    let cpId = this.getAttribute('data-cpid'); // cp_id 값
    console.log("cpId=" + cpId);
    let jsonData ={
        cpId : cpId
    }

    ajaxAPI("/my/MyCoupon/insert", jsonData, "POST").then(response => {

        window.location.href = '/my/MyCoupon';
    })
}


