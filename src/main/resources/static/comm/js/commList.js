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

/*   document.getElementById('0').classList.add('selected');

    let bardType = event.currentTarget.id;
    if (bardType) {
        document.querySelectorAll('.ct_left-menu ul').forEach(function (ul) {
            ul.classList.remove('selected');
        });

        document.getElementById(bardType).classList.add('selected');

    }else if(!bardType) {
       bardType = 0;
    }*/

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


