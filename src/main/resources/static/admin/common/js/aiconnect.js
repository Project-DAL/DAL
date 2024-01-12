let GLOBAL_POPUP = []; // 팝업객체를 저장하기 위한 변수

window.addEventListener("DOMContentLoaded",  () =>  {
    // 팝업닫기 버튼 이벤트
    let popCloser = document.getElementById("pop-closer")
    if(popCloser) { document.getElementById("pop-closer").addEventListener("click", function() { window.close(); }); }

    // 달력 버튼설정
    for(let dateTime of document.getElementsByClassName('dateTimes')) {
        dateTime.addEventListener("click", function() { this.showPicker(); });
    }

    // 달력 지정기간 설정버튼 이벤트 설정
    for(let datePeriod of document.getElementsByClassName('datePeriods')) {
        datePeriod.addEventListener('click',  function () {
            let option = this.getAttribute("option");
            let period = this.getAttribute("period");
            let start = this.getAttribute("start");
            let end = this.getAttribute("end");

            if (start != null && start != '') document.getElementsByName(start)[0].value = aic.date(option, '-', period)
            if (end != null && end != '') {
                if (option == 'tmm' || option == 'TMM' || option == 'mm' || option == 'MM') { // 년월 유형일경우
                    document.getElementsByName(end)[0].value = aic.date('TMM', '-')
                } else { // 년월일 유형일 경우
                    document.getElementsByName(end)[0].value = aic.date('TD', '-')
                }
            }
        });
    }
    aic.periodsClickEvent();
});

window.addEventListener("beforeunload", function() {
    if(GLOBAL_POPUP != null) {
        for(let item in GLOBAL_POPUP) {
            //GLOBAL_POPUP[item].close();
        }
    }
});

