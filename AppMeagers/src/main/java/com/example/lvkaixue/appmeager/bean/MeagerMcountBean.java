package com.example.lvkaixue.appmeager.bean;

import com.example.lvkaixue.appmeager.utils.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lvkaixue on 2016/8/28.
 * 获取一条微博的转播或评论信息列表。
 *  https://graph.qq.com/t/get_repost_list
 */
@HttpResponse(parser = JsonResponseParser.class)
public class MeagerMcountBean implements Serializable{
    public String seqid;
    public String ret;
    public String msg;
    public String errcode;
    public Data data;
        public class Data implements Serializable{
            public String hasnext;
            public String timestamp;
            public String totalnum;
            public String user;
            public List<Info> info;
                public class Info implements Serializable{
                        public String city_code;
                        public String count;
                    public String country_code;
                    public String emotiontype;
                    public String emotionurl;
                    public String from;
                    public String fromurl;
                    public String geo;
                    public String head;
                    public String https_head;
                    public String id;
                    public String isrealname;
                    public String isvip;
                    public String jing;
                    public String latitude;
                    public String likecount;
                    public String location;
                    public String longitude;
                    public String mcount;
                    public String name;
                    public String nick;
                    public String openid;
                    public String origtext;
                    public Pic pic;
                        public class Pic{
                            public List<Infor> info;
                            public class Infor implements Serializable{
                                public List<Integer> pic_XDPI;
                                public List<Integer> pic_YDPI;
                                public List<Integer> pic_height;
                                public List<Integer> pic_size;
                                public List<Integer> pic_type;
                                public List<Integer> pic_width;
                                public List<String> url;
                            }
                        }
                    public String province_code;
                    public String readcount;
                    public String self;
                    public Source source;
                        public class Source implements Serializable{
                            public String city_code;
                            public String count;
                            public String country_code;
                            public String from;
                            public String fromurl;
                            public String geo;
                            public String head;
                            public String https_head;
                            public String id;
                            public List<String> iamge;
                            public String isvip;
                            public String jing;
                            public String latitude;
                            public String likecount;
                            public String location;
                            public String longitude;
                            public String mcount;
                            public String name;
                            public String nick;
                            public String opened;
                            public String origtext;
                            public String province_code;
                            public String readcount;
                            public String self;
                            public String status;
                            public String text;
                            public String timestamp;
                            public String type;
                            public String wei;
                        }
                    public String status;
                    public String text;
                    public String timestamp;
                    public String type;
                    public String wei;
                }
        }
}
