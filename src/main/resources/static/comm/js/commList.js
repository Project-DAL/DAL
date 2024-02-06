"use strict";
/*
File Name      : commList.js
Program Name   : 게시판 목록화면
Draft Author   :
Draft Date     : 2023.12.04
*/

/* 1. Variables *******************************************************************************************************/

/* 2. Functions - Initialize ******************************************************************************************/
/* 2.1 Initialize */
document.addEventListener('DOMContentLoaded', function() {
  fnAjaxCommList();
  fnBtn();
});

/* 3. Functions - Process(CRUD) ***************************************************************************************/

/* 3.1 fnAjaxList() - 목록조회 */
function fnAjaxCommList() {

   ajaxAPI('/comm/commAjaxList', null, "GET").then(response => {

        document.querySelector("#fieldListBody").innerHTML = "";
        let element = document.querySelector("#fieldListBody");

        if (response.length > 0) {
            response.forEach(function (result, index) {
                let template = `
                    <td><p>${result.bard_id}</p></td>
                    <td><p>${result.bard_tit}</p></td>
                    <td><p>${result.bard_cn}</p></td>
                    <td><p>${result.bard_rdate}</p></td>
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

/* 4. Functions - Event Listener **************************************************************************************/
function fnBtn() {
	document.getElementById("REG").addEventListener('click',e=>{
    	window.location = '/comm/commWrite';
    });
}


/* 5. Functions - Html Creator ****************************************************************************************/



/* 5.4 appendFileListHtml() - 첨부파일 생성 */
