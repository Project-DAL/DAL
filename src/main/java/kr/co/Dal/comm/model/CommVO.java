package kr.co.Dal.comm.model;

import kr.co.Dal.cmmn.model.CmmnVO;
import lombok.*;

import java.sql.Date;

@Getter
@EqualsAndHashCode(callSuper = false)
@Builder
public class CommVO extends CmmnVO {
    private int bardId;        // 게시글ID
    private int userId;        // 회원ID
    private int bardType;      // 게시글 유형
    private String bardTit;    // 제목
    private String bardCn;     // 내용
    private Date bardRdate;  // 등록일
    private int bardCnt;       // 조회수
    private int bardLikeCnt;   // 좋아요수
    private int bardStts;      // 게시글상태(1:유효/0:무효)
    private int cateType;      // 카테고리 유형

    //getter
    public int getBardId() {
        return bardId;
    }

    public int getUserId() {
        return userId;
    }

    public int getBardType() {
        return bardType;
    }

    public String getBardTit() {
        return bardTit;
    }

    public String getBardCn() {
        return bardCn;
    }

    public Date getBardRdate() {
        return bardRdate;
    }

    public int getBardCnt() {
        return bardCnt;
    }

    public int getBardLikeCnt() {
        return bardLikeCnt;
    }

    public int getBardStts() {
        return bardStts;
    }

    public int getCateType() {
        return cateType;
    }


    //setter
    public void setBardId(int bardId) {
        this.bardId = bardId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setBardType(int bardType) {
        this.bardType = bardType;
    }

    public void setBardTit(String bardTit) {
        this.bardTit = bardTit;
    }

    public void setBardCn(String bardCn) {
        this.bardCn = bardCn;
    }

    public void setBardRdate(Date bardRdate) {
        this.bardRdate = bardRdate;
    }

    public void setBardCnt(int bardCnt) {
        this.bardCnt = bardCnt;
    }

    public void setBardLikeCnt(int bardLikeCnt) {
        this.bardLikeCnt = bardLikeCnt;
    }

    public void setBardStts(int bardStts) {
        this.bardStts = bardStts;
    }

    public void setCateType(int cateType) {
        this.cateType = cateType;
    }

    // bardId, bardTit, bardRdate 필드만을 포함한 toString() 메서드 직접 작성
    @Override
    public String toString() {
        return "CommVO{" +
                "bardId=" + bardId +
                ", bardTit='" + bardTit + '\'' +
                ", bardRdate=" + bardRdate +
                '}';
    }
}
