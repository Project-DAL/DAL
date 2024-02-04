"use strict";

let divObjLiqType1 = document.getElementById("liq-typeList1");  // 술 대분류
let divObjLiqType2 = document.getElementById("liq-typeList2");  // 술 중분류
let divObjLiqs = document.getElementById("liq-list");           // 술 소분류 - 술
let divObjLiqMsg = document.getElementById("liq-msg");
let divObjJujeong = document.getElementById("liq-jujeong");
let divObjBalhyo = document.getElementById("liq-balhyo");
let divObjJeungru = document.getElementById("liq-jeungru");
let divObjEtc = document.getElementById("liq-etc");

/** Initialize */
document.addEventListener('DOMContentLoaded', function() {
    console.log("DOMContent");
    /* CRUD 버튼 이벤트 등록 */
    addEventListenerCRUDBtn();

    addEventListenerType();
});

/** CRUD 버튼 이벤트 등록 */
function addEventListenerCRUDBtn(){

}

/** 술 대분류 선택에 따른 중분류 선택*/
function addEventListenerType(){
    // 중분류 출력
    divObjJujeong.addEventListener("click", function (){
       console.log("주정 선택");

        divObjLiqType2.innerHTML = "";
        divObjLiqs.innerHTML = "";
        divObjLiqMsg.innerHTML = "* 주정: 녹말 또는 당분이 포함된 재료를 발효시켜 알코올분 85도 이상으로 증류한 것";
    });
    divObjBalhyo.addEventListener("click", function (){
        console.log("발효주류 선택");

        divObjLiqType2.innerHTML = "";
        divObjLiqs.innerHTML = "";
        divObjLiqType2.insertAdjacentHTML('beforeend', `
            <div>탁주</div>
            <div>약주</div>
            <div>청주</div>
            <div>맥주</div>
            <div>과실주</div>
        `);
    });
    divObjJeungru.addEventListener("click", function (){
        console.log("증류주류 선택");

        divObjLiqType2.innerHTML = "";
        divObjLiqs.innerHTML = "";
        divObjLiqType2.insertAdjacentHTML('beforeend', `
            <div>소주</div>
            <div>위스키</div>
            <div>브랜디</div>
            <div>일반증류주</div>
            <div>리큐르</div>
        `);
    });
    divObjEtc.addEventListener("click", function (){
        console.log("증류주류 선택");

        divObjLiqType2.innerHTML = "";
        divObjLiqs.innerHTML = "";
    });
};