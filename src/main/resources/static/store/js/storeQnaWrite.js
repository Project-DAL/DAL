/*
File Name      : storeQnaWrite.js
Program Name   : 상품 문의글 작성 js
Draft Author   : 김석진
Draft Date     : 2024.01.23

Revision History
Ver.  Date          Revised By   Description
------------------------------------------------------------------------------------------------------------------------
0.1   2024.01.14    김석진       최초개발
----------------------------------------------------------------------------------------------------------------------->
*/
function modal(id) {
    var zIndex = 9999;
    var modal = document.getElementById(id);

    // 모달창 뒷배경 어둡게 처리
    var bg = document.createElement('div');
    bg.setStyle({
        position: 'fixed',
        zIndex: zIndex,
        left: '0px',
        top: '0px',
        width: '100%',
        height: '100%',
        overflow: 'auto',
        // 레이어 색갈은 여기서 바꾸면 됨
        backgroundColor: 'rgba(0,0,0,0.4)'
    });
    document.body.append(bg);

    // 닫기 버튼 처리, 시꺼먼 레이어와 모달 div 지우기
    modal.querySelector('.modal_close_btn').addEventListener('click', function() {
        bg.remove();
        modal.style.display = 'none';
    });

    var modalContent = modal.querySelector('.ct_qna-mid');
    modalContent.setStyle({
        overflowY: 'auto',
        maxHeight: '500px'
    })

    modal.setStyle({
        position: 'fixed',
        display: 'block',
        boxShadow: '0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19)',

        // 시꺼먼 레이어 보다 한칸 위에 보이기
        zIndex: zIndex + 1,

        // div center 정렬
        top: '50%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        msTransform: 'translate(-50%, -50%)',
        webkitTransform: 'translate(-50%, -50%)',
        width: '800px',
        height: '600px'
    });
}

// Element 에 style 한번에 오브젝트로 설정하는 함수 추가
Element.prototype.setStyle = function(styles) {
    for (var k in styles) this.style[k] = styles[k];
    return this;
};

function fnInsertQna() {
    if(confirm("저장하시겠습니까?")) {

        let formData = new FormData();

        $.ajax({
            url: "/store/insertProdQna",
            type: "POST",
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache: false,
            data: formData,
            dataType: "json",
            success: function() {
                alert("저장되었습니다.");
            }
        });
    }
}

