"use strict";
/*
File Name      : preferTest.js
Program Name   : 술 MBTI 화면
Draft Author   :
Draft Date     : 2024.01.01
*/

function fnBtn(srvQueId) {
    if (srvQueId === 'start') {
        let startElements = document.querySelectorAll(".prefer-row1");
        startElements.forEach(function(startElement) {
            startElement.style.display = "";
        });

        for (let i = 2; i <= 5; i++) {
            let otherElements = document.querySelectorAll(".prefer-row" + i);
            otherElements.forEach(function(element) {
                element.style.display = "none";
            });
        }
    } else {

        let startElements = document.querySelectorAll(".prefer-row1");
        startElements.forEach(function(startElement) {
            startElement.style.display = "none";
        });

        for (let i = 1; i <= 5; i++) {
            let elements = document.querySelectorAll(".prefer-row" + (i + 1));

            elements.forEach(function(element) {
                if (i === parseInt(srvQueId)) {
                    element.style.display = "";
                } else {
                    element.style.display = "none";
                }
            });
        }
    }
}


