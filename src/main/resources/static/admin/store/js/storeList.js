"use strict";
/*
File Name      : storeList.js
Program Name   : 관리자스토어목록
Draft Author   : 김진형
Draft Date     : 2024.01.17

Revision History
Ver.  Date          Revised By   Description
------------------------------------------------------------------------------------------------------------------------
0.1   2024.01.17    김진형       최초개발
----------------------------------------------------------------------------------------------------------------------->
*/

/* 1. Variables *******************************************************************************************************/
/* 1.1 Etc Variables (Json Object, HTML String, Temporary) */
let iSelectedId     = null; // 선택된 행의 ID


/* 2. Functions - Initialize ******************************************************************************************/
/* 2.1 Initialize */
document.addEventListener('DOMContentLoaded', function() {
    /** 1) CRUD 버튼 이벤트 등록 */
    addEventListenerCRUDBtn()

    /** 2) Search Condition 이벤트 등록 */
    // addEventListenerSearchCondition();

    /** 3) 최초 목록조회 실행 */
});



/* 3. Functions - Process(CRUD) ***************************************************************************************/
function fnInsert() {
    window.location.href = "/admin/store/storeWrite";
}

function fnView() {
    // appendGridListHtml();
}


/* 4. Functions - Event Listener **************************************************************************************/
/*  4.1 Event Listener - Process(CRUD) Button */
function addEventListenerCRUDBtn() {
    document.getElementById("InsertProd").addEventListener('click', fnInsert);    // 초기화
}

/*  4.2 Event Listener - Search Condition Select Input */
// function addEventListenerSearchCondition() {
//     /** 1) 검색어 입력항목에서 엔터키를 누를 경우 목록조회 실행 */
//     너네 텍스트박스 입력.addEventListener('keyup', function(event) {
//         if(event.key === 'Enter') {
//             jsonPageNavigation.currentPageNo = 1;
//             너네목록함수입력();
//         }
//     });
// }


/* 5. Functions - Html Creator ****************************************************************************************/
/* 5.1 appendGridListHtml() - 목록 html 생성 */
// function appendGridListHtml() {
//     /** 1) 각 btn, inputText, label, editor 값 초기화 */
//     iSelectedId                 = '';       // 선택된 ID 초기화
//     document.getElementById("main-table-tbody").innerHTML     = '';       // 게시물 제목 초기화
//
//     /** 2) 목록 생성 */
//     if(jsonGridList.length > 0) {
//         for(let item of jsonGridList) {
//             /** 2-1) gridArea에 <tr> 요소생성 및 추가 */
//             tbodyObjGridArea.insertAdjacentHTML('beforeend', `
//                 <tr>
//                     <td class="label-td"><input type="checkbox" name="chk" class="ck"></td>
//                     <td class="label-td left"><div class="ellp">${htmlStrImg} ${item.bardSbjt}</div></td>
//                     <td class="label-td">${item.rgstId}</td>
//                     <td class="label-td">${item.rgstDt}</td>
//                 </tr>`);
//
//             /** 2-2) gridArea에 Event Listener 등록 */
//             tbodyObjGridArea.querySelector('tr:last-child').addEventListener('click', function() {
//                 /** 2-2-1) 전체 checkbox cheked 해제 */
//                 tbodyObjGridArea.querySelectorAll('input[type="checkbox"]')
//                     .forEach((checkbox) => { checkbox.checked = false; })
//
//                 /** 2-2-2) 전체 checkbox 하일라이트 calss 해제 */
//                 tbodyObjGridArea.querySelectorAll('tr')
//                     .forEach((tr) => { tr.classList.remove('active'); });
//
//                 /** 2-2-3) 현재 행 체크박스 하이라이트 설정 */
//                 this.querySelector('input[type="checkbox"]').checked = true;
//                 this.classList.add('active');
//
//                 /** 2-2-4) 전역변수로 선택된 item 번호 세팅 */
//                 iSelectedId = item.bardId;
//
//                 if(inputObjGblAuthCd.value == "USER"){
//                     btnUpdate.style.display     = 'none';   // 수정버튼 숨김
//                     btnDelete.style.display     = 'none';   // 삭제버튼 숨김
//                 }else {
//                     btnUpdate.style.display     = 'inline-block';   // 수정버튼
//                     btnDelete.style.display     = 'inline-block';   // 삭제버튼
//                 }
//                 /** 2-2-5) 상세조회 실행 */
//                 fnView();
//             });
//         }
//         /** 3) gridArea에 검색결과 없음 <tr> 추가 */
//     }
// }


