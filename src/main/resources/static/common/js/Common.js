"use strict";
/*
©Copyright 2023. DAL. All rights reserved.

File Name      : Common.js
Program Name   : 공통기능 Javascript
Description    : 공통기능 Javascript
Draft Author   : 이원정
Draft Date     : 2023.11.24

Revision History
Ver.  Date         Revised By   Description
------------------------------------------------------------------------------------------------------------------------
0.1   2023.11.24   이원정        최초작성
----------------------------------------------------------------------------------------------------------------------->
*/

/*
Contents


*/

/* 1. Header Drop down  **********************************************************************************************************/
document.addEventListener('DOMContentLoaded', function() {
    console.log("common js");

    let divObjDropdown = document.getElementById("dropdown");   // 드롭다운
    let divObjDropdownMenu = document.getElementById("dropdownMenu");

    divObjDropdown.addEventListener("click", function (){
        if(divObjDropdownMenu.style.display === "") {
            divObjDropdownMenu.style.display = "none";
        }else {
            divObjDropdownMenu.style.display = "";
        }
    });
});
