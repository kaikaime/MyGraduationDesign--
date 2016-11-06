package com.example.lvkaixue.appmeager.bean;

import com.example.lvkaixue.appmeager.utils.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

import java.util.List;

/**
 * Created by lvkaixue on 2016/8/30.
 * 粉丝=
 */
@HttpResponse(parser = JsonResponseParser.class)
public class FansBean {
    public String errcode;
    public String msg;
    public String ret;
    public String seqid;
    public Data data;
        public class Data {
            public String curnum;
            public String hasnext;
            public String nextstartpos;
            public String timestamp;
            public List<Info> info;
                public class Info{
                    public String city_code;
                    public String country_code;
                    public String fansnum;
                    public String head;
                    public String idolnum;
                    public boolean isfans;
                    public boolean isidol;
                    public String isrealname;
                    public String isvip;
                    public String location;
                    public String name;
                    public String nick;
                    public String openid;
                    public String province_code;
                    public String sex;
                    public List<Tag> tag;
                        public class Tag{
                            public String id;
                            public String name;
                        }
                    public List<Tweet> tweet;
                        public class Tweet{
                            public String from;
                            public String id;
                            public String text;
                            public String timestamp;
                        }
                }
        }

    @Override
    public String toString() {
        return "FansBean{" +
                "errcode='" + errcode + '\'' +
                ", msg='" + msg + '\'' +
                ", ret='" + ret + '\'' +
                ", seqid='" + seqid + '\'' +
                ", data=" + data +
                '}';
    }

}
