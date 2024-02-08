"use strict";
/*
File Name      : commWrite.js
Program Name   : 게시판 등록, 수정화면
Draft Author   :
Draft Date     : 2023.12.04
*/

document.getElementById("save").addEventListener("click", function() {
    // "bard_cn" 에디터의 내용을 업데이트
    oEditors.getById["bard_cn"].exec("UPDATE_CONTENTS_FIELD", []);

    // 폼 제출
    document.getElementById("frm").submit();
});