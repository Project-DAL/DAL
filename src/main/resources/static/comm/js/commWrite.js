"use strict";
/*
File Name      : commWrite.js
Program Name   : 게시판 등록, 수정화면
Draft Author   :
Draft Date     : 2023.12.04
*/

document.addEventListener('DOMContentLoaded', function() {
    fnAjaxWrite();
});

function fnAjaxWrite() {
    let bardId = document.getElementById("bardId").value;
    if (bardId !== null && bardId !== "") {
       let jsonData = {
           bardId: bardId
       }

       ajaxAPI("/comm/commAjaxView", jsonData, "POST").then(response => {
           document.getElementById("bardTit").value = ""
           oEditors.getById["bardCn"].exec("SET_IR", [""]);

           document.getElementById("bardTit").value = response.bardTit;
           oEditors.getById["bardCn"].exec("PASTE_HTML", [response.bardCn]);
       });
    }
}


document.getElementById("save").addEventListener("click", function() {
    oEditors.getById["bardCn"].exec("UPDATE_CONTENTS_FIELD", []);

    // 폼 제출
    document.getElementById("frm").submit();
});