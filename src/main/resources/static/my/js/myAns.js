/*
    파일명        : myBoard.js
    최초 작성자    : 박제형
    최초 작성날짜   : 2024.02.02
*/

"use strict";

let btnDelete2 = document.getElementById('btnDelete2');             // 삭제버튼 (내가 쓴 댓글)
let allCheckboxes2 = document.getElementById('all_checkboxes2');    // 전체 체크박스2 (내가 쓴 댓글)
let checkboxes2 = document.querySelectorAll('.checkboxes2');         // 개별 체크박스2 (내가 쓴 댓글)
let ansidArray = [];                                                         // 체크박스에 대한 ansid


document.addEventListener('DOMContentLoaded', function(){
    addEventListenerBtn();
})




function addEventListenerBtn(){

    allCheckboxes2.addEventListener('change', fnAllCheckBox2);
    checkboxes2.forEach(function(checkbox){
        checkbox.addEventListener('change', fnCheckBox2);
    })
    btnDelete2.addEventListener('click', fnDelete2);

}





// 전체 체크박스 함수2 (내가 쓴 댓글)
function fnAllCheckBox2(){
    checkboxes2.forEach(function(checkbox){
        checkbox.checked = allCheckboxes2.checked;
    });

    // 현재 체크박스의 속성 값을 배열에 추가
    ansidArray = [];
    checkboxes2.forEach(function(checkbox){
        if (checkbox.checked) {
            ansidArray.push(checkbox.getAttribute('data-ansid'));

            console.log("ansidArray", ansidArray);
        }
    });

}



// 개별 체크박스 함수2 (내가 쓴 댓글)
function fnCheckBox2(){
    if (!this.checked) {
        allCheckboxes2.checked = false;
    } else {
        // 모든 checkboxes가 체크되어 있는지 확인
        let allChecked = true;
        checkboxes2.forEach(function(checkbox) {
            if (!checkbox.checked) {
                allChecked = false;
            }
        });

        // 모든 checkboxes가 체크되어 있다면 allCheckboxes도 체크
        if (allChecked) {
            allCheckboxes2.checked = true;
        }
    }

    // 현재 체크박스의 속성 값을 배열에 추가
    ansidArray = [];
    checkboxes2.forEach(function(checkbox){
        if (checkbox.checked) {
            ansidArray.push(checkbox.getAttribute('data-ansid'));
        }
    });

    console.log("ansidArray", ansidArray);
}





// 삭제버튼 함수2 (댓글)
function fnDelete2(){
    let checkedCount = 0;

    checkboxes2.forEach(function (checkbox){
        if (checkbox.checked){
            checkedCount++;
        }
    })

    if (checkedCount === 0){
        alert("삭제할 글을 선택해 주세요");
        return;
    }

    let jsonData ={
        ansidArray : ansidArray
    }


    ajaxAPI("/my/MyBoard/deleteAns", jsonData, "POST").then(response => {
        if(confirm("정말 삭제 하시겠습니까?")) {
            window.location.href = '/my/MyAns';
        }
    });
}








