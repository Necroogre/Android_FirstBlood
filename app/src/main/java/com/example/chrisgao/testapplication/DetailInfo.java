package com.example.chrisgao.testapplication;

import java.util.ArrayList;

/**
 * Created by ChrisGao on 2015/10/23.
 */
public class DetailInfo {
    public DetailInfo(String id,String name,int qty){
        this.set_id(id);
        this.setName(name);
        this.setQty(qty);
    }

    private String _id;
    private String name;
    private int qty;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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
