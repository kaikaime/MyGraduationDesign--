package com.example.lvkaixue.appmeager.bean;

import com.example.lvkaixue.appmeager.utils.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

import java.util.ArrayList;

/**
 * Created by kaikaime on 2016/10/26.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class PingLunBean {
    public String ret;
    public String msg;
    public ArrayList<PingLunBeanData> object;

    public class PingLunBeanData {
        public String id;
        public String fName;
        public String mContent;
        public String mTime;
        public boolean mFlag;
        public String mAddress;
    }
}
