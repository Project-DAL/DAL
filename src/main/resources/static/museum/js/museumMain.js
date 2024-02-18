"use strict";

let divObjLiqType = document.querySelectorAll('.liq-type');
let divObjLiqList = document.getElementById("liq-list");
let divObjLiqNm = document.getElementById("liq-info-nm");
let divObjLiqMemo = document.getElementById("liq-info-memo");
let imgObjLiqImg = document.getElementById("liq-img");
let divObjLiqAroma = document.getElementById("liq-aroma");
let divObjLiqBody = document.getElementById("liq-body");
let divObjSmooth = document.getElementById("liq-smooth");


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
    let divObjLiquor = document.querySelectorAll('.liquor');

    //console.log("addEventListenerType");
    for(let type of divObjLiqType) {
        type.addEventListener("click", function(){
            console.log("type clicked");
            //console.log("data-value: " + type.dataset.value);
            /* 분류 선택에 따른 술 목록 가져오기 */
            ajaxAPI('/museum/liqList?liqType=' + type.dataset.value, null, "GET").then(response => {
               console.log("response: " , response);

                divObjLiqList.innerHTML = "";
                for (let i = 0; i < response.liqList.length; i++) {
                    // 새로운 .liquor 요소를 생성하고 내부에 내용을 추가합니다.
                    let newLiquorElement = document.createElement('div');
                    newLiquorElement.classList.add('liquor');
                    newLiquorElement.dataset.liqId = response.liqList[i].liqId;
                    //newLiquorElement.textContent = response.liqList[i].liqNm;

                    // 이미지 요소 생성 및 설정
                    let imgElement = document.createElement('img');
                    imgElement.src = 'data:image/jpeg;base64,' + response.base64Images[i]; // 각 이미지에 맞는 base64 이미지 추가

                    // .liquor 요소 안에 이미지 요소 추가
                    newLiquorElement.appendChild(imgElement);

                    // .liquor 요소를 divObjLiqList에 추가합니다.
                    divObjLiqList.appendChild(newLiquorElement);
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
            console.log("liquor click");
            // 모든 .liquor 요소에서 clicked 클래스를 제거합니다.
            divObjLiquor.forEach(function(element) {
                element.classList.remove('clicked');
            });
            // 현재 클릭된 요소에 clicked 클래스를 추가합니다.
            this.classList.add('clicked');

            // HTML: 케밥 표기법(kebab-case) - data-liq-id
            // JS: 카멜 표기법 - liqId
            //console.log("liquor id: " + liq.dataset.liqId);
            ajaxAPI('/museum/liq?liqId=' + liq.dataset.liqId, null, "GET").then(response => {
                console.log("response2: " , response);
                divObjLiqNm.innerHTML = "";
                divObjLiqMemo.innerHTML  = "";
                divObjLiqNm.innerHTML = response.liq[0].liqNm;
                divObjLiqMemo.innerHTML = response.liq[0].liqMemo;

                let aromaString = liqCountString(response.liq[0].liqAroma);
                let bodyString = liqCountString(response.liq[0].liqBody);
                let SmoothString = liqCountString(response.liq[0].liqSmooth);

                divObjLiqAroma.innerHTML = aromaString;
                divObjLiqBody.innerHTML = bodyString;
                divObjSmooth.innerHTML = SmoothString;

                imgObjLiqImg.src = 'data:image/jpeg;base64,' + response.base64Images[0];
            });
        });
    }


};

/** 향, 바디감, 목넘김 점수 */
function liqCountString(count) {
    let result = '';
    for (let i = 0; i < 5; i++) {
        if (i < count) {
            result += '■ '; // 색이 있는 모형
        } else {
            result += '□ '; // 색이 없는 모형
        }
    }
    return result.trim(); // 마지막에 공백을 제거하여 출력
}