/*
    파일명        : MyMain.js
    최초 작성자    : 박제형
    최초 작성날짜   : 2024.02.07
*/

"use strict";

let orderDateSelect = document.querySelector('.order-date');
let selectedOption = orderDateSelect.options[orderDateSelect.selectedIndex].text;


document.addEventListener('DOMContentLoaded', function(){
    addEventListenerBtn();
    fnAjaxList();
})



function addEventListenerBtn(){
    orderDateSelect.addEventListener('change', function(){
        fnselectData(selectedOption);
    });
}



function fnselectData(){
    console.log('selectedOption: ' + selectedOption);
}



function fnAjaxList() {

    let jsonData = {}


    ajaxAPI("/my/MyPageMain/order", null, "GET").then(response => {
        console.log(response);
        console.log("response length: " + response.length); // 각 주문번호

        for(let i = 0; i < response.length; i++) {
            // 예를 들어 주문번호 1번
            console.log("response[0]: ", response[0]);
            console.log("rdate: " + response[i].order_rdates);
            console.log("rdate length: " + response[i].order_rdates.split(",").length);
            let rdateLength = response[i].order_rdates.split(",").length;
            let prodTits = response[i].prod_tits;

            // 주문번호 1번 + 각 주문 제품
            for (let j = 0; j < rdateLength; j++) {
                console.log("prodTit: " + prodTits.split(",")[j]);
            }

        }

    });
}

