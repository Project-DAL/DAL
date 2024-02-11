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

    document.getElementById('0').classList.add('selected');

    let bardType = event.currentTarget.id;
    if (bardType) {
        document.querySelectorAll('.ct_left-menu ul').forEach(function (ul) {
            ul.classList.remove('selected');
        });

        document.getElementById(bardType).classList.add('selected');

    }else if(!bardType) {
       bardType = 0;
    }



    let searchKeyword = document.getElementById('searchKeyword').value;
    let searchValue = document.getElementById('searchValue').value;

    console.log("bardType   " + bardType)
     console.log("searchKeyword    " + searchKeyword)
      console.log("searchValue    " + searchValue)

   let jsonData = {
       bardType: bardType,
       searchKeyword: searchKeyword,
       searchValue: searchValue
   }

   ajaxAPI('/comm/commAjaxList', jsonData, "POST").then(response => {

        document.querySelector("#fieldListBody").innerHTML = "";
        let element = document.querySelector("#fieldListBody");

        if (response.length > 0) {
            response.forEach(function (result, index) {
                let template = `
                    <tr>
                        <td><p>${result.bardId}</p></td>
                        <td><p>${result.bardTit}</p></td>
                        <td><p>${result.bardCn}</p></td>
                        <td><p>${result.userId}</p></td>
                        <td><p>${result.bardCnt}</p></td>
                        <td><p>${result.bardRdate}</p></td>
                    </tr>
                `;
                element.insertAdjacentHTML('beforeend', template);

                let rows = document.querySelectorAll("tr");

                rows.forEach(function(row) {
                    row.addEventListener("click", function() {
                        var bardId = row.cells[0].textContent;
                        window.location.href = "/comm/commView?bardId=" + bardId;
                    });
                });

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
	document.getElementById("REG").addEventListener('click',function() {
    	window.location = '/comm/commWrite';
    });
}


