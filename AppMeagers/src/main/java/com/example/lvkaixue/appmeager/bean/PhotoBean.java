package com.example.lvkaixue.appmeager.bean;

import com.example.lvkaixue.appmeager.utils.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lvkaixue on 2016/9/3.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class PhotoBean implements Serializable{
    @Override
    public String toString() {
        return "PhotoBean{" +
                "ret='" + ret + '\'' +
                ", albumnum='" + albumnum + '\'' +
                ", msg='" + msg + '\'' +
                ", album=" + album +
                '}';
    }

    public String ret;
    public String albumnum;
    public String msg;
    public List<Album> album;
    public class Album implements Serializable{
        public String albumid;
        public String classid;
        public String coverurl;
        public String createtime;
        public String desc;
        public String name;
        public String picnum;
        public String priv;
    }
}
