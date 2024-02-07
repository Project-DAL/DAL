/*
    파일명        : myBoard.js
    최초 작성자    : 박제형
    최초 작성날짜   : 2024.02.02
*/

"use strict";

let btnDelete = document.getElementById('btnDelete');               // 삭제버튼 (내가 쓴 게시글)
let allCheckboxes = document.getElementById('all_checkboxes');      // 전체 체크박스 (내가 쓴 게시글)
let checkboxes = document.querySelectorAll('.checkboxes');           // 개별 체크박스 (내가 쓴 게시글)
let bardidArray = [];                                                        // 체크박스에 대한 bardid
let bardtypeArray = [];                                                      // 체크박스에 대한 bardtype


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






// 전체 체크박스 함수 (내가 쓴 게시글)
function fnAllCheckBox(){
    checkboxes.forEach(function(checkbox){
        checkbox.checked = allCheckboxes.checked;
    });

    // 현재 체크박스의 속성 값을 배열에 추가
    bardidArray = [];
    bardtypeArray = [];
    checkboxes.forEach(function(checkbox){
        if (checkbox.checked) {
            bardidArray.push(checkbox.getAttribute('data-bardid'));
            bardtypeArray.push(checkbox.getAttribute('data-bardtype'));

            console.log("bardidArray", bardidArray);
            console.log("bardtypeArray", bardtypeArray);
        }
    });


}



// 개별 체크박스 함수 (내가 쓴 게시글)
function fnCheckBox(){
    if (!this.checked) {
        allCheckboxes.checked = false;
    } else {
        // 모든 checkboxes가 체크되어 있는지 확인
        let allChecked = true;
        checkboxes.forEach(function(checkbox) {
            if (!checkbox.checked) {
                allChecked = false;
            }
        });

        // 모든 checkboxes가 체크되어 있다면 allCheckboxes도 체크
        if (allChecked) {
            allCheckboxes.checked = true;
        }
    }

    // 현재 체크박스의 속성 값을 배열에 추가
    bardidArray = [];
    bardtypeArray = [];
    checkboxes.forEach(function(checkbox){
        if (checkbox.checked) {
            bardidArray.push(checkbox.getAttribute('data-bardid'));
            bardtypeArray.push(checkbox.getAttribute('data-bardtype'));
        }
    });

    console.log("bardidArray", bardidArray);
    console.log("bardtypeArray", bardtypeArray);
}




// 삭제버튼 함수 (게시판)
function fnDelete() {
    let checkedCount = 0;

    checkboxes.forEach(function (checkbox){
        if (checkbox.checked){
            checkedCount++;
        }
    })

    if (checkedCount === 0){
        alert("삭제할 글을 선택해 주세요");
        return;
    }

    let jsonData = {
        bardidArray: bardidArray,
        bardtypeArray: bardtypeArray
    }

    ajaxAPI("/my/MyBoard/deleteBoard", jsonData, "POST").then(response => {
        if(confirm("정말 삭제 하시겠습니까?")) {
            window.location.href = '/my/MyBoard';
        }
    });




}

function changePageAndTab(num){
    console.log("num: " + num);
}







