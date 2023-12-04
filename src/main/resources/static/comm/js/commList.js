"use strict";
/*
File Name      : commList.js
Program Name   : 게시판 목록화면
Draft Author   : Pulunick
Draft Date     : 2023.12.04
*/

/* 1. Variables *******************************************************************************************************/

/* 1.1 전역변수 */
let iSelectedId     = null; // 선택된 행의 ID

/* 1.2 Page Navigation Variables */
let jsonPageNavigation = {
    currentPageNo: 1,               // 현재 페이지 번호
    recordCountPerPage: 9,          // 페이지당 목록 갯수
    totalRecordCount: 0,            // 전체 게시물 갯수
    targetObjArea: "pagination",    // 생성될 객체
    searchFunction: "fnAjaxList"    // 검색 함수
}


/* 2. Functions - Initialize ******************************************************************************************/
/* 2.1 Initialize */
document.addEventListener('DOMContentLoaded', function() {
    /** 1) CRUD 버튼 이벤트 등록 */
    addEventListenerCRUDBtn()

    /** 2) Search Condition 이벤트 등록 */
    addEventListenerSearchCondition();

    /** 3) 최초 목록조회 실행 */
    fnAjaxList();
});

/* 3. Functions - Process(CRUD) ***************************************************************************************/

/* 3.1 fnAjaxList() - 목록조회 */
function fnAjaxList() {
    /** 1) Search Condition - jsonParam 세팅 */
    jsonParam.pageStart  = jsonPageNavigation.recordCountPerPage * (jsonPageNavigation.currentPageNo - 1);  // 페이지 시작
    jsonParam.pageSize   = jsonPageNavigation.recordCountPerPage;                                           // 페이지 사이즈
    jsonParam.searchType = selectObjSearchType.value;                                                       // 검색 유형
    jsonParam.searchText = inputObjSearchText.value;                                                        // 검색어

    /** 2) Ajax 요청 실행 */
    Common.ajax(strListUrl, jsonParam, function(result) {


        /** 2-1) json 요청결과를 파싱하여 전역변수에 저장 */
        jsonGridList = result.list;

        /** 2-2) json 요청결과를 파싱하여 전체 게시물 수를 페이지 객체에 저장 */
        jsonPageNavigation.totalRecordCount = result.count;

        /** 2-3) 게시물 총 건수 화면에 출력 */
        spanObjTotalCount.innerText = result.count;

        /** 2-4) 목록 HTML을 생성하여 Append */
        appendGridListHtml();

        /** 2-5) 페이지 네비게이션 HTML을 생성하여 Append */
        appendPageNavigationHtml();
    });
}

/* 4. Functions - Event Listener **************************************************************************************/

/*  4.1 Event Listener - Process(CRUD) Button */
function addEventListenerCRUDBtn() {
    btnList.addEventListener('click', function () {
        jsonPageNavigation.currentPageNo = 1;
        fnAjaxList();
    });  // 검색
    if(btnInsert) btnInsert.addEventListener('click', fnInsert);  // 등록
    if(btnUpdate) btnUpdate.addEventListener('click', fnUpdate);  // 수정
    if(btnDelete) btnDelete.addEventListener('click', fnDelete);  // 삭제
    btnReset.addEventListener('click', fnReset);    // 초기화
}

/*  4.2 Event Listener - Search Condition Select Input */
function addEventListenerSearchCondition() {
    /** 1) 검색어 입력항목에서 엔터키를 누를 경우 목록조회 실행 */
    inputObjSearchText.addEventListener('keyup', function(event) {
        if(event.key === 'Enter') {
            jsonPageNavigation.currentPageNo = 1;
            fnAjaxList();
        }
    });
}

