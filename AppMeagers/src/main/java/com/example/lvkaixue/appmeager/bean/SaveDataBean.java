package com.example.lvkaixue.appmeager.bean;

import java.io.Serializable;

/**
 * Created by lvkaixue on 2016/9/17.
 */
public class SaveDataBean implements Serializable{
    private String id;
    private String mConData;
    private String mConDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getmConData() {
        return mConData;
    }

    public void setmConData(String mConData) {
        this.mConData = mConData;
    }

    public String getmConDate() {
        return mConDate;
    }

    public void setmConDate(String mConDate) {
        this.mConDate = mConDate;
    }
}
