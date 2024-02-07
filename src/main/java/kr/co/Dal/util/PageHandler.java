package kr.co.Dal.util;

import lombok.Data;

/* Model List */
@Data
public class PageHandler {
    private int totalCnt;       // 총 게시물 개수
    // private int naviSize=10;    // 페이지 내비게이션의 크기 (한 페이지에 보일 글 개수)
    private int totalPage;      // 전체 페이지의 수
    private int beginPage;      // 내비게이션의 첫 번째 페이지
    private int endPage;        // 내비게이션의 마지막 페이지
    private boolean showPrev;   // 이전 페이지 링크 여부
    private boolean showNext;   // 다음 페이지 링크 여부
    private SearchCondition sc;


    public PageHandler(int totalCnt, SearchCondition sc, int naviSize){
        this.totalCnt = totalCnt;
        this.sc = sc;
        doPaging(totalCnt, sc, naviSize);
    }

    public void doPaging(int totalCnt, SearchCondition sc, int naviSize){
        this.totalCnt = totalCnt;

        totalPage = (int)Math.ceil(totalCnt/(double)sc.getPageSize());
        beginPage = (sc.getPage() - 1)/naviSize * naviSize + 1;
        endPage = Math.min(beginPage + naviSize -1, totalPage);
        showPrev = beginPage !=1;
        showNext = endPage != totalPage;

    }
}