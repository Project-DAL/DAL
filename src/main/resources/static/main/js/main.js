"use strict";

/* 1. Variables *******************************************************************************************************/
/* 1.1 Service Url String */
const strProvinceList = '/rest/provinceList';    // 셀렉트 지역 목록 URL

/* 1.3 Search Condition Object */
let inputObjSearchText  = document.getElementById('searchText'); // 검색어

/* 1.4 Search Object */
let selectObjProvince = document.getElementById("search-province");     // 시/도 셀렉트
let selectObjCity     = document.getElementById("search-city");         // 시/군/구 셀렉트
let selectObjTown     = document.getElementById("search-town");         // 읍/면/동 셀렉트

/* 1.5 List & ViewPage Object */
let divObjStoreList = document.getElementById("store-list");        // 가게 목록 전체 박스
let divObjStoreBox = document.getElementById("store-box");          // 가게 목록 개별 박스

/* 1.6 Process(CRUD) Button Object */
let btnList     = document.getElementById('btnList');       // 검색 버튼

/* 1.7 Etc Variables (Json Object, HTML String, Temporary) */
let jsonData       = {};   // 요청 파라미터
let positions = [];     // 검색 결과 가게 장소 표시


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
let swLat = "";         // 영역정보의 남서위도
let swLng = "";         // 영역정보의 남서경도
let neLat = "";         // 영역정보의 북동위도
let neLng = "";         // 영역정보의 북동경도
let swLatstr = "";      // 영역정보의 남서위도
let swLngstr = "";      // 영역정보의 남서경도
let neLatstr = "";      // 영역정보의 북동위도
let neLngstr = "";      // 영역정보의 북동경도



/** Initialize */
document.addEventListener('DOMContentLoaded', function() {
    console.log("DOMContent");
    /* CRUD 버튼 이벤트 등록 */
    addEventListenerCRUDBtn();

    /* 셀렉트박스 행정 구역 목록 */
    fnSelectProvince();

    /* GeoLocation 현재 접속 위치 조회 */
    fnGeoLocation();



});

/** CRUD 버튼 이벤트 등록 */
function addEventListenerCRUDBtn(){
    btnList.addEventListener("click", fnAjaxList);
    selectObjProvince.addEventListener("change", fnSelectCity);
    selectObjCity.addEventListener("change", fnSelectTown);
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
                message = '<div style="padding:5px;">현재접속위치</div>'; // 인포윈도우에 표시될 내용입니다

            // 마커와 인포윈도우를 표시합니다
            console.log("locPosition type: " , typeof locPosition);
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

    // 확인용 (개발 완료 이후 삭제)
    var message = '<p>영역좌표는 남서쪽 위도, 경도는  ' + swLatlng.toString() + '이고 <br>';
    message += '북동쪽 위도, 경도는  ' + neLatlng.toString() + '입니다 </p>';
    var resultDiv = document.getElementById('result');
    resultDiv.innerHTML = message;

}

/** 셀렉트 박스 지역 선택하기 */
// 시/도 목록
function fnSelectProvince(){
    ajaxAPI(strProvinceList, null, "GET").then(response => {
        for(let i = 0; i < response.length; i++) {
            // option add
            let optionElement = document.createElement('option');
            optionElement.value = response[i].province_id;
            optionElement.text = response[i].province_nm;
            selectObjProvince.add(optionElement);
        }
    });
}
// 시/도 선택에 따른 시/군/구 목록
function  fnSelectCity() {
    console.log("selectObjProvince id : " + selectObjProvince.value);
    console.log("selectObjProvince text : " + selectObjProvince.options[selectObjProvince.selectedIndex].text);

    jsonData.province_id = selectObjProvince.value;

    // 기존 시/군/구 비우기
    while (selectObjCity.options.length > 1) {
        selectObjCity.remove(1);
    }

    // 기존 읍/면/동 비우기
    while (selectObjTown.options.length > 1) {
        selectObjTown.remove(1);
    }

    ajaxAPI('/rest/cityList?province_id='+selectObjProvince.value, null, "GET").then(response => {
        for(let i=0; i < response.length; i++){
            // option add
            let optionElement = document.createElement('option');
            optionElement.value = response[i].city_id;
            optionElement.text = response[i].city_nm;
            selectObjCity.add(optionElement);
        }
    });
}
// 시/군/구 선택에 따른 읍/면/동 목록
function fnSelectTown () {
    console.log("fnSelectTown");

    // 기존 읍/면/동 비우기
    while (selectObjTown.options.length > 1) {
        selectObjTown.remove(1);
    }

    console.log("province_id: " +  selectObjProvince.value);
    console.log("city_id: " +  selectObjCity.value);

    ajaxAPI('/rest/townList?province_id=' + selectObjProvince.value + '&city_id=' + selectObjCity.value, null, "GET").then(response => {
        for(let i=0; i < response.length; i++){
            // option add
            let optionElement = document.createElement('option');
            optionElement.value = response[i].town_id;
            optionElement.text = response[i].town_nm;
            selectObjTown.add(optionElement);
        }
    })

}

