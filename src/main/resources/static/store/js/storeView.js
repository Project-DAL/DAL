/*
File Name      : storeView.css
Program Name   : 상품 상세 페이지 js
Draft Author   : 김석진
Draft Date     : 2024.01.14

Revision History
Ver.  Date          Revised By   Description
------------------------------------------------------------------------------------------------------------------------
0.1   2024.01.14    김석진       최초개발
----------------------------------------------------------------------------------------------------------------------->
*/
let midbar = document.getElementById('ct_midbar');

// 메뉴바의 위치 계산
let midbarTop = midbar.offsetTop;
let midbarHeight = midbar.offsetHeight;

// 메뉴바의 너비 계산
let midbarWidth = midbar.offsetWidth;

// 스크롤 이벤트 핸들러
window.addEventListener("scroll", function() {

    // 스크롤하는 높이를 체크
    let scrollPosition = window.scrollY;

    // 스크롤하는 위치가 메뉴바보다 클 경우 (더 아래로 스크롤 할 경우)
    if (scrollPosition >= midbarTop) {
        console.log("midbarTop = " + midbarTop + ", scroll = " + scrollPosition);
        midbar.style.position = "fixed";
        midbar.style.width = midbarWidth + "px";
        midbar.style.top = "40px";
    } else {
        // 메뉴바를 다시 원래 속성으로 변경
        midbar.style.position = "static";
    }
});
