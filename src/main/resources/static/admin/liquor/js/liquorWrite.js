"use strict";
/*
File Name      : liquorWrite.js
Program Name   : 관리자주류등록
Draft Author   : 김진형
Draft Date     : 2024.02.15

Revision History
Ver.  Date          Revised By   Description
------------------------------------------------------------------------------------------------------------------------
0.1   2024.02.15    김진형       최초개발
----------------------------------------------------------------------------------------------------------------------->
*/

/* 1. Variables *******************************************************************************************************/
/* 1.1 Etc Variables (Json Object, HTML String, Temporary) */

/* 2. Functions - Initialize ******************************************************************************************/
/* 2.1 Initialize */
document.addEventListener('DOMContentLoaded', function() {
    /** 1) CRUD 버튼 이벤트 등록 */
    addEventListenerCRUDBtn()

    /** 2) Search Condition 이벤트 등록 */
    // addEventListenerSearchCondition();
});

/* 3. Functions - Process(CRUD) ***************************************************************************************/
function fnInsert() {
    const liqInfo = {
        liqNm: document.getElementById('liqNm').value,
        liqType: document.getElementById('liqType').value,
        liqPlace: document.getElementById('liqPlace').value,
        liqCp: document.getElementById('liqCp').value,
        liqAlc: document.getElementById('liqAlc').value,
        liqHash: document.getElementById('liqHash').value,
        liqMemo: document.getElementById('liqMemo').value,
        liqCn: document.getElementById('description').value,
        liqAroma: document.getElementById('liqAroma').value,
        liqSmooth: document.getElementById('liqSmooth').value,
        liqBody: document.getElementById('liqBody').value,
        // 기타 필요한 정보 추가
    };

    ajaxAPI('/admin/liquorInsert', liqInfo, "POST").then(response => {
        // 술 정보 전송 성공 시 로직, 예를 들어 페이지를 새로고침하거나 알림을 표시
        if (response.liqId) {
            uploadProductFiles(response.liqId);
            // 추가적인 성공 시 처리 로직
        } else {
            console.error('Failed to submit liquor info');
        }
    }).catch(error => {
        console.error('Error submitting liquor info:', error);
    });
}

/* 파일등록 */
function uploadProductFiles(liqId) {
    const fileInput = document.getElementById('liquorImg');
    const files = fileInput.files;
    const formData = new FormData();

    for (let i = 0; i < files.length; i++) {
        formData.append('files', files[i]);
    }

    // 상품 ID도 함께 서버로 전송
    formData.append('liqId', liqId);

    ajaxMultiFile('/admin/liquorUploadFiles', formData, 'POST')
        .then(response => {
            console.log('Files uploaded successfully:', response);
            // 여기서 추가적인 처리를 할 수 있습니다. 예를 들어, 업로드 성공 메시지를 표시하거나 페이지를 새로고침합니다.
        })
        .catch(error => {
            console.error('Error uploading files:', error);
        });
}

/* 4. Functions - Event Listener **************************************************************************************/
/*  4.1 Event Listener - Process(CRUD) Button */
function addEventListenerCRUDBtn() {
    document.getElementById("insertLiquor").addEventListener('click', fnInsert);    // 등록
}