/*
    파일명        : myWish.js
    최초 작성자    : 박제형
    최초 작성날짜   : 2024.02.02
*/

"use strict";
let checkboxes = document.querySelectorAll('.checkboxes');    // 체크박스
let btnEdit = document.getElementById('btnEdit');            // 편집버튼
let btnDel = document.getElementById('btnDel');              // 삭제버튼
let btnCancel = document.getElementById('btnCancel');        // 취소버튼
let prodidArray = [];                                                 // prodid 배열



document.addEventListener('DOMContentLoaded', function(){
    addEventListenerBtn();
})





function addEventListenerBtn() {
    btnDel.addEventListener('click', fnDelete);
    btnEdit.addEventListener('click', fnEdit);
    btnCancel.addEventListener('click', fnCancel);
    checkboxes.forEach(function(checkbox){
        checkbox.addEventListener('change', fnCheckBox);
    })
}




// 이미지 클릭시 상품정보로 이동 함수
function fnClickContent(dataProdid){
    let prodId = dataProdid;
    console.log("prodId =" + prodId);
    window.location.href = "http://localhost:8282/store/StoreView?prodId=" + prodId;

}



// 편집 버튼 클릭 함수
function fnEdit(){

    console.log("fnEdit");

    checkboxes.forEach(checkbox => {
        checkbox.style.display = '';
    });
    btnEdit.style.display = 'none';
    btnDel.style.display = '';
    btnCancel.style.display = '';
}


// 취소 버튼 클릭 함수
function fnCancel(){
    btnEdit.style.display = '';
    btnDel.style.display = 'none';
    btnCancel.style.display = 'none';
    checkboxes.forEach(checkbox => {
        checkbox.style.display = 'none';
    });
}


// 체크박스 함수
function fnCheckBox(){    // 현재 체크박스의 속성 값을 배열에 추가
    prodidArray = [];
    checkboxes.forEach(function(checkbox){
        if (checkbox.checked) {
            prodidArray.push(checkbox.getAttribute('data-prodid'));
        }
    });

    console.log("prodidArray", prodidArray);

}

// 삭제 버튼 함수
function fnDelete(){
    let checkedCount = 0;

    checkboxes.forEach(function (checkbox){
        if (checkbox.checked){
            checkedCount++;
        }
    })

    if (checkedCount === 0){
        alert("삭제할 상품을 선택해 주세요");
        return;
    }


    let jsonData = {
        prodidArray: prodidArray
    }

    ajaxAPI("/my/MyWish/deleteWish", jsonData, "POST").then(response => {
        confirm("정말 삭제 하시겠습니까?");
        window.location.href = '/my/MyWish';
    })

}