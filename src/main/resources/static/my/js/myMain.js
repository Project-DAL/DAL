/*
    파일명        : MyMain.js
    최초 작성자    : 박제형
    최초 작성날짜   : 2024.02.07
*/

"use strict";

let orderDateSelect = document.querySelector('.order-date');
let selectedOption = orderDateSelect.options[orderDateSelect.selectedIndex].text;
let orderInfo = document.getElementById("order-info");


document.addEventListener('DOMContentLoaded', function(){
    addEventListenerBtn();
    fnAjaxList();
})



function addEventListenerBtn(){
    orderDateSelect.addEventListener('change', function(){
        fnselectData(selectedOption);
    });
}



function fnselectData(){
    console.log('selectedOption: ' + selectedOption);
}



function fnAjaxList() {

    let jsonData = {}

    // 기존 주문 목록 비우기
    orderInfo.innerHTML = "";


    ajaxAPI("/my/MyPageMain/order", null, "GET").then(response => {
        console.log(response);
        console.log("response length: " + response.length); // 각 주문번호

        for(let i = 0; i < response.length; i++) {
            // 예를 들어 주문번호 1번
            console.log("총 주문번호: ", response);
            //console.log("prodTits: ", response[i].prod_tits);
            //console.log("rdate: " + response[i].order_rdates);
            //console.log("rdate length: " + response[i].order_rdates.split(",").length);
            // 주문일자
            let rdateLength = response[i].order_rdates.split(",").length;
            // 주문 상품 이름
            let prodTits = response[i].prod_tits;

            console.log("prodTits: ", prodTits);
            console.log("rdate: ", response[i].order_rdates.split(","));
            console.log("rdate2: ", response[i].order_rdates.split(",")[0]);

            // 주문번호만큼 주문 박스 생성
            orderInfo.insertAdjacentHTML('beforeend', `
                    <div class="order-box">
                        <div class="order-info-spec">
                            <div>${response[i].order_rdates.split(",")[0]}</div>
                            <div class="order-info-spec-stts">${response[i].order_sttss.split(",")[0]}</div>
                            <div class="order-info-spec-view">배송조회</div>
                        </div>    
                    </div> 
                  
                `);

            // 주문번호 1번 + 각 주문 제품
            for (let j = 0; j < rdateLength; j++) {
                console.log("prodTit: " + prodTits.split(",")[j]);
                document.querySelectorAll('.order-box')[i].insertAdjacentHTML('beforeend', `
                    <div class="order-info-spec-detail">
                        <div class="order-info-spec-pic">
                            <img class="order-img" th:src="@{/my/img/soju.png}" alt="">
                        </div>
                        <div class="order-info-spec-specinfo">
                            <p>${prodTits.split(",")[j]}</p>
                            <div class="f-flex">
                               <p>${response[i].od_sell_prices.split(",")[j]}</p><span>원</span>
                            </div>
                            <div class="f-flex">
                               <span>총</span><p>${response[i].od_cnts.split(",")[j]}</p><span>개</span>
                            </div>
                        </div>
                    </div>
                `);
            }
        }

    });
}

