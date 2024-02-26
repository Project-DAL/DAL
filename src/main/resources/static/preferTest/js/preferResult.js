"use strict";
/*
File Name      : preferTest.js
Program Name   : 술 MBTI 화면
Draft Author   :
Draft Date     : 2024.01.01
*/


function kakaoShare() {
  Kakao.Share.sendDefault({
    objectType: 'feed',
    content: {
      title: 'DAL',
      description: '당신은 어떤 유형인가요?',
      imageUrl: 'http://localhost:8282/preferTest/img/dalLogo.png',
      link: {
        mobileWebUrl: 'http://localhost:8282/preferTest/preferTest',
        webUrl: 'http://localhost:8282/preferTest/preferTest',
      },
    },
    itemContent: {
      titleImageUrl: 'http://localhost:8282/preferTest/img/dalLogo.png',
      titleImageText: '술 MBTI',
    },
    social: {
      likeCount: 10,
      commentCount: 20,
      sharedCount: 30,
    },
    buttons: [
      {
        title: '웹으로 이동',
        link: {
          mobileWebUrl: 'http://localhost:8282/preferTest/preferTest',
          webUrl: 'http://localhost:8282/preferTest/preferTest',
        },
      },
      {
        title: '앱으로 이동',
        link: {
          mobileWebUrl: 'http://localhost:8282/preferTest/preferTest',
          webUrl: 'http://localhost:8282/preferTest/preferTest',
        },
      },
    ],
  });
}



