"use strict";
/*
File Name      : commWrite.js
Program Name   : 게시판 등록, 수정화면
Draft Author   :
Draft Date     : 2023.12.04
*/

document.addEventListener('DOMContentLoaded', function() {
    fnBtn();
    fnreplyGpSeqMax();
});

function fnBtn() {
	document.getElementById("btnUpdate").addEventListener('click',function() {
	    let bardId = document.getElementById("bardId").value
        window.location = '/comm/commWrite?bardId=' + bardId;
    });

   	document.getElementById("btnDelete").addEventListener('click',function() {
   	    let bardId = document.getElementById("bardId").value
           window.location = '/comm/commAjaxWriteDelete?bardId=' + bardId;
    });

    document.getElementById("save").addEventListener("click", function() {
        document.getElementById("frm").submit();
    });
}

function editButtonClick(replyId) {

    // 댓글 수정
    document.getElementById('replyCnView-' + replyId).classList.add('hide');
    document.getElementById('replyCnInsert-' + replyId).classList.remove('hide');

    // 버튼
    document.getElementById('btnReplyCancel-' + replyId).classList.remove('hide');
    document.getElementById('btnReplyDelete-' + replyId).classList.add('hide');
    document.getElementById('btnReplyUpdate-' + replyId).classList.add('hide');
    document.getElementById('btnReplyInsert-' + replyId).classList.remove('hide');
    document.getElementById('btnReplyReply-' + replyId).classList.add('hide');
}

function cancelButtonClick(replyId) {
    // 댓글 수정
    document.getElementById('replyCnView-' + replyId).classList.remove('hide');
    document.getElementById('replyCnInsert-' + replyId).classList.add('hide');

    // 버튼
    document.getElementById('btnReplyCancel-' + replyId).classList.add('hide');
    document.getElementById('btnReplyDelete-' + replyId).classList.remove('hide');
    document.getElementById('btnReplyUpdate-' + replyId).classList.remove('hide');
    document.getElementById('btnReplyInsert-' + replyId).classList.add('hide');
    document.getElementById('btnReplyReply-' + replyId).classList.remove('hide');
    document.getElementById('commReplyReply-' + replyId).classList.add('hide');

}

function insertButtonClick(replyId) {
    document.getElementById('btnReplyInsert-' + replyId).addEventListener("click", function() {
       document.getElementById("commDetailFrm").submit();
    });
}

function deleteButtonClick(replyId) {
       let bardId = document.getElementById("bardId").value

       ajaxAPI("/comm/commAjaxWriteReplyDelete?bardId=" + bardId + "&replyId=" + replyId, null, "GET").then(response => {
            $('#commDetailFrm-' + replyId).remove();

       });
}

function replyButtonClick(replyId) {
    document.getElementById('commReplyReply-' + replyId).classList.remove('hide');
}

function fnreplyGpSeqMax() {
    let bardId = document.getElementById("bardId").value

    ajaxAPI("/comm/commAjaxWriteReplyGpSeqMax?bardId=" + bardId, null, "GET").then(response => {
            document.getElementById('writeReplyGpSeq').value = response.replyGpSeqMax
    });
}