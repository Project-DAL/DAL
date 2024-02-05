"use strict";

let divObjLiqType = document.querySelectorAll('.liq-type');
let divObjLiqList = document.getElementById("liq-list");
let divObjLiqNm = document.getElementById("liq-info-nm");
let divObjLiqMemo = document.getElementById("liq-info-memo");

/** Initialize */
document.addEventListener('DOMContentLoaded', function() {
    console.log("DOMContent");
    /* CRUD 버튼 이벤트 등록 */
    addEventListenerCRUDBtn();

    /* 술 분류 선택  */
    addEventListenerType();
});

/** CRUD 버튼 이벤트 등록 */
function addEventListenerCRUDBtn(){

}

/** 술 분류 선택 */
function addEventListenerType(){
    console.log("addEventListenerType");
    for(let type of divObjLiqType) {
        type.addEventListener("click", function(){
            console.log("data-value: " + type.dataset.value);
            /* 분류 선택에 따른 술 목록 가져오기 */
            ajaxAPI('/museum/liqList?liqType=' + type.dataset.value, null, "GET").then(response => {
               console.log("response: " , response);

                divObjLiqList.innerHTML = "";
                for(let i=0; i<response.length; i++) {
                    divObjLiqList.insertAdjacentHTML('beforeend', `
                        <div>${response[i].liq_nm}</div>
                    `);
                }

            });
        });
    }



};
