"use strict";
/*
File Name      : storeWrite.js
Program Name   : 관리자스토어등록
Draft Author   : 김진형
Draft Date     : 2024.02.12

Revision History
Ver.  Date          Revised By   Description
------------------------------------------------------------------------------------------------------------------------
0.1   2024.02.12    김진형       최초개발
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
    const prodInfo = {
        prodTit: document.getElementById('prodTit').value,
        prodType: document.getElementById('prodType').value,
        prodCnt: document.getElementById('prodCnt').value,
        prodOriPrice: document.getElementById('prodOriPrice').value,
        prodSalePrice: document.getElementById('prodSalePrice').value,
        prodStts: document.getElementById('prodStts').value,
        prodCnFile: document.getElementById('description').value,
        // 기타 필요한 정보 추가
    };

    ajaxAPI('/admin/storeInsert', prodInfo, "POST").then(response => {
        // 상품 정보 전송 성공 시, 파일 업로드 진행
        if (response.prodId) {
            uploadProductFiles(response.prodId);
        } else {
            console.error('Failed to submit product info');
        }
    }).catch(error => {
        console.error('Error submitting product info:', error);
    });
}

/* 파일등록 */
function uploadProductFiles(prodId) {
    const fileInput = document.getElementById('prodImg');
    const files = fileInput.files;
    const formData = new FormData();

    for (let i = 0; i < files.length; i++) {
        formData.append('files', files[i]);
    }

    // 상품 ID도 함께 서버로 전송
    formData.append('prodId', prodId);

    ajaxMultiFile('/admin/storeUploadFiles', formData, 'POST')
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
    document.getElementById("insertProd").addEventListener('click', fnInsert);    // 등록
}