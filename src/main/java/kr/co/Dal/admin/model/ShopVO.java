package kr.co.Dal.admin.model;

import lombok.Data;

import java.util.Date;

@Data
public class ShopVO {
    private int stId;
    private int provinceId;
    private int stTypeId;
    private String stNm;
    private String stCity;
    private String stAddr;
    private int stStts;
    private double stLongtitude;
    private double stLattitude;
    private String stXy;
    private String stInfo;
    private String stDetail;
    private String stTel;
    private String stThumbs;
    private String stRdate;
    private String stUdate;
    private String stWdate;
    private String stHoursOpen;
    private String stHoursClosed;
    private String stLiquorNo;
}
