package com.example.lvkaixue.appmeager.bean;

import com.example.lvkaixue.appmeager.utils.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lvkaixue on 2016/8/8.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class MeaterBean implements Serializable{
    public MeaterData data;
    public class MeaterData implements Serializable{
        public String birth_day;
        public String birth_month;
        public String birth_year;
        public String city_code;
        public List<Comp> comp;
        public class Comp implements Serializable{
            public String begin_year;
            public String company_name;
            public String department_name;
            public String end_year;
            public String id;
        }
        public String country_code;
        public List<Edu> edu;
        public class Edu implements Serializable{
            public String departhmentid;
            public String id;
            public String level;
            public String schoolid;
            public String year;
        }
        public String email;
        public String exp;
        public String fansnum;
        public String favnum;
        public String head;
        public String homecity_code;
        public String homecounttry_code;
        public String homepage;
        public String homeprovince_code;
        public String https_head;
        public String idolnum;
        public String indestry_code;
        public String introduction;
        public String isent;
        public String ismyblack;
        public String ismyfans;
        public String ismyidol;
        public String isrealname;
        public String isvip;
        public String level;
        public String location;
        public String mutual_fans_num;
        public String name;
        public String nick;
        public String openid;
        public String province_code;
        public String regtime;
        public String send_private_flag;
        public String sex;
        public List<Tag> tag;
        public class Tag implements Serializable{
            public String id;
            public String name;
        }
        public List<Tweetinfo> tweetinfo;
        public class Tweetinfo implements Serializable{
            public String city_code;
            public String country_code;
            public String emotiontype;
            public String emotonurl;
            public String from;
            public String fromurl;
            public String geo;
            public String id;
            public List<String> image;
                        public class  Image implements Serializable{
                            public String url;
                        }
            public String latitude;
            public String location;
            public String longitude;
            public List<Music> music;
                        public class  Music implements Serializable{
                            public String url;
                        }
            public String origtext;
            public String province_code;
            public String self;
            public String status;
            public String text;
            public String timestamp;
            public String type;
            public List<Video> video;
                        public class  Video implements Serializable{
                            public String url;
                        }
        }
        public String tweetnum;
        public String verifyinfo;
    }
        public String errcode;
        public String msg;
        public String ret;
        public String seqid;

    @Override
    public String toString() {
        return "MeaterBean{" +
                "data=" + data +
                ", errcode='" + errcode + '\'' +
                ", msg='" + msg + '\'' +
                ", ret='" + ret + '\'' +
                ", seqid='" + seqid + '\'' +
                '}';
    }
}
