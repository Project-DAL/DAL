/* ajax List */

const Pagination = {
    appendPagination: function (jsonPageNavigation) {
        let currentPageNo       = jsonPageNavigation.currentPageNo;           // 현재 페이지 번호
        let totalRecordCount    = jsonPageNavigation.totalRecordCount;        // 전체 게시물 건수
        let recordCountPerPage  = jsonPageNavigation.recordCountPerPage;      // 한 페이지당 보여줄 목록 수
        let totalPageCount      = Math.ceil(totalRecordCount / recordCountPerPage);                          // 전체 페이지 수
        let loopStart           = ((Math.ceil(currentPageNo / 10) - 1) * 10) + 1;                            // 페이지 번호 (시작)
        let loopEnd             = (loopStart + 10) > totalPageCount ? (totalPageCount + 1) : (loopStart + 10);  // 페이지 번호 (끝)
        let pagePreNo           = ((Math.ceil(currentPageNo / 10) - 1) * 10);                                // 이전 페이지 번호
        let pageNextNo          = ((Math.ceil(currentPageNo / 10)) * 10) + 1;                                // 다음 페이지 번호
        let paginationArea      = document.getElementById(jsonPageNavigation.targetObjArea);               // 페이징이 표시될 영역

        // 페이징 영역 초기화
        paginationArea.innerHTML = `<ul><li></li></ul>`;

        // 페이징 버튼이 표시될 영역
        let paginationLi = paginationArea.querySelector('li');

        // 첫 페이지 버튼 Append
        paginationLi.insertAdjacentHTML('beforeend', `
            <a class="pagbox material-icons">keyboard_double_arrow_left</a>`);

        // 첫 페이지 버튼 이벤트 처리
        paginationLi.querySelector('a:last-child').addEventListener('click', function() {
            jsonPageNavigation.currentPageNo = 1;
            window[jsonPageNavigation.searchFunction]();
        });

        // 이전 페이지 버튼 Append
        paginationLi.insertAdjacentHTML('beforeend', `
            <a class="pagbox material-icons">keyboard_arrow_left</a>`);

        // 이전 페이지 버튼 이벤트 처리
        paginationLi.querySelector('a:last-child').addEventListener('click', function() {
            if (jsonPageNavigation.currentPageNo > 1) {
                jsonPageNavigation.currentPageNo--;
                window[jsonPageNavigation.searchFunction]();
            }
        });

        // 페이지 번호 버튼 생성 및 이벤트 처리
        for(let i = loopStart; i < loopEnd; i++) {
            paginationLi.insertAdjacentHTML('beforeend', `<a class="pagbox label-pg" href="#">${i}</a>`);
            paginationLi.querySelector('a:last-child').addEventListener('click', function() {
                jsonPageNavigation.currentPageNo = i;
                window[jsonPageNavigation.searchFunction]();
            });

            // 현재 페이지일 경우 버튼 하이라이트
            if(i === jsonPageNavigation.currentPageNo) paginationArea.querySelector('a:last-child').classList.add('active');
        }

        // 다음 페이지 버튼 생성
        paginationLi.insertAdjacentHTML('beforeend', `
            <a class="pagbox material-icons">keyboard_arrow_right</a>`);

        // 다음 페이지 버튼 이벤트 처리
        paginationLi.querySelector('a:last-child').addEventListener('click', function() {
            if (jsonPageNavigation.currentPageNo < totalPageCount) {
                jsonPageNavigation.currentPageNo++;
                window[jsonPageNavigation.searchFunction]();
            }
        });

        // 마지막 페이지 버튼 생성
        paginationLi.insertAdjacentHTML('beforeend', `
            <a class="pagbox material-icons">keyboard_double_arrow_right</a>`);

        paginationLi.querySelector('a:last-child').addEventListener('click', function() {
            jsonPageNavigation.currentPageNo = totalPageCount;
            window[jsonPageNavigation.searchFunction]();
        });



    }
}