/*
File Name      : storeMain.js
Program Name   : 스토어 메인 페이지 js
Draft Author   : 김석진
Draft Date     : 2024.01.14

Revision History
Ver.  Date          Revised By   Description
------------------------------------------------------------------------------------------------------------------------
0.1   2024.01.14    김석진       최초개발
----------------------------------------------------------------------------------------------------------------------->
*/
$(document).ready(function () {
    $(".slider").bxSlider({
        auto: true,
        pause: 5000,
        minSlides: 3,
        slideWidth: 1376,
    });

    $(".bxslider").bxSlider({
        minSlides: 4,
        maxSlides: 4,
        slideWidth: 1376,
        slideMargin: 50,
        moveSlides: 1
    });
});

document.addEventListener('DOMContentLoaded', function() {
    fnProdList();
});

function fnProdList() {
    let lists = document.querySelectorAll(".ct_cate-wrap li");

    lists.forEach(function(list) {
        list.addEventListener("click", function() {
            let prodType = list.getAttribute("data-prodType");
            window.location.href = "/store/storeList?prodType=" + prodType;
        })
    })
}