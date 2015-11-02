package com.example.chrisgao.testapplication;

import java.util.ArrayList;

/**
 * Created by ChrisGao on 2015/10/30.
 */
public class DetailViewModel {
    public DetailViewModel(String orderCode, int scanQty, int verifiedQty, ArrayList<DetailInfo> detailInfos){
        this.setOrderCode(orderCode);
        this.setScanQty(scanQty);
        this.setVerifiedQty(verifiedQty);
        this.setDetailInfos(detailInfos);
    }

    private String orderCode;
    private int scanQty;
    private int verifiedQty;
    private ArrayList<DetailInfo> detailInfos;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String code) {
        this.orderCode = code;
    }

    public int getScanQty() {
        return scanQty;
    }

    public void setScanQty(int scanQty) {
        this.scanQty = scanQty;
    }

    public int getVerifiedQty() {
        return verifiedQty;
    }

    public void setVerifiedQty(int verifiedQty) {
        this.verifiedQty = verifiedQty;
    }

    public ArrayList<DetailInfo> getDetailInfos() {
        return detailInfos;
    }

    public void setDetailInfos(ArrayList<DetailInfo> detailInfos) {
        this.detailInfos = detailInfos;
    }
}
