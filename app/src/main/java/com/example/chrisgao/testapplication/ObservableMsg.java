package com.example.chrisgao.testapplication;

import java.util.Objects;

public class ObservableMsg {
    public String EventName;
    public Object Data;

    public ObservableMsg(String eventName, Object data){
        this.EventName = eventName;
        this.Data = data;
    }
}
