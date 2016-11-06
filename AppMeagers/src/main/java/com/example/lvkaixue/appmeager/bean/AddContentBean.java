package com.example.lvkaixue.appmeager.bean;

import com.example.lvkaixue.appmeager.utils.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

/**
 * Created by ll on 2016/8/12.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class AddContentBean {
    public String ret;
    public String msg;
    public String errcode;
    public Data data;
    public class Data{
        public String id;
        public String time;
    }

    @Override
    public String toString() {
        return "你的数据是"+"AddContentBean{" +
                "ret='" + ret + '\'' +
                ", msg='" + msg + '\'' +
                ", errcode='" + errcode + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
