/*
    파일명        : myBoard.js
    최초 작성자    : 박제형
    최초 작성날짜   : 2024.02.02
*/

"use strict";

let btnDelete = document.getElementById('btnDelete');               // 삭제버튼 (내가 쓴 게시글)
let btnDelete2 = document.getElementById('btnDelete2');             // 삭제버튼 (내가 쓴 댓글)
let allCheckboxes = document.getElementById('all_checkboxes');      // 전체 체크박스 (내가 쓴 게시글)
let allCheckboxes2 = document.getElementById('all_checkboxes2');    // 전체 체크박스2 (내가 쓴 댓글)
let checkboxes = document.querySelectorAll('.checkboxes');           // 개별 체크박스 (내가 쓴 게시글)
let checkboxes2 = document.querySelectorAll('.checkboxes2');         // 개별 체크박스2 (내가 쓴 댓글)
let myBoardTab = document.getElementById('myBoardTab');             // 내가 쓴 게시글 탭
let myReplyTab = document.getElementById('myReplyTab');             // 내가 쓴 댓글 탭
let boardTab = document.getElementById('boardTab');                 // 게시글 목록 범위
let replyTab = document.getElementById('replyTab');                 // 댓글 목록 범위
let bardidArray = [];                                                        // 체크박스에 대한 bardid
let bardtypeArray = [];                                                      // 체크박스에 대한 bardtype
let ansidArray = [];                                                         // 체크박스에 대한 ansid
let title1 = document.getElementById('title1'); // 내 게시글
let title2 = document.getElementById('title2'); // 내 댓글


document.addEventListener('DOMContentLoaded', function(){
    addEventListenerBtn();
})




function addEventListenerBtn(){
    allCheckboxes.addEventListener('change', fnAllCheckBox);
    checkboxes.forEach(function(checkbox){
        checkbox.addEventListener('change', fnCheckBox);
    })
    allCheckboxes2.addEventListener('change', fnAllCheckBox2);
    checkboxes2.forEach(function(checkbox){
        checkbox.addEventListener('change', fnCheckBox2);
    })
    btnDelete.addEventListener('click', fnDelete);
    btnDelete2.addEventListener('click', fnDelete2);
    myBoardTab.addEventListener('click', fnMyBoardTab);
    myReplyTab.addEventListener('click', fnMyReplyTab);
}




// 내가 쓴 게시글 탭 함수
function fnMyBoardTab(){
    boardTab.style.display = '';
    replyTab.style.display = 'none';
    btnDelete.style.display = '';
    btnDelete2.style.display = 'none';
    title1.style.display = '';
    title2.style.display = 'none';

}

// 내가 쓴 댓글 탭 함수
function fnMyReplyTab(){
    boardTab.style.display = 'none';
    replyTab.style.display = '';
    btnDelete.style.display = 'none';
    btnDelete2.style.display = '';
    title1.style.display = 'none';
    title2.style.display = '';

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
              window.location.href = '/my/MyBoard';
          }
      });
}








