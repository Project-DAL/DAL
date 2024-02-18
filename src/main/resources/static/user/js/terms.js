"use strict";
/*
File Name      : terms.js
Program Name   : 약관동의
Draft Author   : 신호준
Draft Date     : 2024.02.13

Revision History
Ver.  Date          Revised By   Description
------------------------------------------------------------------------------------------------------------------------
0.1   2024.02.13    신호준       최초개발
----------------------------------------------------------------------------------------------------------------------->
*/

/* 1. Variables *******************************************************************************************************/
/* 1.1 Etc Variables (Json Object, HTML String, Temporary) */
let iSelectedId     = null; // 선택된 행의 ID
let jsonGridList    = null;
let jsonData = {};


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



/* 4. Functions - Event Listener **************************************************************************************/
/*  4.1 Event Listener - Process(CRUD) Button */
function addEventListenerCRUDBtn() {
    // 체크박스 전체 선택/해제 기능 추가
    document.getElementById('checkAll').addEventListener('click', toggleCheckAll);

    // 개별 체크박스 선택 시 전체 선택 체크박스 해제 확인
    let checkboxes = document.querySelectorAll('.terms');
    checkboxes.forEach(function (checkbox) {
        checkbox.addEventListener('click', checkSingleCheckbox);
    });
}

// 체크박스 전체 선택/해제 함수
function toggleCheckAll() {
    let checkAllCheckbox = document.getElementById('checkAll');
    let checkboxes = document.querySelectorAll('.terms');

    checkboxes.forEach(function (checkbox) {
        checkbox.checked = checkAllCheckbox.checked;
        // 개별 체크박스에 대한 처리 함수 호출
        let index = checkbox.id.charAt(checkbox.id.length - 1);
        updateCheckboxValue(index);
    });
}

// 개별 체크박스 선택 시 전체 선택 체크박스 해제 함수
function checkSingleCheckbox() {
    let checkAllCheckbox = document.getElementById('checkAll');
    let checkboxes = document.querySelectorAll('.terms');
    let allChecked = Array.from(checkboxes).every(function (checkbox) {
        return checkbox.checked;
    });

    checkAllCheckbox.checked = allChecked;
}



// 약관 동의 페이지의 JavaScript 코드
// 체크박스 값을 업데이트하는 함수
// 자바스크립트 코드
function updateCheckboxValue(index) {
    const agreeCheckbox = document.getElementById(`agreeCheckbox${index}`);
    const hiddenField = document.getElementById(`agreeValueChk${index}`);

    if (agreeCheckbox && hiddenField) {
        const agreeValue = agreeCheckbox.checked ? 1 : 0;

        if (index === 1) {
            // chk1이 선택되었을 때 chk2도 0으로 설정
            const chk2Checkbox = document.getElementById('agreeCheckbox2');
            const hiddenFieldChk2 = document.getElementById('agreeValueChk2');

            if (chk2Checkbox && hiddenFieldChk2 && !chk2Checkbox.checked) {
                hiddenFieldChk2.value = 0;
                console.log(`Agree Value 2:`, 0);
            }
        }

        hiddenField.value = agreeValue;
        console.log(`Agree Value ${index}:`, agreeValue);
    } else if (hiddenField) {
        // 체크박스가 없을 경우 기본값 0으로 설정
        hiddenField.value = 0;
        console.log(`Agree Value ${index}:`, 0);
    }
}



function checkAndSubmit() {
    let agreeCheckbox1 = document.getElementById('agreeCheckbox1');
    if (!agreeCheckbox1.checked) {
        alert('필수 선택은 하셔야 합니다.');
        return;
    }


    // 모든 체크가 완료되었을 때 다음 동작 수행
    // 예: 폼 서브밋 또는 다음 페이지로 이동
    document.getElementById('termsForm').submit();
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



