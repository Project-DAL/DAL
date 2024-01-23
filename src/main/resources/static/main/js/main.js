"use strict";

/** Kakao Map API 생성 */
let mapContainer = document.getElementById('map');      //지도를 담을 영역의 DOM 레퍼런스
let mapOption = {                          //지도를 생성할 때 필요한 기본 옵션
    center: new kakao.maps.LatLng(33.450701, 126.570667),                      //지도의 중심좌표
    level: 3                                                                   //지도의 레벨(확대, 축소 정도)
};
let map = new kakao.maps.Map(mapContainer, mapOption);         //지도 생성 및 객체 리턴
let lat = "";           // 위도
let lon = "";           // 경도
let bounds = "";        // 지도 영역 정보
let swLatlng = "";      // 영역정보의 남서쪽 정보
let neLatlng = "";      // 영역정보의 북동쪽 정보


/** Initialize */
document.addEventListener('DOMContentLoaded', function() {
    console.log("DOMContent");
    /* CRUD 버튼 이벤트 등록 */
    addEventListenerCRUDBtn();

    /* GeoLocation 현재 접속 위치 조회 */
    fnGeoLocation();

});

/** CRUD 버튼 이벤트 등록 */
function addEventListenerCRUDBtn(){
    //btnSearch.addEventListener("click", fnSearchLocation2);
}

/** Kakao MAP function */
/* 지도에 사용자 컨트롤 올리기 */
function setMapType(maptype) {
    var roadmapControl = document.getElementById('btnRoadmap');
    var skyviewControl = document.getElementById('btnSkyview');
    if (maptype === 'roadmap') {
        map.setMapTypeId(kakao.maps.MapTypeId.ROADMAP);
        roadmapControl.className = 'selected_btn';
        skyviewControl.className = 'unselected_btn';
    } else {
        map.setMapTypeId(kakao.maps.MapTypeId.HYBRID);
        skyviewControl.className = 'selected_btn';
        roadmapControl.className = 'unselected_btn';
    }
}

/* 지도 확대, 축소 컨트롤에서 확대 버튼을 누르면 호출되어 지도를 확대하는 함수입니다 */
function zoomIn() {
    map.setLevel(map.getLevel() - 1);
}

/* 지도 확대, 축소 컨트롤에서 축소 버튼을 누르면 호출되어 지도를 확대하는 함수입니다 */
function zoomOut() {
    map.setLevel(map.getLevel() + 1);
}

/* GeoLocation 현재 접속 위치 조회 */
function fnGeoLocation() {
    console.log("fnGeoLocation start");
    // HTML5의 geolocation으로 사용할 수 있는지 확인합니다
    if (navigator.geolocation) {

        // GeoLocation을 이용해서 접속 위치를 얻어옵니다
        navigator.geolocation.getCurrentPosition(function(position) {

             lat = position.coords.latitude;  // 현재 접속 위치 위도
             lon = position.coords.longitude; // 현재 접속 위치 경도

            var locPosition = new kakao.maps.LatLng(lat, lon), // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
                message = '<div style="padding:5px;">'+ lat + ',' + lon +'</div>'; // 인포윈도우에 표시될 내용입니다

            // 마커와 인포윈도우를 표시합니다
            displayMarker(locPosition, message);

            // 지도 영역정보 얻기
            fnCornerCoordinates();

        });

    } else { // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다

        var locPosition = new kakao.maps.LatLng(33.450701, 126.570667),
            message = 'geolocation을 사용할수 없어요..'

        displayMarker(locPosition, message);
    }

}

/* 마커와 인포윈도우를 표시 */
// 지도에 마커와 인포윈도우를 표시하는 함수입니다
function displayMarker(locPosition, message) {

    // 마커를 생성합니다
    var marker = new kakao.maps.Marker({
        map: map,
        position: locPosition
    });

    var iwContent = message, // 인포윈도우에 표시할 내용
        iwRemoveable = true;

    // 인포윈도우를 생성합니다
    var infowindow = new kakao.maps.InfoWindow({
        content : iwContent,
        removable : iwRemoveable
    });

    // 인포윈도우를 마커위에 표시합니다
    infowindow.open(map, marker);

    // 지도 중심좌표를 접속위치로 변경합니다
    map.setCenter(locPosition);
}

/** 지도 영역정보 얻기 (각 모서리 좌표) */
function fnCornerCoordinates(){
    console.log("fnCornerCoordinates");

    // 지도 영역정보를 얻어옵니다
    bounds = map.getBounds();

    // 영역정보의 남서쪽 정보를 얻어옵니다
    swLatlng = bounds.getSouthWest();

    // 영역정보의 북동쪽 정보를 얻어옵니다
    neLatlng = bounds.getNorthEast();

    console.log("bounds: " + bounds);
    console.log("swLatlng: " + swLatlng);
    console.log("neLatlng: " + neLatlng);

    // 확인용 (개발 완료 이후 삭제)
    var message = '<p>영역좌표는 남서쪽 위도, 경도는  ' + swLatlng.toString() + '이고 <br>';
    message += '북동쪽 위도, 경도는  ' + neLatlng.toString() + '입니다 </p>';
    var resultDiv = document.getElementById('result');
    resultDiv.innerHTML = message;

}