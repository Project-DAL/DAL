"use strict";
/*
File Name      : commWrite.js
Program Name   : 게시판 등록, 수정화면
Draft Author   :
Draft Date     : 2023.12.04
*/

document.addEventListener('DOMContentLoaded', function() {
    fnAjaxCommView();
    fnBtn();
});


function fnAjaxCommView() {
   let bardId = document.getElementById("bardId").value;

   ajaxAPI('/comm/commAjaxViewLike?bardId='+bardId, null, "GET").then(response => {});

   let jsonData = {
       bardId: bardId
   }

   ajaxAPI("/comm/commAjaxView", jsonData, "POST").then(response => {
        document.querySelector("#fieldListBody").innerHTML = "";
        let element = document.querySelector("#fieldListBody");

        let template = `
                <tr>
                    <th>작성자</th>
                    <td>${response.userId}</td>
                </tr>
                <tr>
                    <th>제목</th>
                    <td>${response.bardTit}</td>
                </tr>
                <tr>
                    <th>등록일</th>
                    <td>${response.bardRdate}</td>
                </tr>
                <tr>
                    <th>조회수</th>
                    <td>${response.bardCnt}</td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td>${response.bardCn}</td>
                </tr>
        `;
        element.insertAdjacentHTML('beforeend', template);
   });

   console.log(jsonData)

   ajaxAPI("/comm/commAjaxReplyView", jsonData, "POST").then(response => {
        document.querySelector("#fieldReplyBody").innerHTML = "";
        let element = document.querySelector("#fieldReplyBody");

        if (response.length > 0) {
            response.forEach(function (result, index) {
                let template = `
                    <div class="reply">
                        <p>Reply ID: ${result.replyId}</p>
                        <p>${result.replyCn}</p>
                        <p>Date: ${result.replyRdate}</p>
                    </div>
                `;
                element.insertAdjacentHTML('beforeend', template);

            });
        }else {
             let template = `
                    <td colspan="4"><p>등록된 글이 없습니다.</p></td>
                `;
                element.insertAdjacentHTML('beforeend', template);
        }
   });



}

function fnBtn() {
	document.getElementById("btnUpdate").addEventListener('click',function() {
	    let bardId = document.getElementById("bardId").value
        window.location = '/comm/commWrite?bardId=' + bardId;
    });

   	document.getElementById("btnDelete").addEventListener('click',function() {
   	    let bardId = document.getElementById("bardId").value
           window.location = '/comm/commAjaxWriteDelete?bardId=' + bardId;
    });

    document.getElementById("save").addEventListener("click", function() {
        document.getElementById("frm").submit();
    });

}