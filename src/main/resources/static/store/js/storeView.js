/*
File Name      : storeView.css
Program Name   : 상품 상세 페이지 js
Draft Author   : 김석진
Draft Date     : 2024.01.14

Revision History
Ver.  Date          Revised By   Description
------------------------------------------------------------------------------------------------------------------------
0.1   2024.02.06    김석진       최초개발
0.2   2024.02.15    김석진       문의글 목록 아코디언 구현
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
    if (scrollPosition + 60 >= midbarTop) {
        console.log("midbarTop = " + midbarTop + ", scroll = " + scrollPosition);
        midbar.style.position = "fixed";
        midbar.style.width = midbarWidth + "px";
        midbar.style.top = "40px";
    } else {
        // 메뉴바를 다시 원래 속성으로 변경
        midbar.style.position = "static";
    }
});

/* 문의글 목록 아코디언 구현 */
const dropdownTit = document.querySelectorAll('tbody .qnaList-wrap');
const dropdownCn = document.querySelectorAll('.qnaList-dropdown');

for(let i = 0; i < dropdownTit.length; i++){
    dropdownTit[i].addEventListener('click', function(){

        // 클릭한 문의글이 토글되어 있을 시 토글지움.
        if(dropdownCn[i].classList.contains('active')){
            dropdownCn[i].classList.toggle('active');
        }
        else {
            // 기존의 토글되어 있는 글이 있을지 모르니 싹 다 지움
            for(let x = 0; x < dropdownCn.length; x++){
                dropdownCn[x].classList.remove('active');
            }
            // 클릭한 문의글에 토글.
            dropdownCn[i].classList.toggle('active');
        }
    })
}

document.addEventListener('DOMContentLoaded', function() {
    // fnMoveCategory();
    let categories = document.querySelectorAll('.midbar-title');

    categories.forEach(function(category) {
        category.addEventListener("click", function() {
            let targetId = category.getAttribute('data-target');
            console.log(targetId);
            document.getElementById(targetId).scrollIntoView({
                block: "center"
            });
        });
    });
});

// function fnMoveCategory() {
//     let category = document.querySelectorAll('.midbar-title li');
//
//     category.forEach(function(list) {
//         list.addEventListener("click", function() {
//             let targetId = list.getAttribute('data-target');
//             console.log(targetId);
//             document.querySelector(targetId).scrollIntoView();
//         })
//     })
// }