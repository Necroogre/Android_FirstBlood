package com.example.chrisgao.testapplication;

/**
 * Created by ChrisGao on 2015/11/2.
 */
public class DataInfo {
    public DataInfo(String code,String name,int qty){
        this.setCode(code);
        this.setName(name);
        this.setQty(qty);
    }

    private String code;
    private String name;
    private int qty;
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