const aic = {
    defaultMethod: "POST",
    currentTreeNode: null,
    selectedList: [],

    /**
     * form -> json string 변환
     * @param obj (ex) "#formMain"
     * @returns {{}}
     */
    json: function(obj) {
        let form = document.querySelector(obj);
        let formData = null;
        let serializeData = {};
        if(form == null) {
            aic.error("지정한 객체를 찾을 수 없습니다. (" + obj + ")");
        } else {
            try {
                formData = new FormData(form);
                for(let [key, value] of formData) {
                    let sel = form.querySelectorAll("[name=" + key + "]");
                    if(sel == null) sel = form.querySelectorAll("[id=" + key + "]");

                    if(sel.length > 1) {
                        if(serializeData[key] === undefined) serializeData[key] = [];
                        serializeData[key].push(value);
                    } else {
                        serializeData[key] = value;
                    }
                }
            } catch(e) {
                aic.error(e);
            }
        }
        return serializeData;
    },

    /**
     * post 방식 ajax 호출
     * @param url
     * @param body
     * @param fn
     */
    ajaxPost: function(url, body, fn) {
        this.ajax(url, body, fn, "POST");
    },

    /**
     * get 방식 ajax 호출
     * @param url
     * @param body
     * @param fn
     */
    ajaxGet: function(url, body, fn) {
        this.ajax(url, body, fn, "GET");
    },

    /**
     * 폼 또는 JSON 파라미터를 Stringify 하여 ajax 호출
     * (ex) json type body : { id : '1234', name : '1234' }
     * (ex) form type body : aic.json("#formData")
     * @param url
     * @param body
     * @param fn
     * @param method
     */
    ajax: function(url, body, fn, method='POST') {
        if(url == null) {
            aic.error("요청경로(URL)를 입력하세요. (" + url + ")");
        } else {
            let params = {
                cache: "no-cache",
                method: (method == null) ? "POST" : method,
                body: JSON.stringify(body),
                headers: {
                    "Content-Type": "application/json",
                    "Accept": "application/json; charset=UTF-8"
                }
            };

            try {
                aic.loading("start"); // 로딩바 표시

                fetch(url, params).
                then((response) => response.json()).
                then((data) => {
                    aic.loading("stop"); // 로딩바 숨김

                    if(data.status == 400) {
                        if (data.errors == null) {
                            aic.error(data.message);
                        } else {
                            if (data.errors.length > 0) {
                                let validMsg = "";
                                for (let tmpMsg of data.errors) {
                                    validMsg += tmpMsg.defaultMessage + "\r\n";
                                }
                                aic.warn(validMsg);
                            } else {
                                aic.error(data.message);
                            }
                        }
                    } else if(data.status == 405) {
                        aic.error(data.message);
                    } else if(data.status == 404) {
                        aic.error("요청경로(URL)를 찾을 수 없거나 잘못되었습니다. (" + url + ")");
                    } else if(data.status == 500) {
                        aic.error(data.message);
                    } else {
                        if(fn != null) {
                            if(typeof fn == "function") {
                                fn(data);
                            } else {
                                aic.error("지정된 후처리 함수를 찾을 수 없습니다. (" + fn + ")");
                            }
                        }
                    }
                }).
                catch((e) => {
                    aic.loading("stop"); // 로딩바 숨김
                    console.log(e);
                    aic.error(e);
                });
            } catch(ex) {
                aic.loading("stop"); // 로딩바 숨김
                console.log(ex);
            }
        }
    },

    /**
     * 폼을 JSON String 으로 Stringify 하지 않고 그대로 전송 (header 정보 없음)
     * (ex) 파일업로드 시에 사용
     * @param url
     * @param form
     * @param fn
     */
    ajaxForm: function(url, form, fn) {
        if(url == null) {
            aic.error("요청경로(URL)를 입력하세요. (" + url + ")");
        } else {
            let params = {
                cache: "no-cache",
                method: "POST",
                body: new FormData(document.querySelector(form))
            };

            fetch(url, params).
            then((response) => response.json()).
            then((data) => {
                if(data.status == 400) {
                    if (data.errors == null) {
                        aic.error(data.message);
                    } else {
                        if (data.errors.length > 0) {
                            let validMsg = "";
                            for (let tmpMsg of data.errors) {
                                validMsg += tmpMsg.defaultMessage + "\r\n";
                            }
                            aic.warn(validMsg);
                        } else {
                            aic.error(data.message);
                        }
                    }
                } else if(data.status == 405) {
                    aic.error(data.message);
                } else if(data.status == 404) {
                    aic.error("요청경로(URL)를 찾을 수 없거나 잘못되었습니다. (" + url + ")");
                } else if(data.status == 500) {
                    aic.error(data.message);
                } else {
                    if(fn != null) {
                        if(typeof fn == "function") {
                            fn(data);
                        } else {
                            aic.error("지정된 후처리 함수를 찾을 수 없습니다. (" + fn + ")");
                        }
                    }
                }
            }).
            catch((e) => {
                console.log(e);
                aic.error(e);
            });
        }
    },

    excel: function(url, fileName, data) {
        if(url == null || url == "") {
            alert("요청경로가 누락되었습니다.");
            return;
        }
        if(fileName == null || fileName == "") {
            alert("파일명이 누락되었습니다.");
            return;
        }

        aic.loading("start");

        try {
            let xhr = new XMLHttpRequest();

            xhr.open("POST", url);
            xhr.setRequestHeader("Content-Type", "application/json");
            xhr.responseType = "arraybuffer";
            xhr.onload = function() {
                if(xhr.readyState == 4) {
                    aic.loading("stop");

                    if(xhr.status == 200) {
                        let arrayBuffer = xhr.response;

                        if(arrayBuffer) {
                            let blob    = new Blob([arrayBuffer], { type: "application/octetstream" });
                            let link    = document.createElement('a');

                            link.href       = window.URL.createObjectURL(blob);
                            link.download   = fileName + "_" + aic.now() + ".xlsx";

                            link.click();
                        }
                    } else if(xhr.status == 403) {
                        alert("접근이 거부되었습니다.");
                    } else if(xhr.status == 404) {
                        alert("요청경로를 찾을 수 없습니다.")
                    } else if(xhr.status == 500) {
                        alert("오류가 발생하였습니다.")
                    } else {
                        alert("오류가 발생하였습니다.")
                    }
                }
            };

            xhr.send(JSON.stringify(data));
        } catch(e) {
            aic.loading("stop");
            console.log(e);
        }
    },

    loading: function(type) {
        if(type.toUpperCase() == "START") {
            $(".aic-loading-bar").show();
        } else {
            $(".aic-loading-bar").hide();
        }
    },

    now: function() {
        let date    = new Date(); // Data 객체 생성
        let year    = date.getFullYear().toString(); // 년도 구하기
        let month   = date.getMonth() + 1; // 월 구하기
        let day     = date.getDate(); // 날짜 구하기
        let hour    = date.getHours(); // 시간 구하기
        let minites = date.getMinutes(); // 분 구하기
        let seconds = date.getSeconds(); // 초 구하기

        month   = month < 10 ? '0' + month.toString() : month.toString(); // 10월 미만 0 추가
        day     = day < 10 ? '0' + day.toString() : day.toString(); // 10일 미만 0 추가
        hour    = hour < 10 ? '0' + hour.toString() : hour.toString(); // 10시 미만 0 추가
        minites = minites < 10 ? '0' + minites.toString() : minites.toString(); // 10분 미만 0 추가
        seconds = seconds < 10 ? '0' + seconds.toString() : seconds.toString(); // 10초 미만 0 추가

        return year + month + day + hour + minites + seconds; // yyyymmddhhmmss 형식으로 리턴
    },

    /**
     * 첨부파일 선택 버튼 클릭 이벤트
     * @param index
     */
    fileAdd: function(index) {
        try { document.querySelector("#AIC_FOBJ" + index).click(); } catch(e) { aic.error(e); }
    },

    /**
     * 첨부파일 선택후 file 객체에 있는 파일명을 입력폼에 표시
     * @param index
     */
    fileAddBack: function(index) {
        try {
            let file 			= document.querySelector("#AIC_FOBJ" + index);
            let filePath 		= file.value;
            let filePathSplit 	= filePath.split('\\');
            let filePathLength 	= filePathSplit.length;
            let fileNameSplit 	= filePathSplit[filePathLength-1].split('.');
            let fileName 		= fileNameSplit[0];
            let fileExt         = (fileNameSplit.length > 1) ? fileNameSplit[1] : "";

            document.querySelector("#AIC_FNAME" + index).value = fileName + ((fileExt != "") ? "." + fileExt : "");
            document.querySelector("#AIC_FID" + index).value = "0";
        } catch(e) {
            aic.error(e);
        }
    },

    /**
     * 첨부된 파일 초기화
     * @param index
     */
    fileClear: function(index) {
        try {
            document.querySelector("#AIC_FNAME" + index).value = "";
            document.querySelector("#AIC_FID" + index).value = "";
        } catch(e) { aic.error(e); }
    },

    /**
     * 첨부파일 다운로드
     * @param index
     */
    fileDown: function(index) {
        try { window.location = "/file/download?fileId=" + document.querySelector("#AIC_FID" + index).value; } catch(e) { aic.error(e); }
    },

    /**
     * info logger
     * @param message
     */
    info: function(message) {
        console.log(message);
        alert("[정보]: " + message);
    },

    /**
     * warn logger
     * @param message
     */
    warn: function(message) {
        console.log(message);
        alert("[경고]: " + message);
    },

    /**
     * error logger
     * @param message
     */
    error: function(message) {
        console.log(message);
        alert("[오류]: " + message);
    },

    /** 사용 설명<br>
     * @table_id - [ table ]의 id 값
     * @list - [ table ]에 들어갈 값을 가진 객체 목록
     * @fn - tr에 걸린 OnClick 이벤트의 함수
     * @key - 함수 실행시, 함수에 전달할 tr의 값
     * @evt - 이벤트 유형, click/dblclick 등
     * writeList('shTable', shList, shFn, 'shKey')<br>
     * [ shTable ] 내부에 [ shList ] 의 각 객체들을 tr 별로 담게 된다. 각 tr은 클릭시 [ shFn(shKey) ]를 실행시키게 된다.
     * [ TD 테그속성 옵션 ]
     * 1. escape : true로 html을 작성하면 aic.unescapeHtml 함수실행결과가 화면에 표시(치환된 특수문자를 원래대로 표현)
     * */
    writeList : function(table_id, list, fn, key, evt) {
        let table = document.getElementById(table_id)
        while(table.rows[1]) {
            table.deleteRow(0);
        }
        let tr = table.querySelector('tr');
        tr.style.display = 'none';
        for(let i in list) {
            let view = list[i];
            table.insertRow(i);
            table.rows[i].innerHTML = tr.innerHTML;

            // 행선택 이벤트 추가
            table.rows[i].addEventListener('click', function() { aic.writeHighlight(table_id, table.rows[i]); });

            // 사용자 정의 이벤트 추가
            if(evt == null) evt = 'click';
            if(fn != null) {
                if(key != null) table.rows[i].addEventListener(evt, function() { fn(view[key]); });
                else table.rows[i].addEventListener(evt, function() { fn(view); });
            }

            for(let idx in view) {
                let td = table.rows[i].querySelector('*[name="' + idx +'"]')
                if(td != undefined) {
                    let value   = view[idx];
                    let escape  = td.getAttribute("escape");

                    if(escape == "true") value = aic.unescapeHtml(value); // 테그제거

                    td.setAttribute('value',    value);
                    td.setAttribute('alt',      value);
                    td.setAttribute('title',    value);
                    td.innerHTML = value;
                }
            }
        }
    },

    /**
     * 선택된 행 하이라이트
     * @param table_id
     * @param obj
     */
    writeHighlight: function(table_id, obj) {
        let prevSelectedList = this.selectedList[table_id];
        if(prevSelectedList != null) prevSelectedList.style.backgroundColor = "#fff";
        this.selectedList[table_id] = obj;
        this.selectedList[table_id].style.backgroundColor = "#f2f2f2";
    },

    /**
     * writeList로 생성된 테이블에서 선택된 행의 index 번째 값을 리턴
     * @param table_id
     * @param index
     */
    tableValue: function(table_id, index) {
        if(table_id == null) {
            aic.error("table id is null");
        } else if(index == null || index < 0) {
            aic.error("index is null");
        }

        let selectObj = this.selectedList[table_id];

        if(selectObj == null) return null;

        return selectObj.children[index].innerText;
    },

    grid: function(grid_id, data, fn, key, evt) {
        let grid    = document.getElementById(grid_id);
        let body    = document.createElement("div");
        let bodyPre = document.getElementById(grid_id + "_body");

        if(bodyPre != null) bodyPre.remove(); // 이전에 생성된 body 테그 삭제

        body.setAttribute("class", "com_grid_body");
        body.setAttribute("id", grid_id + "_body");

        grid.parentNode.appendChild(body);

        if(data == null) {
            fnNodata(body);
        } else {
            if(data.length > 0) {
                for(let i in data) {
                    let row = grid.cloneNode(true);

                    row.removeAttribute("id");
                    row.removeAttribute("class");
                    row.setAttribute("class", "com_grid_row");
                    row.addEventListener('click', function() { aic.gridActive(grid_id, row); }); // 행선택 CSS 이벤트

                    // 사용자정의 이벤트 추가
                    if(fn != null) {
                        if(key != null) row.addEventListener((evt == null) ? 'click' : evt, function() { fn(data[i][key]); });
                        else row.addEventListener((evt == null) ? 'click' : evt, function() { fn(); });
                    }
                    grid.appendChild(row);

                    for(let key in data[i]) {
                        let col = row.querySelector(".com_grid_row div[data-name=" + key + "]");
                        //let col = document.querySelector("div[id=" + grid_id + "] .com_grid_row div[data-name=" + key + "]");

                        if(col != null) {
                            let content = data[i][key];

                            // 2023.03.29 data-type이 count일 경우 천단위 콤마 및 건 표시
                            if(col.getAttribute("data-type") == "count") content = parseInt(content).toLocaleString() + "건";

                            if(col.dataset.escape) content = aic.unescapeHtml(content);

                            col.innerText =  content;
                            col.setAttribute('alt',   content);
                            col.setAttribute('title', content);
                        }
                    }
                    body.appendChild(row);
                }
            } else {
                fnNodata(body);
            }
        }

        function fnNodata(body) {
            let row = grid.cloneNode(true);
            let col = document.createElement("div");

            row.removeAttribute("id");
            row.removeAttribute("class");
            row.setAttribute("class", "com_grid_row");

            while(row.hasChildNodes()) {
                row.removeChild(row.firstChild);
            }

            col.setAttribute("class", "com_grid_none");
            col.innerText = "조회된 데이터가 없습니다";
            row.appendChild(col);
            body.appendChild(row);
        }
    },

    gridValue: function(grid_id, cols) {
        if(grid_id == null) {
            aic.error("grid id is null");
        } else if(cols == null) {
            aic.error("cols is null");
        }

        let selectObj = this.selectedList[grid_id];

        if(selectObj == null) return null;

        if(isNaN(cols)) {
            return selectObj.querySelector("div[data-name=" + cols + "]").innerText;
        } else {
            return selectObj.children[cols].innerText;
        }

    },

    gridActive: function(grid_id, row) {
        let prevSelectedList = this.selectedList[grid_id];
        if(prevSelectedList != null) prevSelectedList.style.backgroundColor = "#fff";

        this.selectedList[grid_id] = row;
        this.selectedList[grid_id].style.backgroundColor = "#f2f2f2";
    },

    /**
     * 페이징 함수
     * @param paginationId (div 오브젝트 ID)
     * @param searchModel (서버에서 받아온 조회관련 VO 객체)
     * @param pageFunction (입력하지 않을경우 default로 searchPage 실행)
     */
    pagination: function(paginationId, searchModel, pageFunction) {
        let paginationObj = document.getElementById(paginationId);

        // 초기화
        while(paginationObj.hasChildNodes()) {
            paginationObj.removeChild(paginationObj.firstChild);
        }

        // default 페이징 함수 설정
        let defaultFunc;
        if(pageFunction == null || typeof pageFunction == 'undefined') defaultFunc = "searchPage";
        else defaultFunc = pageFunction.name;

        // 전체 목록수 표시
        // 2023.03.24 천단위 콤마가 표시될수 있도록 변경
        if(document.getElementById('totalNo')) {
            let totalNo = searchModel.totalNo;
            document.getElementById('totalNo').innerText = parseInt(totalNo).toLocaleString() + "건";
        }

        // 첫페이지 버튼
        let first = document.createElement("div");
        let firstIcon = document.createElement("i");
        firstIcon.setAttribute("class", "bi bi-chevron-double-left");

        if(searchModel.pageTotal < 1 || searchModel.pageNo === 1) {
            first.setAttribute("class", "com_pg_disable");
        } else {
            first.setAttribute("onclick", defaultFunc + "(1)");
        }

        first.appendChild(firstIcon);

        // 왼쪽 버튼
        let left = document.createElement("div");
        let leftIcon = document.createElement("i");
        leftIcon.setAttribute("class", "bi bi-chevron-left");
        left.appendChild(leftIcon);

        if(searchModel.pageNo <= searchModel.pageNavi) {
            left.setAttribute("class", "com_pg_disable");
        } else {
            left.setAttribute("onclick", defaultFunc + "(" + (Number(searchModel.pageStart) - Number(searchModel.pageNavi)) + ")");
        }

        // 오른쪽 버튼
        let right = document.createElement("div");
        let rightIcon = document.createElement("i");
        rightIcon.setAttribute("class", "bi bi-chevron-right");
        right.appendChild(rightIcon);

        if(searchModel.pageEnd < searchModel.pageTotal) {
            right.setAttribute("onclick", defaultFunc + "(" + (Number(searchModel.pageStart) + Number(searchModel.pageNavi)) + ")");
        } else {
            right.setAttribute("class", "com_pg_disable");
        }

        // 마지막페이지 버튼
        let last = document.createElement("div");
        let lastIcon = document.createElement("i");
        lastIcon.setAttribute("class", "bi bi-chevron-double-right");

        if(searchModel.pageTotal < 1) {
            last.setAttribute("class", "com_pg_disable");
        } else {
            last.setAttribute("onclick", defaultFunc + "(" + searchModel.pageTotal + ")");
        }

        last.appendChild(lastIcon);

        paginationObj.appendChild(first);
        paginationObj.appendChild(left);

        // 페이지 버튼
        for(let i = searchModel.pageStart; i <= searchModel.pageEnd; i++) {
            let pageBtn = document.createElement("div");
            pageBtn.setAttribute("onclick", defaultFunc + "(" + i + ")");
            pageBtn.textContent = i;

            if(searchModel.pageNo == i) {
                pageBtn.setAttribute("class", "com_pg_active");
            }

            if(searchModel.pageTotal < i) continue;

            paginationObj.appendChild(pageBtn);
        }

        paginationObj.appendChild(right);
        paginationObj.appendChild(last);
    },

    /** 구현중
     *
     */
    writeNavi: function (paging_id, search) {
        let paging = document.getElementById(paging_id)

        if(document.getElementById('totalNo')) document.getElementById('totalNo').innerText = search.totalNo

        let html = "";
        html += '<div '
        if(search.pageStart != 1)
            html += 'onClick="searchPage(' + (Number(search.pageStart)-5) + ')"'
        html += '><i class="bi-chevron-left"></i></div>'

        for(let i=0; i<search.pageNavi; i++) {
            let num = (Number(search.pageStart)+i)
            if(num <= search.pageEnd) {
                html += '<div ';
                html += 'value="' + num + '" ';
                if(num == search.pageNo)
                    html += 'class="chn_bs_naviAct" '
                html += 'onClick="searchPage('+ num +')">';
                html += num;
                html += '</div>';
            } else {
                html += '<div style="background:#EEEEEE"> </div>'
            }
        } aic.wr

        html += '<div '
        if(search.pageEnd != search.pageTotal)
            html += 'onClick="searchPage(' + (Number(search.pageStart)+search.pageNavi) + ')"'
        html += '><i class="bi-chevron-right"></i></div>'
        paging.innerHTML = html
    },

    /** 사용설명<br>
     * @table_id - [ table ]의 id 값
     * @view - [ table ] 에 들어갈 값을 가진 객체
     * writeView('shTable', shView)<br>
     * [ shTable ] 내부에서 객체의 변수명과 동일한 name 을 갖고 있는 요소([ name="변수명" ])의 [ value ] 및 [ text ] 를 맵핑 시켜준다.
     */
    writeView: function(table_id, view) {
        for(let idx in view) {
            let target = document.querySelector('table#' + table_id + ' *[name="'+idx+'"]')
            if(target != undefined) {
                if(target.tagName == "INPUT" || target.tagName == "SELECT" || target.tagName == "TEXTAREA") {
                    target.value = aic.unescapeHtml(view[idx]);
                } else {
                    target.innerText = aic.unescapeHtml(view[idx]);
                }
            }
        }
    },

    /** 사용설명<br>
     * @param element_id - 선택 요소의 id 값
     * clearView('element_id')<br>
     * 해당 요소 내부에서 name 속성을 갖고 있는 요소의 [ value ] 및 [ text ] 를 전부 초기화한다.
     */
    clearView: function(element_id) {
        let targets = document.querySelectorAll('#' + element_id + ' *[name]')
        for(let target of targets) {
            target.value = ''
            if(target.tagName == 'SELECT'){ target.querySelectorAll("option")[0].selected = true}
        }
    },


    /**
     * POST 방식으로 팝업호출
     * @param url
     * @param name (팝업이름)
     * @param width (따옴표없이 숫자만)
     * @param height (따옴표없이 숫자만)
     * @param params (전달해야할 파라미터를 JSON 형식으로 작성) (ex) { "user_id" : "22222" }
     * @param options (window.open features 옵션을 직접 지정할 경우 설정)
     *
     * (ex) aic.popup("/scn/entyGrpWrite", "ENTY_GRP_WRITE", 500, 300, { "enty_grp_id" : id });
     */
    popup: function(url, name, width, height, params, options) {
        let form = document.createElement("form");

        if(options == null) {
            options = "toolbar=no, width=" + width + "px, height=" + height + "px, directories=no, status=no, scrollorbars=no, resizable=no";
        }

        GLOBAL_POPUP[(GLOBAL_POPUP == null) ? 0 : GLOBAL_POPUP.length] = window.open("", name, options);

        form.setAttribute("id", "dynamicForm");
        form.setAttribute("method", "POST");
        form.setAttribute("action", url);
        form.setAttribute("target", name);

        if(params != null) {
            let keys = Object.keys(params);

            for(let i = 0; i < keys.length; i++) {
                let hidden = document.createElement("input");

                hidden.setAttribute("type", "hidden");
                hidden.setAttribute("name", keys[i]);
                hidden.setAttribute("value", params[keys[i]]);
                form.appendChild(hidden);
            }
        }

        document.body.appendChild(form);

        document.getElementById("dynamicForm").submit();
        document.getElementById("dynamicForm").remove();
    },
    open: function(url, params) {
        let form = document.createElement("form");

        form.setAttribute("id", "dynamicForm");
        form.setAttribute("method", "POST");
        form.setAttribute("action", url);

        if(params != null) {
            let keys = Object.keys(params);

            for(let i = 0; i < keys.length; i++) {
                let hidden = document.createElement("input");

                hidden.setAttribute("type", "hidden");
                hidden.setAttribute("name", keys[i]);
                hidden.setAttribute("value", params[keys[i]]);
                form.appendChild(hidden);
            }
        }

        document.body.appendChild(form);

        document.getElementById("dynamicForm").submit();
    },
    tree: function(url, treeId, options) {
        aic.ajax(url, options.params, function(data) {
            let treeList    = data.list;
            let clickEvt    = options.click;
            let expand      = options.expand;
            let all         = (options.all == null) ? false : options.all;
            let treeObj     = $("#" + treeId);
            let map         = {}, node, roots = [], i;
            let alls        = {};

            alls.id     = "0";
            alls.name   = "전체";
            alls.pid    = "";
            alls.text   = "전체";

            // 전체 여부가 TRUE일 경우 항목 추가
            if(all) roots.push(alls);

            // TREE 데이터 생성
            if(treeList != null) {
                for(i = 0; i < treeList.length; i += 1) {
                    map[treeList[i].id] = i;
                    treeList[i].nodes = [];
                    treeList[i].text = treeList[i].name;
                }
                for(i = 0; i < treeList.length; i += 1) {
                    node = treeList[i];

                    if(node.pid !== "0" && node.pid !== null) {
                        treeList[map[node.pid]].nodes.push(node);
                    } else {
                        roots.push(node);
                    }
                }
            }

            treeObj.treeview({
                data: JSON.stringify(roots).replaceAll(",\"nodes\":[]", ""),
                borderColor: "white",
                onNodeSelected: function(event, data) {
                    // 2023.04.06 예를들어 5번째 목록을 선택햇다가 재조회하여 목록이 3개만 있는데 그때 목록을 선택하면 이전노드(5번째)를 해제하려다가 예외가 발생하는 현상 보완
                    try {
                        // 이전에 선택한 노드가 현재 선택한 노드와 다를 경우 이전 선택한 노드를 선택해제 처리
                        if(this.currentTreeNode != null && this.currentTreeNode != data.nodeId) {
                            treeObj.treeview('unselectNode', [ this.currentTreeNode, { silent: true } ]);
                        }

                        // 현재 선택한 노드를 기억하도록 설정
                        this.currentTreeNode = data.nodeId;
                    } catch(e) {}

                    // 클릭이벤트 설정이 있을경우 실행
                    if(clickEvt != null) clickEvt(data);
                },
                onNodeUnselected: function(event, data) {
                    // 선택 해제한 노드가 이미 선택했던 노드라면 강제로 다시 선택된 것으로 처리
                    // 같은 노드를 선택했을경우 토글되는 것을 방지하기 위함
                    if(this.currentTreeNode == data.nodeId) {
                        treeObj.treeview('selectNode', [ data.nodeId, { silent: true } ]);
                        if(clickEvt != null) clickEvt(data);
                    }
                }
            });

            // 전체 항목이 있을경우 자동 선택 처리
            if(all) treeObj.treeview('selectNode', [ 0, { silent: true } ]);

            // 펼치기 옵션이 있을경우 해당 값으로 펼치기 설정
            if(expand != null && expand > 0) {
                treeObj.treeview('expandAll', { levels: expand, silent: true });
            }
        });
    },
    treeOpen: function(treeId) {
        $("#" + treeId).treeview('expandAll', { levels: 10, silent: true });
    },
    treeClose: function(treeId) {
        $("#" + treeId).treeview('collapseAll', { silent: true });
    },
    treeSelect: function(treeId, nodeId) {
        if(nodeId == null || nodeId == "") nodeId = $("#" + treeId).treeview('getSelected')[0].nodeId;
        $("[data-nodeid='" + nodeId + "'").click();
    },
    maxLength: function(object, size) {
        let text = object.value;
        if(text.length > size) {
            object.value = text.substring(0, size);
            alert(num + '까지만 입력이 가능합니다');
        }
    },
    unescapeHtml: function(html) {
        if(html == null) return "";
        else if(html == "") return "";

        return String(html)
            .replace(/&amp;/g, '&')
            .replace(/&lt;/g, '<')
            .replace(/&gt;/g, '>')
            .replace(/&quot;/g, '"')
            .replace(/&#039;/g, "'")
            .replace(/&#39;/g, "'")
            .replace(/&apos;/g, "'")
            .replace(/&lsquo;/g, "‘")
            .replace(/&rsquo;/g, "’")
            .replace(/&middot;/g, "·")
            .replace(/&rarr;/g, "→")
            .replace(/&rArr;/g, "⇒")
            .replace(/&larr;/g, "←")
            .replace(/&#9608;/g, "█")
            .replace(/&divide;/g, "÷")
        ;
    },
    combo: function(url, comboList, dataList, options) {
        let params = {};

        if(options != null) params = options.params;

        for(let i = 0; i < comboList.length; i++) {
            let comboObj = document.getElementById(comboList[i]);

            // 초기화
            while(comboObj.hasChildNodes()) {
                comboObj.removeChild(comboObj.firstChild);
            }

            if((i + 1) < comboList.length) {
                comboObj.addEventListener("change", function() {
                    if(comboObj.value == "") {
                        for(let j = (i + 1); j < comboList.length; j++) {
                            let childObj = document.getElementById(comboList[j]);

                            // 초기화
                            while (childObj.hasChildNodes()) {
                                childObj.removeChild(childObj.firstChild);
                            }

                            let options = document.createElement("option");
                            options.setAttribute("value", "");
                            options.text = "전체";
                            childObj.appendChild(options);
                        }
                    } else {
                        aic.ajax(url, { "pvalue" : comboObj.value }, function(data) {
                            for(let j = (i + 1); j < comboList.length; j++) {
                                let childObj = document.getElementById(comboList[j]);

                                // 초기화
                                while(childObj.hasChildNodes()) {
                                    childObj.removeChild(childObj.firstChild);
                                }

                                if(j == (i + 1)) {
                                    let options = document.createElement("option");
                                    options.setAttribute("value", "");
                                    options.text = "전체";
                                    childObj.appendChild(options);

                                    for(let i = 0; i < data.length; i += 1) {
                                        let options = document.createElement("option");
                                        options.setAttribute("value", data[i].value);
                                        options.text = data[i].text;
                                        childObj.appendChild(options);
                                    }
                                } else {
                                    let options = document.createElement("option");
                                    options.setAttribute("value", "");
                                    options.text = "전체";
                                    childObj.appendChild(options);
                                }
                            }
                        });
                    }
                });
            }

            // 1레벨 콤보박스 생성
            if(i == 0) {
                let options = document.createElement("option");
                options.setAttribute("value", "");
                options.text = "전체";
                comboObj.appendChild(options);

                aic.ajax(url, params, function(data) {
                    for(let x = 0; x < data.length; x++) {
                        let options = document.createElement("option");
                        options.setAttribute("value", data[x].value);
                        options.text = data[x].text;
                        if(dataList != null && dataList[i] == data[x].value) options.selected = true;
                        comboObj.appendChild(options);
                    }
                });
            } else {
                // 2레벨 이하 콤보박스 생성
                if(dataList != null && dataList.length > 0) {
                    let options = document.createElement("option");
                    options.setAttribute("value", "");
                    options.text = "전체";
                    comboObj.appendChild(options);

                    aic.ajax(url, { "pvalue" : dataList[i - 1] }, function(data) {
                        for(let y = 0; y < data.length; y++) {
                            let options = document.createElement("option");
                            options.setAttribute("value", data[y].value);
                            options.text = data[y].text;
                            if(dataList[i] == data[y].value) options.selected = true;
                            comboObj.appendChild(options);
                        }
                    });
                } else {
                    let options = document.createElement("option");
                    options.setAttribute("value", "");
                    options.text = "전체";
                    comboObj.appendChild(options);
                }
            }
        }
    },
    writeChat: function(dscsnHistId) {
        aic.ajax("/cbt/chatList/ajax", { "dscsn_hist_id" : dscsnHistId }, function(data) {
            let chatList = data.chatList;
            let chatHead = document.getElementById("chatHead");
            let chatCntn = document.getElementById("chatCntn");

            while(chatCntn.hasChildNodes()) {
                chatCntn.removeChild(chatCntn.firstChild);
            }

            for (let i = 0; i < chatList.length; i++) {
                if(i == 0) {
                    chatHead.innerHTML = "상담이력ID: " + chatList[i].dscsn_hist_id + ", 고객명: (" + chatList[i].cust_id + ") "+ chatList[i].prvc_nm;
                     //+ "<img src='/static/com/img/excelImg.png' width='20' height='20' style='margin-left: 5px;' onclick='excelCbtHistDetail()' >"; 상세 상담내용 엑셀 일단 제외
                }

                writeChatCreate(chatCntn, chatList[i], 'Q');
                writeChatCreate(chatCntn, chatList[i], 'A');
            }
        });

        // 채팅 엘리먼트 생성
        function writeChatCreate(elObj, item, type) {
            let elDiv   = document.createElement("div");
            let elSpan1 = document.createElement("span");
            let elSpan2 = document.createElement("span");
            let elSpan3 = document.createElement("span");

            // 내용이 없을 경우 생성하지 않음
            if (type == 'Q' && (item.uter_cn == null || item.uter_cn == '')) return;
            if (type == 'A' && (item.ans_cn == null || item.ans_cn == '')) return;

            if (type == 'Q') {
                elDiv.setAttribute("class", "kakao_question");
                elSpan1.innerText = "("+item.cust_id+") " + item.prvc_nm;
                elSpan2.innerText = aic.unescapeHtml(item.uter_cn);
                elSpan3.innerText = item.chnl_dmnd_dt;

                let elDiv2 = document.createElement("div");
                let elImg  = document.createElement("img");

                elImg.setAttribute("src", "/kakao/profile/image?chnlCustId=" + item.chnl_cust_id);
                elImg.onclick = function() {
                    aic.popup("/chn/userProfile", "KAKAO_PROFILE", 400, 520, { "chnl_cust_id" : item.chnl_cust_id });
                }


                elDiv.appendChild(elSpan1); // 발화자
                elDiv2.appendChild(elSpan1); // 발화자
                elDiv2.appendChild(elSpan2); // 발화내용


                elDiv.appendChild(elImg);
                elDiv.appendChild(elDiv2);
                elDiv.appendChild(elSpan3);

            } else if (type == 'A') {
                elDiv.setAttribute("class", "kakao_answer");
                elSpan1.innerText = item.chnl_ans_dt;
                elSpan2.innerText = aic.unescapeHtml(item.ans_cn);


                elDiv.appendChild(elSpan1); // 답변일시
                elDiv.appendChild(elSpan2); // 발화내용
            }

            elObj.appendChild(elDiv);
        }
    },
    date: function(options, format, period) {
            let date = new Date();

            if(format == null) format = "";

            // 이번달
            if(options == 'tm' || options == 'TM') {
                return dateStr(new Date(date.getFullYear(), date.getMonth(), 1), format);

            // 이번주
            } else if(options == 'tw' || options == 'TW') {
                let day = date.getDay();
                let diff = date.getDate() - day + (day == 0 ? -6 : 1);
                date.setDate(diff);
                return dateStr(date, format);

            // 오늘
            } else if(options == 'td' || options == 'TD') {
                return dateStr(date, format);

            // period 개월 전
            } else if(options == 'm' || options == 'M') {
                let month = date.getMonth();
                date.setMonth(month - period);
                return dateStr(date, format);

            // period 일 전
            } else if(options == 'd' || options == 'D') {
                let day = date.getDate();
                date.setDate(day - period);
                return dateStr(date, format);
            } else if(options == 'tmm' || options == 'TMM') {
                let month = date.getMonth();
                date.setMonth(month);
                return monthStr(date, format);
            } else if(options == 'mm' || options == 'MM') {
                let month = date.getMonth();
                date.setMonth(month - period);
                return monthStr(date, format);
            }

            function dateStr(date, format) {
                let year    = date.getFullYear();
                let month   = date.getMonth() + 1;
                let day     = date.getDate();

                month   = (month < 10) ? "0" + String(month) : month;
                day     = (day < 10) ? "0" + String(day) : day;

                return String(year) + format + String(month) + format + String(day);
            }

            function monthStr(date, format) {
                let year    = date.getFullYear();
                let month   = date.getMonth() + 1;

                month   = (month < 10) ? "0" + String(month) : month;

                return String(year) + format + String(month);
            }
    },
     /**
     * 그리드 열 더블 클릭한 열 클립보드 복사
     * @param grid_id (테이블 그리드 id)
     */
    clip: function(grid_id){
		let list = document.getElementById(grid_id+"_body");
		let rows = list.querySelectorAll(".com_grid_row");
		for ( let row of rows ){
			let cols =  row.querySelectorAll("div");
			for ( let col of cols ) {
				col.ondblclick =()=>{
					let text = col.textContent;
					if(text != null && text != ""){
						navigator.clipboard.writeText(text)	.then(()=>{
							/*success*/
							aic.alert("해당 내용이 클립보드에 복사되었습니다."+"\n'"+text+"'");
						},()=>{
							/*fail*/
							aic.alert("클립보드 복사에 실패하였습니다.")
						})
					}
				}
			}
		}

	},
    initEditor: function(editorVar, editorId) {
        nhn.husky.EZCreator.createInIFrame({
            oAppRef: editorVar,
            elPlaceHolder: editorId, // 에디터 띄울 textarea id
            sSkinURI: "/smarteditor/SmartEditor2Skin.html", // 에디터 경로
            fCreator: "createSEditor2",
            htParams : {
                fOnBeforeUnload: function() {}
            }
        });
    },
    getId: function(id) {
        return document.getElementById(id);
    },
    getIdValue: function(id) {
        return document.getElementById(id).value;
    },
    getName: function(name, index) {
        return document.getElementsByName(name)[index == null ? 0 : index];
    },
    getNameValue: function(name, index) {
        return document.getElementsByName(name)[index == null ? 0 : index].value;
    },
    isEmptyIdValue: function(id) {
        if(aic.getIdValue(id) == null) return true;
        else if(aic.getIdValue(id) == "") return true;

        return false;
    },
    isEmptyNameValue: function(id) {
        if(aic.getNameValue(id) == null) return true;
        else if(aic.getNameValue(id) == "") return true;

        return false;
    },
    lpad: function(str, pad, len) {
        pad  = pad || '0';
        str  = str + '';

        return str.length >= len ? str : new Array(len - str.length + 1).join(pad) + str;
    },

    /**
     * 테이블 정렬 이벤트 - 김정진 작성 중
     *
     * <table> - <tr> - <td> 구조는 적용 X
     * <div> - <div> 일 때 사용가능합니다.
     * 화면마다 header, body 아이디, 클래스가 다 달라서 일단 이렇게 여러개를 넣게 했습니다. 만약 구조가 통일된다면 더 짧게 작성할 수도 있을 것 같습니다.
     * 굳이 필요 없을 확률이 높지만 이왕 작성한거 기록 차원에서 남겨두겠습니다.
     *
     * @param header_id (헤더에 부여되는 ID)
     * @param header_class (헤더에 부여되는 class)
     * @param body_id (바디에 부여되는 id)
     * @param body_class (바디에 부여되는 class)
     * @param row_class (row 에 부여되는 class)
     * @param fn (사용자 정의 이벤트)
     * @param evt (이벤트 타입)
     * @param key (함수에 전달하는 key)
     * */
    tableRearrange:
        function (header_id, header_class, body_id, body_class, row_class, fn, evt, key) {
            // 1.1 대상 테이블 헤더에 정렬 아이콘 부여
            // id 인 경우
            let tableHeader;
            tableHeader = document.querySelectorAll("[id=" + `${header_id}` + "] > *");
            if (tableHeader === null || tableHeader.length === 0) {
                tableHeader = document.querySelectorAll("[class=" + `${header_class}` + "] > *");
            }

            // 1.2 헤더에 이미지를 포함시킵니다.
            for (let i = 0; i < tableHeader.length; i++) {
                let img = document.createElement("img");
                img.src = "/cbt/img/caret-down-fill.svg";
                img.setAttribute("data-sort", "asc");
                img.width = 12;
                img.height = 12;
                img.alt = "";
                img.classList.add("down-arrow");
                tableHeader[i].append(img);
                tableHeader[i].addEventListener("click", (evt) => {
                    addClickEvent(evt, i);
                });
            }

            // 1.3 클릭 이벤트 부여 (180deg 회전)
            function addClickEvent(evt, table_index) {
                const arrow = evt.target.children[0];
                arrow.classList.toggle("down-arrow");
                arrow.classList.toggle("up-arrow");
                addArrangeEvent(table_index, body_id, arrow);
            }

            // 1.4 각 헤더마다 정렬 이벤트 부여
            function addArrangeEvent(table_index, body_id, target_arrow) {
                // 기존에 존재하는 데이터 선택
                let original;
                original = document.querySelectorAll("[id=" + `${body_id}` + "] > *");
                if (original === null || original.length === 0) {
                    original = document.querySelectorAll("[class=" + `${body_class}` + "] > *");
                }
                // NodeList -> Array
                original = [...original];
                let HTML = "";
                // 데이터가 출력될 body 선택
                let body;
                body = document.querySelector("[id=" + `${body_id}` + "]");
                if (body == null || body.length === 0) {
                    body = document.querySelector("[class=" + `${body_class}` + "]");
                }
                let arr = null;
                // 오름차순 정렬
                if (target_arrow.classList.contains("down-arrow")) {
                    arr = rearrange(original, "asc", table_index);
                    HTML = createHTML(arr, HTML);
                }
                // 내림차순 정렬
                else if (target_arrow.classList.contains("up-arrow")) {
                    arr = rearrange(original, "desc", table_index);
                    HTML = createHTML(arr, HTML);
                }
                body.innerHTML = HTML;
                // key 값을 찾아서 대입
                if (evt === null) evt = "click";
                if (fn !== null) {
                    // 사용자 정의 이벤트 부여
                    let createdRows = document.getElementsByClassName("`${row_class}`");
                    createdRows = [...createdRows];
                    if (createdRows) {
                        createdRows.forEach((row) => {
                            row.addEventListener(evt, () => {
                                if (key === null || key === undefined || key === "") {
                                    fn();
                                } else {
                                    let param = null;
                                    const items = [...row.children];
                                    items.forEach(item => {
                                        // 사용자에게 입력받은 id 와 일치하는 값 탐색
                                        if (item.attributes["data-name"].nodeValue === `${key}`) {
                                            param = item.textContent;
                                            // 찾았으면 반복문 종료
                                            return false;
                                        }
                                    });
                                    fn(param);
                                }
                            });
                        });
                    }
                }
            }

            // 1.5 정렬
            function rearrange(array, type, table_index) {
                // 숫자만 존재하고, 날짜 형식을 포함하지 않는 케이스를 동시에 나타내는 정규식을 만들어주세요.. ㅜ.ㅜ
                // 정규식 사이트 띄워두고 몇 번 해보다가 포기하고 2번으로 케이스를 쪼갰습니다.

                // sort() : 내부값을 UNICODE 값으로 치환해서 계산하기 때문에 케이스를 나눠서 계산해야합니다.
                // 숫자는 return a - b || return b - a;
                // 문자는 if(a > b) return -1 || if(a < b) return -1;

                // 숫자가 포함되어 있는지 검사 : 이렇게 하니까 날짜 형식도 통과합니다.
                const regNumber = /[\d]/;
                // 날짜 형식인지 검사 (삽질하다가 일단은 white space, :, - 가 포함되어 있으면 날짜로 판단하도록 작성했습니다.
                const regDate = /[?\s:-]/g;
                // 내림차순
                if (type === "desc") {
                    array.sort((a, b) => {
                        let firstValue = a.children[`${table_index}`].textContent;
                        let secondValue = b.children[`${table_index}`].textContent;
                        // 숫자만 포함한 경우
                        if (regNumber.test(firstValue) && regNumber.test(secondValue)) {
                            // 숫자만
                            if (!regDate.test(firstValue) && !regDate.test(secondValue)) return secondValue - firstValue;
                            // 날짜
                            else if (firstValue > secondValue) return -1;
                        }
                        // 문자인 경우
                        else {
                            if (firstValue > secondValue) return -1;
                            if (firstValue < secondValue) return 1;
                            if (firstValue === secondValue) return 0;
                            else return -1;
                        }
                    });
                }
                // 오름차순
                else if (type === "asc") {
                    array.sort((a, b) => {
                        let firstValue = a.children[`${table_index}`].textContent;
                        let secondValue = b.children[`${table_index}`].textContent;
                        // 숫자만 포함한 경우
                        if (regNumber.test(firstValue) && regNumber.test(secondValue)) {
                            // 숫자만
                            if (!regDate.test(firstValue) && !regDate.test(secondValue)) return firstValue - secondValue;
                            // 날짜
                            else if (firstValue < secondValue) return -1;
                        }
                        // 문자
                        else {
                            if (firstValue > secondValue) return 1;
                            if (firstValue < secondValue) return -1;
                            if (firstValue === secondValue) return 0;
                            else return -1;
                        }
                    });
                }
                return array;
            }

            // 정렬된 요소 HTML 로 작성
            function createHTML(obj, html) {
                obj.forEach((item) => {
                    html += `<div class=${row_class}>`;
                    const inner = [...item.children];
                    inner.forEach((data) => {
                        // attributes 에 data-name 등의 속성이 포함되어 있습니다.
                        html +=
                            `<div ${data.attributes["data-name"].nodeName}="${data.attributes["data-name"].nodeValue}"
                            ${data.attributes["alt"].nodeName}="${data.attributes["alt"].nodeValue}"
                            ${data.attributes["title"].nodeName}="${data.attributes["title"].nodeValue}">${data.innerText}</div>`;
                    });
                    html += "</div>";
                });
                return html;
            }
        },
		/**
		* 일정 시간 뒤 사라지는 토스트를 호출하는 함수
		* aic.alert("텍스트 내용");
		* @param content 알림 창에 들어갈 내용
		*/
		alert: function toast(content) {
			/** setTimeout을 통해 특정 요소를 일정 시간뒤 삭제
		    * @param element 삭제할 요소의 이름, 이름앞에 선택자 구분도 같이 넣어 사용/ delayRemove(#id), delayRemove(.class)
		    * @param time 삭제되기 까지의 지연 시간 / time-1 = 1초 , time-10 = 10초
		    ' */
            let tout;

			function delayRemove(element,time){
				tout =(element.includes("name"))?
				/*  name  */setTimeout(()=>document.querySelectorAll(element).forEach(e =>e.remove()) ,time*1000) :
				/*id|class*/setTimeout(()=>document.querySelector(element).remove() ,time*1000);
			}
			function delayCancle(){ clearTimeout(tout) }
			// toast 호출 시 toast.css 파일링크가 없다면 CSS파일 참조 태그를 head에 삽입
			if(document.head.querySelector('link[href="/mob/css/toast.css"]') == null){
				let css = document.createElement("link");
				css.setAttribute("rel","stylesheet");
				css.setAttribute("href","/mob/css/toast.css");
				document.head.appendChild(css);
			}
		  	let toast = document.querySelector('.toast');
			// 이미 생성된 알림창이 없다면 새로 생성
			if(toast == null) {
			    toast = document.createElement("div");
			    toast.setAttribute("class","toast");
			  	toast.insertAdjacentHTML("afterbegin",`
			  		<i class="bi bi-exclamation-circle icon"></i><span style="font-variant:small-caps; font-weight: bold; color:#fff;"> 알림 </span>
			  		<div class="toastTxt"></div>`);
			  	toast.querySelector(".toast .toastTxt").innerText = (content != null )? content : "성공적으로 실행되었습니다."
			    document.body.appendChild(toast);
			    // 애니메이션 효과
			    toast.classList.add("show");
			  	delayRemove(".toast",3);
	   		}else{ // 이미 알림창이 있다면 기존 알림창을 갱신
				delayCancle();

				toast.querySelector(".toast .toastTxt").innerText = ( content != null )? content : "성공적으로 실행되었습니다." ;
				toast.remove();
				document.body.appendChild(toast);
				delayRemove(".toast",3);
			}
	    },

    /**
     * milliseconds 만큼 기다리는 비동기함수
     * */
    timing: async (milliseconds) => {
        await new Promise((resolve) => {
            setTimeout(resolve, milliseconds);
        });
    },

    /**
     * dataPeriods 클릭 이벤트를 부여하는 함수
     * */
    periodsClickEvent: () => {
        document.querySelectorAll(".datePeriods").forEach(period => {
            period.addEventListener("click",  () => {
                aic.timing(100).then(() => fnSearch(1));
            });
        });
    }

}