/* 5. Functions - Html Creator ****************************************************************************************/
/* 5.1 appendGridListHtml() - 목록 html 생성 */
function appendGridListHtml() {

    console.log("here1");
    console.log("jsonGridList: ", jsonGridList);
    console.log("jsonGridList.length: " + jsonGridList.length);

    /** 1) 각 btn, inputText, label, editor 값 초기화 */
    iSelectedId                 = '';       // 선택된 ID 초기화
    thObjBardSbjt.innerHTML     = '';       // 게시물 제목 초기화
    tbodyObjGridArea.innerHTML  = '';       // 게시물 목록 HTML 초기화
    divObjFile.innerText = '';              // 파일 목록 초기화
    spanObjFileCount.innerHTML = 0;         // 파일 총수 초기화

    /** 2) 목록 생성 */
    if(jsonGridList.length > 0) {

        console.log("here2");

        for(let item of jsonGridList) {

            console.log("here3");

            /** 2-1) New Item에 대한 image html 생성 */
            if(item.isNew == 'N')       htmlStrImg = '<img src="/cmmn/img/icon_board_new.png" alt="">';
            else if(item.isNew == 'U')  htmlStrImg = '<img src="/cmmn/img/icon_board_mmnew.png" alt="">';
            else                        htmlStrImg = '';

            /** 2-2) gridArea에 <tr> 요소생성 및 추가 */
            tbodyObjGridArea.insertAdjacentHTML('beforeend', `
                <tr>
                    <td class="label-td"><input type="checkbox" name="chk" class="ck"></td>
                    <td class="label-td left"><div class="ellp">${htmlStrImg} ${item.bardSbjt}</div></td>
                    <td class="label-td">${item.rgstId}</td>
                    <td class="label-td">${item.rgstDt}</td>
                </tr>`);

            /** 2-3) gridArea에 Event Listener 등록 */
            tbodyObjGridArea.querySelector('tr:last-child').addEventListener('click', function() {
                /** 2-3-1) 전체 checkbox cheked 해제 */
                tbodyObjGridArea.querySelectorAll('input[type="checkbox"]')
                    .forEach((checkbox) => { checkbox.checked = false; })

                /** 2-3-2) 전체 checkbox 하일라이트 calss 해제 */
                tbodyObjGridArea.querySelectorAll('tr')
                    .forEach((tr) => { tr.classList.remove('active'); });

                /** 2-3-3) 현재 행 체크박스 하이라이트 설정 */
                this.querySelector('input[type="checkbox"]').checked = true;
                this.classList.add('active');

                /** 2-3-4) 전역변수로 선택된 item 번호 세팅 */
                iSelectedId = item.bardId;

                if(inputObjGblAuthCd.value == "USER"){
                    btnUpdate.style.display     = 'none';   // 수정버튼 숨김
                    btnDelete.style.display     = 'none';   // 삭제버튼 숨김
                }else {
                    btnUpdate.style.display     = 'inline-block';   // 수정버튼
                    btnDelete.style.display     = 'inline-block';   // 삭제버튼
                }


                /** 2-3-5) 상세조회 실행 */
                fnView();
            });
        }
        /** 3) gridArea에 검색결과 없음 <tr> 추가 */
    } else {
        tbodyObjGridArea.insertAdjacentHTML('beforeend', `
            <tr class="label-td"><td colspan="4">검색결과가 존재하지 않습니다.</td></tr>`);
    }

    /** 3) 상세내용 초기화 */
    let iframeContent = iframeObjbardCnFrame.contentWindow.document.getElementById('contents');
    if(iframeContent) iframeContent.innerHTML = ''; // iframe 페이지가 로딩되기전에 실행되면 오류가 발생하므로 체크후 실행
}

/* 5.2 appendPageNavigationHtml() - 페이지 네비게이션 생성 */
function appendPageNavigationHtml() {

    if(spanObjTotalCount.innerText > 0) {
        divObjPagination.style.display = '';
    }
    else {
        divObjPagination.style.display = 'none';
    }

    Pagination.appendPagination(jsonPageNavigation);
}

/* 5.3 appendViewHtml() - 상세정보 생성 */
function appendViewHtml() {
    /** 1) 지역변수 선언 (주의: 예외적인 경우에만 선언) */
    let divObjBardCn = iframeObjbardCnFrame.contentWindow.document.getElementById('contents');

    /** 2) 값 바인딩 및 버튼 설정 */
    iSelectedId                 = jsonView.bardId;      // 선택된 행의 ID 저장
    thObjBardSbjt.innerHTML     = jsonView.bardSbjt;    // 게시물 제목 설정

    /** 3) 내용을 IFRAME 내부에 있는 객체에 바인딩 */
    divObjBardCn.innerHTML = Common.unescapeHTML(jsonView.bardCn);

    /** 4) 스크롤바 맨위로 이동 */
    iframeObjbardCnFrame.contentWindow.scrollTo(0, 0);

    /** 5) 첨부파일 html append */
    appendFileListHtml();
}

/* 5.4 appendFileListHtml() - 첨부파일 생성 */
function appendFileListHtml() {

    /** 2) html 생성 객체 초기화 */
    htmlStrDiv = '';
    divObjFile.innerText = '';
    spanObjFileCount.innerHTML = 0;

    spanObjFileCount.innerHTML = jsonFileList.length;
    /** 3) 첨부파일 html 생성 및 click 이벤트 등록 */
    if(jsonFileList.length > 0) {

        jsonFileList.sort(function(a, b) {
            return a.fileId - b.fileId;
        });

        /** 1) 파일목록 테그 생성 */
        for(let item of jsonFileList) {

            spanObjFileCount.innerHTML = jsonFileList.length;

            let fileSizeInKB = Math.floor(item.fileSize / 1024);
            let formattedSize = fileSizeInKB.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',') + ' KB';

            htmlStrDiv += `<ul>
                              <li onclick="Common.fileDown(${item.fileId})">${item.orgnFileNm + ((item.fileExt !== '') ? '.' + item.fileExt : '')}</li>
                              <li data="size" style="cursor: default;">${formattedSize}</li>
                              <i onclick="Common.fileDown(${item.fileId})" id="saveFile" class="material-icons saveFile" style="cursor: pointer;">save_alt</i>
                            </ul>`;
        }

        /** 2) 첨부파일 html을 상세정보 영역에 append */
        divObjFile.insertAdjacentHTML('beforeend', `${htmlStrDiv}`);
    }
}