package com.example.lvkaixue.appmeager.bean;

import com.example.lvkaixue.appmeager.utils.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

/**
 * Created by lvkaixue on 2016/8/30.
 * 关注朋友
 */
@HttpResponse(parser = JsonResponseParser.class)
public class AboutPerBean {
    public String ret;
    public String errcode;
    public String msg;

    @Override
    public String toString() {
        return "AboutPerBean{" +
                "ret='" + ret + '\'' +
                ", errcode='" + errcode + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
