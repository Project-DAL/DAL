"use strict";
/*
File Name      : commWrite.js
Program Name   : 게시판 등록화면
Draft Author   :
Draft Date     : 2023.12.04
*/


    let oEditors = []

    smartEditor = function() {
      console.log("Naver SmartEditor")
      nhn.husky.EZCreator.createInIFrame({
        oAppRef: oEditors,
        elPlaceHolder: "editorTxt",
        sSkinURI: "/smarteditor/SmartEditor2Skin.html",
        fCreator: "createSEditor2"
      })
    }

   document.addEventListener('DOMContentLoaded', function() {
        smartEditor();
    });
