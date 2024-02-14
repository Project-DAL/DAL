"use strict";
/*
File Name      : storeList.js
Program Name   : 주류목록
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
    fnAjaxList();
});

/* 3. Functions - Process(CRUD) ***************************************************************************************/
function fnInsert() {
    window.location.href = "/admin/liquor/liquorWrite";
}

function fnAjaxList() {
    let table = $('#main-table').DataTable();

    table.clear().destroy(); // 기존 데이터를 지우고 DataTable 인스턴스를 파괴합니다.
    ajaxAPI('/admin/liquorList',null,"GET").then(response => {
        console.log("response: ", response);

        jsonGridList = response.list;

        appendGridListHtml();
    })
}

function fnAJaxCategoryList(dataType) {
    let table = $('#main-table').DataTable();

    table.clear().destroy(); // 기존 데이터를 지우고 DataTable 인스턴스를 파괴합니다.
    console.log("dataType : " + dataType)
    ajaxAPI('/admin/liquorCategoryList?liqType=' + dataType,null,"GET").then(response => {
        console.log("response: ", response);

        jsonGridList = response.list;

        console.log("jsonGridList : " + jsonGridList)

        appendGridListHtml();
    })
}


function addEventListenerCRUDBtn() {
    document.getElementById("InsertLiquor").addEventListener('click', fnInsert);    // 초기화
    document.getElementById('allProd').addEventListener('click', fnAjaxList);     // 모든 파일 불러오기
    document.getElementById('sojuProd').addEventListener('click', () => {
        const dataType = '1';
        fnAJaxCategoryList(dataType);
    });
    document.getElementById('beerProd').addEventListener('click', () => {
        const dataType = '2';
        fnAJaxCategoryList(dataType);
    });
    document.getElementById('liquorProd').addEventListener('click', () => {
        const dataType = '3';
        fnAJaxCategoryList(dataType);
    });
    document.getElementById('traditionalProd').addEventListener('click', () => {
        const dataType = '4';
        fnAJaxCategoryList(dataType);
        console.log(dataType)
    });
}




/* 5. Functions - Html Creator ****************************************************************************************/
/* 5.1 appendGridListHtml() - 목록 html 생성 */
function appendGridListHtml() {
    console.log("jsonGridListHTMl : " + jsonGridList);
    /** 1) 각 btn, inputText, label, editor 값 초기화 */
    iSelectedId                 = '';       // 선택된 ID 초기화
    document.getElementById("main-table-tbody").innerHTML     = '';       // 게시물 제목 초기화
    console.log(document.getElementById("main-table-tbody").innerHTML);

    /** 2) 목록 생성 */
    if(jsonGridList.length > 0) {
        for(let item of jsonGridList) {
            console.log("item : " + item.prodTit)
            document.getElementById("main-table-tbody").insertAdjacentHTML('beforeend', `
                 <tr>
                                        <td>${item.liqId}</td>
                                        <td><a href="#">${item.liqNm}</a></td>
                                        <td>${item.liqType}</td>
                                        <td>${item.liqPlace}</td>
                                        <td>${item.liqCp}</td>
                                        <td>${item.liqAlc}</td>
                                        <td>${item.liqHash}</td>
                                        <td class="modi-layout">
                                            <button class="btn btn-primary btn-sm d-inline-block mr2" data-id="${item.liqId}" onclick="modifyProduct(${item.liqId})"><i class="fas fa-edit"></i></button>
                                            <button class="btn btn-danger btn-sm d-inline-block" data-id="${item.liqId}" onclick="deleteProduct(${item.liqId})"><i class="fas fa-trash"></i></button>
                                        </td>
                                    </tr>`);

        }
    }
    console.log(document.getElementById("main-table-tbody").innerHTML);
    // DataTable 초기화 또는 업데이트
    initializeDataTable();
}

function initializeDataTable() {
    // 기존에 DataTable 인스턴스가 있으면 파괴
    console.log("main-table : " + $.fn.DataTable.isDataTable('#main-table'))
    if ($.fn.DataTable.isDataTable('#main-table')) {
        $('#main-table').DataTable().destroy();
    }
    let table = $("#main-table").DataTable({
        "responsive": true,
        "lengthChange": false,
        "autoWidth": false,
        "buttons": ["copy", "csv", "excel", "pdf", "print", "colvis"],
        "pageLength": 10
    });

    // DataTable 초기화 완료 후 콜백
    table.on('init', function () {
        console.log('DataTable initialization complete');
        // 여기에 DOM 상태를 확인하는 코드를 추가하세요
        console.log(document.getElementById("main-table-tbody").innerHTML);
    }).buttons().container().appendTo('#main-table_wrapper .col-md-6:eq(0)');
}

function deleteProduct(liqId) {
    if (confirm("해당 주류를 삭제하시겠습니까?")) {
        console.log(liqId);

        ajaxAPI('/admin/deleteLiquor/' + liqId,null,"DELETE").then(response => {
            if(response.status === 'success') {
                alert('주류가 삭제되었습니다.');
                // 여기에 상품 목록을 다시 로드하는 로직 추가
                fnAjaxList();
            } else {
                alert('주류 삭제에 실패했습니다.');
            }
        }).catch(error => {
            console.error('Error deleting product:', error);
            alert('주류 삭제 중 오류가 발생했습니다.');
        });
    }
}

function modifyProduct(liqId) {
    window.location.href = "/admin/storeModify/" + liqId;
}

