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

1. Common               - 공통기능 Javascript
1.1 unescapeHTML()      - 문자열에 포함된 HTML 특수문자를 원래 형식으로 변환하여 반환
1.2 isEmpty()           - 문자열이 비어있는지 여부를 Boolean으로 반환
1.3 popup()             - 팝업창 호출
1.4 getYMD()            - 오늘 또는 오늘부터 특정일 이전/이후 일자를 구분자를 포함하여 반환
1.5 ajax()              - 비동기 서버 호출
1.6 ajaxFile()          - 비동기 서버 파일 호출
1.7 ajaxExcel()         - Excel 다운로드
1.8 fileDown()          - 파일 다운로드
1.9 secToTime()         - 시간 체크

*/

/* 1. Common **********************************************************************************************************/
const Common = {
    /* 1.1 unescapeHTML() - 문자열에 포함된 HTML 특수문자를 원래 형식으로 변환하여 반환 */
    unescapeHTML: function(html) {
        let div = document.createElement("div");
        div.innerHTML = html;
        return div.textContent || div.innerText || "";
    },

    /* 1.2 isEmpty() - 문자열이 비어있는지 여부를 Boolean으로 반환 */
    isEmpty: function(str) {
        if (str === null) return true;
        if (typeof str === 'string' && str === '') return true;
        if (typeof str === 'undefined') return true;
    },

    gateway: function(type, width, height, custId, custTel) {
        return this.popup(type, "http://10.99.57.17:7008/snsGateway.jsp", width, height, {
            type: type, // 화면유형
            userId: top.document.getElementById('GBL_USER_ID').value, // 상담원ID
            custId: custId, // 고객식별번호
            custTel: custTel // 고객전화번호
        });
    },

    /* 1.3 popup() - 팝업창 호출 */
    popup: function(name, url, width, height, params, options) {
        let form = document.createElement('form');
        let left = Math.round(window.screenX + (window.outerWidth / 2) - (width / 2));
        let top = Math.round(window.screenY + (window.outerHeight / 2) - (height / 2)) - 50;

        options = 'left=' + left + ',top=' + top + ',width=' + width + ',height=' + height;
        if (options == null) options += ',scrollbars=yes,status=no,location=no,menubar=no,titlebar=no,toolbar=no,resizable=no';

        // 팝업창 정보를 전역변수에 저장
        let newPopup = window.open('', name, options);
        if(parent && parent !== this) window.parent.GBL_POPUP_ARR.push(newPopup); // iframe 내부일 경우
        else window.GBL_POPUP_ARR.push(newPopup); // 상담메인일 경우

        form.method = 'post';
        form.action = url;
        form.target = name;

        if (params != null) {
            // 전달받은 매개변수를 사용하여 숨겨진 입력 필드를 생성하여 폼에 추가
            for (let key in params) {
                let input = document.createElement('input');
                input.setAttribute('type', 'hidden');
                input.setAttribute('name', key);
                input.setAttribute('id', key);
                input.setAttribute('value', params[key]);
                form.append(input);
            }
        }

        document.body.append(form);
        form.submit();
        form.remove();
    },

    /* 1.4 getYMD() - 오늘 또는 오늘부터 특정일 이전/이후 일자를 구분자를 포함하여 반환 */
    getYMD(type, numberOfDiff, separator) {
        let nowDate = new Date();
        let returnDate;

        if(type === 'D' || type === 'd') {
            returnDate = new Date(nowDate.setDate(nowDate.getDate() + numberOfDiff));
        } else if(type === 'M' || type === 'm') {
            returnDate = new Date(nowDate.setMonth(nowDate.getMonth() + numberOfDiff));

        } else if(type === 'DS') {
            returnDate  = new Date(nowDate.setMonth(nowDate.getMonth() + numberOfDiff));

            let month   = returnDate.getMonth() + 1;

            month   = month < 10 ? '0' + month.toString() : month.toString();

            return returnDate.getFullYear() + '-' + month + '-01';

        } else if(type === 'DE') {
            returnDate  = new Date(nowDate.getFullYear(), nowDate.getMonth() + numberOfDiff + 1, 0);

            let month   = returnDate.getMonth() + 1;
            let day     = returnDate.getDate();

            month   = month < 10 ? '0' + month.toString() : month.toString();
            day     = day < 10 ? '0' + day.toString() : day.toString();

            return returnDate.getFullYear() + '-' + month + '-' + day;
        } else if(type === 'MS') {
            returnDate  = new Date(nowDate.setFullYear(nowDate.getFullYear() + numberOfDiff));
            return returnDate.getFullYear() + '-01';

        } else if(type === 'ME') {
            returnDate  = new Date(nowDate.setFullYear(nowDate.getFullYear() + numberOfDiff));
            return returnDate.getFullYear() + '-12';

        } else if(type === 'YY') {
            returnDate  = new Date(nowDate.setFullYear(nowDate.getFullYear() + numberOfDiff));
            return returnDate.getFullYear();
        } else if(type === 'YMDHMS') {
            let year    = nowDate.getFullYear().toString();
            let month   = nowDate.getMonth() + 1;
            let day     = nowDate.getDate();
            let hour    = nowDate.getHours();
            let minute  = nowDate.getMinutes();
            let second  = nowDate.getSeconds();

            month   = month < 10 ? '0' + month.toString() : month.toString();
            day     = day < 10 ? '0' + day.toString() : day.toString();
            hour    = hour < 10 ? '0' + hour.toString() : hour.toString();
            minute  = minute < 10 ? '0' + minute.toString() : minute.toString();
            second  = second < 10 ? '0' + second.toString() : second.toString();

            return year + month + day + hour + minute + second;
        }

        let rYear   = returnDate.getFullYear();
        let rMonth  = returnDate.getMonth() + 1;
        let rDay    = returnDate.getDate();

        if(rMonth < 10) rMonth = '0' + rMonth;
        if(rDay < 10) rDay = '0' + rDay;

        return rYear + separator + rMonth + separator + rDay;
    },
    /* 강제 로그아웃 */
    logoutForced: function() {
        fetch('/logoutForced', {
            method: "POST",
            keepalive: true // 이 옵션이 없으면 ajax 요청이 실행될떄가 있고 안될떄가 있음
        }).
        then(() => {})
    },

    /* 1.5 ajax() - 비동기 서버 호출 */
    ajax: function(requestUrl, requestParams, callbackMethod) {
        fetch(requestUrl, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "X-Requested-With": "XMLHttpRequest"
            },
            body: new URLSearchParams(requestParams)
        }).
        then((response) => {
            console.log(response);
        }).
        catch((error) => {
            console.log(error);

        });
    },

    /* 1.6 ajaxFile() - 비동기 서버 파일 호출 */
    ajaxFile: function(requestUrl, requestParams, callbackMethod) {
        fetch(requestUrl, {
            method: "POST",
            headers: {
            },
            body: requestParams
        }).
        then((response) => {
            if(response.status === 200) {
                return response.json();
            } else if(response.status === 403) {
                if(!window.GBL_SESSION_EXPIRED) {
                    window.GBL_SESSION_EXPIRED = true;

                    if(stompClient) stompClient.disconnect();

                    clearInterval(window.GBL_TIMER_FOOTER); // 현황정보 자동갱신 해제

                    alert("세션이 만료되었습니다.\r\n확인 버튼을 누르면 로그인 페이지로 이동합니다.");
                    location.href = "/login";
                }
            } else if(response.status === 404) {
                throw 404;
            } else if(response.status === 500) {
                throw 500;
            }
        }).
        then((responseJson) => {
            callbackMethod(responseJson);
        }).
        catch((error) => {
            if(Object.prototype.toString.call(error) !== "[object JSON]") return;

            console.log(error);
            if(error === 404)       {
                //alert('요청된 경로를 찾을 수 없습니다. 관리자에게 문의하세요.');
                toastr.error('요청된 경로를 찾을 수 없습니다. 관리자에게 문의하세요.');
            }
            else if(error === 500)  {
                //alert('처리중 오류가 발생하였습니다. 관리자에게 문의하세요.');
                toastr.error('처리중 오류가 발생하였습니다. 관리자에게 문의하세요.');
            }
            else {
                //alert('알수없는 오류가 발생하였습니다. 관리자에게 문의하세요.');
                toastr.error('알수없는 오류가 발생하였습니다. 관리자에게 문의하세요.');
            }
        });
    },

    /* 1.7 ajaxExcel() - Excel 다운로드 */
    ajaxExcel: function(requestUrl, requestParams, excelFileName) {
        fetch(requestUrl, {
            method: "POST",
            headers: {
                'Access-Control-Expose-Headers': 'Content-Disposition'
            },
            body: new URLSearchParams(requestParams)
        }).
        then((response) => {
            if(!response.ok) throw new Error('처리중 오류가 발생하였습니다. 관리자에게 문의하세요.');

            return response.blob();
        }).
        then((blob) => {
            const downloadUrl   = window.URL.createObjectURL(blob);
            const anchorElement = document.createElement('a');

            document.body.appendChild(anchorElement);

            anchorElement.download  = excelFileName + '_' + Common.getYMD("YMDHMS") + '.xlsx';
            anchorElement.href      = downloadUrl;

            anchorElement.click();

            document.body.removeChild(anchorElement);
            window.URL.revokeObjectURL(downloadUrl);
        }).
        catch((error) => {
            if(Object.prototype.toString.call(error) !== "[object JSON]") return;

            console.log(error);
            //alert('처리중 오류가 발생하였습니다. 관리자에게 문의하세요.');
            toastr.error('처리중 오류가 발생하였습니다. 관리자에게 문의하세요.');
        });
    },

    /* 1.8 fileDown() - 파일 다운로드 */
    fileDown: function(fileId) {
        let popup = window.open('/file/download?fileId=' + fileId, '_blank', 'width=500px; height=200px');
        // 부모 창이 닫힐 때 팝업 창도 닫히도록
        window.addEventListener('beforeunload', function () {
            if (popup && !popup.closed) {
                popup.close();
            }
        });
    },

    /* 1.9 secToTime() - 시간 체크 */
    secToTime: function(seconds) {
        if(seconds == null || seconds === '' || seconds === undefined) seconds = 0;

        let hour = Math.floor(seconds / 3600);
        let minute = Math.floor((seconds % 3600) / 60);
        let second = seconds % 60;

        return hour + "시간 " + minute + "분 " + second + "초";
    }
}