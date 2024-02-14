/*
파일명        : myAsk.js
최초 작성자    : 박제형
최초 작성날짜   : 2024.02.14
*/

"use strict";

let naverTalk = document.getElementById('naverTalk');
let kakaoAsk = document.getElementById('kakaoAsk');





document.addEventListener('DOMContentLoaded', function(){
    addEventListenerBtn();

})


function addEventListenerBtn(){

    naverTalk.addEventListener('click', fnNaverTalk);
    kakaoAsk.addEventListener('click', fnKakaoAsk);
}



function fnKakaoAsk(){

    /* sdk 초기화 */
/*
    Kakao.init("663e42ce0c9aff3c878dcb5e58b0c164");
*/

    /* sdk 초기화 여부 판단 */
    Kakao.isInitialized();


    console.log("CCCCCLLLIIKKKCKCK");


    Kakao.Channel.chat({
        channelPublicId: '_KLGJG',
    });




}



function fnNaverTalk(){

/*
    window.open("https://kakao.com/ask/qa/qa?qa_id=1234567890");
*/

}
