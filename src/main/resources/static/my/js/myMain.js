/*
    파일명        : MyMain.js
    최초 작성자    : 박제형
    최초 작성날짜   : 2024.02.07
*/

"use strict";

let orderDateValue = document.getElementById('order-date');
let orderSttsValue = document.getElementById('order-stts');
let orderDateSelect = document.querySelector('.order-date');
let selectedOption = orderDateSelect.options[orderDateSelect.selectedIndex].text;
let orderInfo = document.getElementById("order-info");
let btnEnter = document.getElementById("btnEnter");



document.addEventListener('DOMContentLoaded', function(){
    addEventListenerBtn();
    fnAjaxList();
})



function addEventListenerBtn(){
    btnEnter.addEventListener("click", fnselectData);
}



function fnselectData(){
    console.log("fnselecData clicked");
    console.log('orderDateValue: ' + orderDateValue.value);
    console.log('orderDateValue type: ' + typeof orderDateValue.value);

    console.log("orderSttsValue" + orderSttsValue.value);

    // 기존 주문 목록 비우기
    orderInfo.innerHTML = "";

    ajaxAPI('/my/MyPageMain/orderValue?odDateValue=' + orderDateValue.value + '&odSttsValue=' +orderSttsValue.value, null, "GET").then(response => {

        for(let i = 0; i < response.length; i++) {
            // 주문일자
            let rdateLength = response[i].order_rdates.split(",").length;
            // 주문 상품 이름
            let prodTits = response[i].prod_tits;

            let orderSttsTxt = "";

            if (response[i].order_stts === 1) {
                orderSttsTxt = "입금대기";
            } else if (response[i].order_stts === 2) {
                orderSttsTxt = "결제완료";
            }else if (response[i].order_stts === 3) {
                orderSttsTxt = "배송준비중";
            }else if (response[i].order_stts === 4) {
                orderSttsTxt = "배송중";
            }else if (response[i].order_stts === 5) {
                orderSttsTxt = "배송완료";
            }else if (response[i].order_stts === 6) {
                orderSttsTxt = "구매확정";
            }else if (response[i].order_stts === 7) {
                orderSttsTxt = "취소";
            }else if (response[i].order_stts === 8) {
                orderSttsTxt = "교환";
            }else if (response[i].order_stts === 9) {
                orderSttsTxt = "반품";
            }



            // 주문번호만큼 주문 박스 생성
            orderInfo.insertAdjacentHTML('beforeend', `
                    <div class="order-box">
                        <div class="order-info-spec">
                            <div class="order-info-spec1">
                                <div>${response[i].order_rdate}</div>
                                <div class="order-info-spec-stts">${orderSttsTxt}</div>
                            </div>
                            <div class="order-info-spec-view">배송조회</div>
                        </div>    
                    </div> 
            `);

            // 주문번호 1번 + 각 주문 제품
            for (let j = 0; j < rdateLength; j++) {
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



function fnAjaxList() {

    let jsonData = {}

    // 기존 주문 목록 비우기
    orderInfo.innerHTML = "";

    ajaxAPI("/my/MyPageMain/order", null, "GET").then(response => {

        for(let i = 0; i < response.length; i++) {
            // 주문일자
            let rdateLength = response[i].order_rdates.split(",").length;
            // 주문 상품 이름
            let prodTits = response[i].prod_tits;

            let orderSttsTxt = "";

            if (response[i].order_stts === 1) {
                orderSttsTxt = "입금대기";
            } else if (response[i].order_stts === 2) {
                orderSttsTxt = "결제완료";
            }else if (response[i].order_stts === 3) {
                orderSttsTxt = "배송준비중";
            }else if (response[i].order_stts === 4) {
                orderSttsTxt = "배송중";
            }else if (response[i].order_stts === 5) {
                orderSttsTxt = "배송완료";
            }else if (response[i].order_stts === 6) {
                orderSttsTxt = "구매확정";
            }else if (response[i].order_stts === 7) {
                orderSttsTxt = "취소";
            }else if (response[i].order_stts === 8) {
                orderSttsTxt = "교환";
            }else if (response[i].order_stts === 9) {
                orderSttsTxt = "반품";
            }

            // 주문번호만큼 주문 박스 생성
            orderInfo.insertAdjacentHTML('beforeend', `
                    <div class="order-box">
                        <div class="order-info-spec">
                            <div class="order-info-spec1">
                                <div>${response[i].order_rdate}</div>
                                <div class="order-info-spec-stts">${orderSttsTxt}</div>
                            </div>
                            <div class="order-info-spec-view">배송조회</div>
                        </div>    
                    </div> 
                `);

            // 주문번호 1번 + 각 주문 제품
            for (let j = 0; j < rdateLength; j++) {
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

