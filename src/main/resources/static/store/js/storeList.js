/*
File Name      : storeList.js
Program Name   : 상품 리스트 페이지 js
Draft Author   : 김석진
Draft Date     : 2024.01.14

Revision History
Ver.  Date          Revised By   Description
------------------------------------------------------------------------------------------------------------------------
0.1   2024.01.14    김석진       최초개발
0.2   2024.02.17    김석진       상세조회 이벤트 구현
----------------------------------------------------------------------------------------------------------------------->
*/

document.addEventListener('DOMContentLoaded', function() {
    fnProdDetail();
    addEventListenerBtn();
});

function fnProdDetail() {
    let lists = document.querySelectorAll("li[data-prodId]");

    lists.forEach(function (list) {
        list.addEventListener("click", function() {
            let prodId = list.getAttribute("data-prodId");
            window.location.href = "/store/storeView?prodId=" + prodId;
        })
    })
}

let prodType = "";

function addEventListenerBtn(){
    document.querySelectorAll(".ct_cate-wrap li").forEach(li => {
        li.addEventListener("click", function() {
            prodType = this.getAttribute("data-prodType");
            console.log(prodType);
        });
    });
    prodType.addEventListener('click', fnSelectProdList);
}

function fnSelectProdList() {
    let jsonData = {
        prodType : prodType
    };
    ajaxAPI("/store/selectListAjax", jsonData, "GET").then(response => {
        console.log("되는건가");
    })
}