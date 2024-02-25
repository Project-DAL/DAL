"use strict";
/*
File Name      : preferTest.js
Program Name   : 술 MBTI 화면
Draft Author   :
Draft Date     : 2024.01.01
*/

function fnClick(srvQueId) {

    let buttonCss = document.querySelectorAll('input[name="testType' + srvQueId + '"]');

    buttonCss.forEach(function(bc) {
       //모든 라디오 css 제거
       bc.parentElement.classList.remove('selected-radio');

       //선택된 라디오 css 추가
       if (bc.checked) {
           bc.parentElement.classList.add('selected-radio');
       }
    });
}

function fnBtn(srvQueId) {
    if (srvQueId === 'start') {
        let statusBar = document.querySelector(".status");
            statusBar.style.display = "";

        let startElement = document.querySelector(".prefer-row0");
            startElement.style.display = "none";

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
        //유효성 검사
        let selectedValue = document.querySelector('input[name="testType' + srvQueId + '"]:checked');

        if (!selectedValue) {
            alert("둘 중에 하나를 선택해주세요.");
            return;
        }else {
            // 진행바
            let progressBar = document.getElementById('progressBar');
            let currentWidth = parseFloat(progressBar.style.width) || 0;
            let newWidth = currentWidth + 25;

            if (newWidth <= 100) {
              progressBar.style.width = newWidth + '%';
            }
        }

        for (let i = 0; i <= 4; i++) {
            let elements = document.querySelectorAll(".prefer-row" + (i + 1));
            elements.forEach(function(element) {
                if (i === parseInt(srvQueId)) {
                    element.style.display = "";

                } else {
                    element.style.display = "none";
                }
            });
        }

        let thirdElement = document.querySelector(".prefer-row5:nth-child(3)");
        thirdElement.style.display = "none";

        if(srvQueId == 4) {
           document.getElementById("lastButton").style.display = "";
        }
    }
}

function fnResult() {
    let srvResCount = 1;

    for (var i = 1; i <= 5; i++) {
        var groupName = "testType" + i;
        var radioButtons = document.querySelectorAll('[name="' + groupName + '"]');

        radioButtons.forEach(function(radioButton) {
            if (radioButton.checked) {
                var radioValue = radioButton.value;
                console.log(groupName + ':', radioValue);

                if (radioValue === 'Y') {
                    srvResCount++;
                     console.log('srvResCount:', srvResCount);
                }

            }
        });
    }

    window.location.href = "/preferTest/preferResult?srvResId="+ srvResCount;
}







