"use strict";
/*
File Name      : commList.js
Program Name   : 게시판 목록화면
Draft Author   :
Draft Date     : 2023.12.04
*/

// document.addEventListener
document.addEventListener('DOMContentLoaded', function() {
  fnAjaxCommList();
  fnBtn();
});

// function
function fnAjaxCommList() {
   let rows = document.querySelectorAll("tr[data-bardId]");

   rows.forEach(function(row) {
       row.addEventListener("click", function() {
           let bardId = row.getAttribute("data-bardId");
           window.location.href = "/comm/commView?bardId=" + bardId;
       });
   });

}

function fnBtn() {
	document.getElementById("REG").addEventListener('click',function() {
    	window.location = '/comm/commWrite';
    });
}


