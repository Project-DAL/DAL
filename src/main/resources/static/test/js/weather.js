document.addEventListener('DOMContentLoaded', function (){

    console.log("1, page loaded");

    console.log("2, Ajax 전송");

    let url = 'http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst'; /*URL*/
    let jsonParam = {};

    jsonParam.serviceKey = "5CZW8vE4f16CFpvpKdt5RwisoNCWGjCG%2FlZqcpESy8NaGCdcB8vPI6aDpHCKyQcqTImAERfoVVHuzLJsaDHitg%3D%3D";
    jsonParam.pageNo = "1";
    jsonParam.numOfRows = "10";
    jsonParam.dataType = "JSON";
    jsonParam.base_date = "20231124";
    jsonParam.base_time = "0600";
    jsonParam.nx = "63";
    jsonParam.ny = "124";

    let queryParams = '?' + encodeURIComponent('serviceKey') + '=' + '5CZW8vE4f16CFpvpKdt5RwisoNCWGjCG%2FlZqcpESy8NaGCdcB8vPI6aDpHCKyQcqTImAERfoVVHuzLJsaDHitg%3D%3D'; /*Service Key*/
    queryParams += '&' + encodeURIComponent('pageNo') + '=' + encodeURIComponent('1'); /**/
    queryParams += '&' + encodeURIComponent('numOfRows') + '=' + encodeURIComponent('10'); /**/
    queryParams += '&' + encodeURIComponent('dataType') + '=' + encodeURIComponent('JSON'); /**/
    queryParams += '&' + encodeURIComponent('base_date') + '=' + encodeURIComponent('20231124'); /**/
    queryParams += '&' + encodeURIComponent('base_time') + '=' + encodeURIComponent('0600'); /**/
    queryParams += '&' + encodeURIComponent('nx') + '=' + encodeURIComponent('63'); /**/
    queryParams += '&' + encodeURIComponent('ny') + '=' + encodeURIComponent('124'); /**/

    Common.ajax(url, jsonParam, function (result){
        console.log("result:" ,result);
    })

})