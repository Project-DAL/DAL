/*
    파일명        : join.js
    최초 작성자    : 신호준
    최초 작성날짜   : 2024.02.13
*/

function openZipSearch() {
    new daum.Postcode({
        oncomplete: function(data) {
            var addr = '';
            if (data.userSelectedType === 'R') {
                addr = data.roadAddress;
            } else {
                addr = data.jibunAddress;
            }

            $("#zip_code").val(data.zonecode);
            $("#addr").val(addr);
            $("#addr_dtl").val("");
            $("#addr_dtl").focus();
        }
    }).open();
}