/** 지역 및 주류에 따른 검색 결과 조회 */
function fnAjaxList(){
    console.log("fnAjaxList");

    let province_ = selectObjProvince.options[selectObjProvince.selectedIndex].text;
    let city_ = selectObjCity.options[selectObjCity.selectedIndex].text;
    let town_ = selectObjTown.options[selectObjTown.selectedIndex].text;
    let locationText = province_ + " " + city_ + " " + town_;

    console.log("locationText:  " + locationText);
    console.log("inputObjSearchText: " + inputObjSearchText.value);
    //console.log("swLatlng: " + swLatlng);
    //console.log("neLatlng: " + neLatlng);

    if(province_ == "시/도") {
        alert("검색할 지역을 선택하세요.");
        return false;
    }
    if(inputObjSearchText.value == "") {
        alert("검색할 주류를 입력하세요.");
        return false;
    }

    // 주소-좌표 변환 객체를 생성합니다
    let geocoder = new kakao.maps.services.Geocoder();

    // 주소로 좌표를 검색합니다
    geocoder.addressSearch(locationText, function (result, status) {
        // 정상적으로 검색이 완료됐으면
        if (status === kakao.maps.services.Status.OK) {

            console.log("정상적으로 검색");

            // 위도, 경도
            let xValue = parseFloat(result[0].x);
            let yValue = parseFloat(result[0].y);

            console.log("xValue: " + xValue);
            console.log("yValue: " + yValue);

            lat = xValue;   // 위도
            lon = yValue;   // 경도

            var coords = new kakao.maps.LatLng(yValue, xValue);

            map.setCenter(coords);

            // 지도 영역정보를 얻어옵니다
            bounds = map.getBounds();

            // 영역정보의 남서쪽 정보를 얻어옵니다
            swLatlng = bounds.getSouthWest();

            // 영역정보의 북동쪽 정보를 얻어옵니다
            neLatlng = bounds.getNorthEast();

            console.log("swLatlng2: " + swLatlng);
            console.log("neLatlng2: " + neLatlng);

            swLat = swLatlng.getLat().toString();         // 영역정보의 남서위도
            swLng = swLatlng.getLng().toString();         // 영역정보의 남서경도
            neLat = neLatlng.getLat().toString();         // 영역정보의 북동위도
            neLng = neLatlng.getLng().toString();         // 영역정보의 북동경도

            // 확인용 (개발 완료 이후 삭제)
            var message = '<p>영역좌표는 남서쪽 위도, 경도는  ' + swLatlng.toString() + '이고 <br>';
            message += '북동쪽 위도, 경도는  ' + neLatlng.toString() + '입니다 </p>';
            var resultDiv = document.getElementById('result');
            resultDiv.innerHTML = message;

            ajaxAPI('/rest/storeList?swLat='+ swLat + '&swLng='+ swLng + '&neLat='+ neLat + '&neLng='+ neLng + '&prod_tit='+ inputObjSearchText.value, null, "GET").then(response => {
                console.log("storeList ajax success");
                console.log("response: ", response);

                // 기존 positions 배열 비우기
                positions = [];

                // 조건에 따른 st_tb의 검색 결과의 개수만큼 가게 표시하기
                for (let i=0; i < response.length; i++) {

                    // positions에 검색 결과 가게 정보 추가하기
                    positions.push({
                        stId: response[i].st_id,
                        content: '<div>' + response[i].st_nm + '</div>',
                        latlng: new kakao.maps.LatLng(response[i].st_lattitude, response[i].st_longtitude)
                    })

                    // 가게 목록 HTML 추가하기
                    // divObjStoreList
                    // divObjStoreBox
                    divObjStoreList.insertAdjacentHTML('beforeend', `
                        <div class="store-box" id="store-box">
                            <div class="store-info">
                                <div class="store-info-img">img</div>
                                <div class="store-info-txt">
                                    <div class="store-info-name">restaurant name</div>
                                    <div class="store-info-score">
                                        <div>★★★★★</div>
                                        <div>4/8</div>
                                        <div>(185 reviews)</div>
                                    </div>
                                    <div class="store-info-hour">
                                        <div class="store-info-open">OPEN</div>
                                        <div class="store-info-closed">CLOSED</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    `);

                }

                console.log("positions: ", positions);

                for(let i=0; i < positions.length; i++) {
                    // 마커를 생성합니다
                    var marker = new kakao.maps.Marker({
                        map: map, // 마커를 표시할 지도
                        position: positions[i].latlng // 마커의 위치
                    });

                    marker.stId = positions[i].stId;

                    console.log("marker stId: " + marker.stId);

                    // 마커에 스토어 아이디 추가하기
                    console.log("marker: ", marker);

                    // 마커에 표시할 인포윈도우를 생성합니다
                    var infowindow = new kakao.maps.InfoWindow({
                        content: positions[i].content // 인포윈도우에 표시할 내용
                    });

                    // 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
                    infowindow.open(map, marker);

                    // 마커 클릭 이벤트
                    kakao.maps.event.addListener(marker, 'click', makeClickListener(marker.stId, response));

                    // 마커에 mouseover 이벤트를 등록한다
                    kakao.maps.event.addListener(marker, 'mouseover', function() {
                        console.log('마커에 mouseover 이벤트가 발생했습니다!');
                    });

                    // 마커에 mouseout 이벤트 등록
                    kakao.maps.event.addListener(marker, 'mouseout', function() {
                        console.log('마커에 mouseout 이벤트가 발생했습니다!');
                    });


                    // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
                    // 이벤트 리스너로는 클로저를 만들어 등록합니다
                    // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
                    //kakao.maps.event.addListener(marker, 'click', makeOverListener(map, marker, infowindow));
                    //kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));

                    // 마커 클릭 이벤트 - 클릭시 해당 가게 정보 불러오기
                    //kakao.maps.event.addListener(marker, 'click', makeClickListener(map, marker, infowindow));
                }



            })
        }
    })

}

/** 마커 클릭 이벤트 */
function makeClickListener(clickedStId, response) {
    return function() {
        console.log('마커를 클릭했습니다1' + clickedStId);
        console.log('마커를 클릭했습니다2', response);
    };
}


