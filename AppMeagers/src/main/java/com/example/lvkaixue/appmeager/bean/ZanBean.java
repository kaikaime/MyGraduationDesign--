package com.example.lvkaixue.appmeager.bean;

import com.example.lvkaixue.appmeager.utils.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

import java.util.ArrayList;

/**
 * Created by kaikaime on 2016/10/26.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class ZanBean {
    public String ret;
    public String msg;
    public ArrayList<ZanBeanData> object;

    public class ZanBeanData {
        public String id;
        public String p_id;
        public String z_id;
    }
}
