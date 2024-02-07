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
})



function addEventListenerBtn(){
    orderDateSelect.addEventListener('change', function(){
        fnselectData(selectedOption);
    });
}



function fnselectData(){
    console.log('selectedOption: ' + selectedOption);






    /*
       let jsonData = {

       }

    ajaxAPI("/my/MyWithdraw/deleteUser", null, "POST").then(response => {

    });*/
}