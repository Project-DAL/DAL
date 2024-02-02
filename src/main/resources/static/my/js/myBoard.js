"use strict";
let btnDelete = document.getElementById('btnDelete');               // 삭제버튼
let allCheckboxes = document.getElementById('all_checkboxes');      // 전체 체크박스
let checkboxes = document.querySelectorAll('.checkboxes');    // 개별 체크박스


document.addEventListener('DOMContentLoaded', function(){
    addEventListenerBtn();
})


function addEventListenerBtn(){
    allCheckboxes.addEventListener('change', fnAllCheckBox);
    checkboxes.forEach(function(checkbox){
        checkbox.addEventListener('change', fnCheckBox);
    })
    btnDelete.addEventListener('click', fnDelete);

}



// 전체 체크박스 함수
function fnAllCheckBox(){
    checkboxes.forEach(function(checkbox){
        checkbox.checked = allCheckboxes.checked;
    });
}



// 개별 체크박스 함수
function fnCheckBox(){
    if (!this.checked) {
        allCheckboxes.checked = false;
    }
}


// 삭제버튼 함수
function fnDelete(){

    console.log("삭제완료");
}
