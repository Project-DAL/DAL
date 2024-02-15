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

    /* 술 분류 선택 */
    addEventListenerType();

    /* 술 정보 가져오기 */
    fnAjaxLiquor();
});

/** CRUD 버튼 이벤트 등록 */
function addEventListenerCRUDBtn(){

}

/** 술 분류 선택 */
function addEventListenerType(){
    //console.log("addEventListenerType");
    for(let type of divObjLiqType) {
        type.addEventListener("click", function(){
            //console.log("type clicked");
            //console.log("data-value: " + type.dataset.value);
            /* 분류 선택에 따른 술 목록 가져오기 */
            ajaxAPI('/museum/liqList?liqType=' + type.dataset.value, null, "GET").then(response => {
               //console.log("response: " , response);

                divObjLiqList.innerHTML = "";
                for(let i=0; i<response.length; i++) {
                    divObjLiqList.insertAdjacentHTML('beforeend', `
                        <div class="liquor" data-liq-id="${response[i].liqId}">${response[i].liqNm}</div>
                    `);
                }

                fnAjaxLiquor();

            });
        });
    }
};

/** 술 정보 가져오기 */
function fnAjaxLiquor(){

    let divObjLiquor = document.querySelectorAll('.liquor');
    //console.log("liquor size: " + divObjLiquor.length);
    for (let liq of divObjLiquor) {
        liq.addEventListener("click", function(){
            // HTML: 케밥 표기법(kebab-case) - data-liq-id
            // JS: 카멜 표기법 - liqId
            //console.log("liquor id: " + liq.dataset.liqId);
            ajaxAPI('/museum/liq?liqId=' + liq.dataset.liqId, null, "GET").then(response => {
                console.log("response: " , response);
                divObjLiqNm.innerHTML = "";
                divObjLiqMemo.innerHTML  = "";
                divObjLiqNm.innerHTML = response[0].liqNm;
                divObjLiqMemo.innerHTML = response[0].liqMemo;
            });
        });
    }


};
