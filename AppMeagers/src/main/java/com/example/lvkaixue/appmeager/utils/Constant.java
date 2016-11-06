package com.example.lvkaixue.appmeager.utils;

/**
 * Created by ll on 2016/8/12.
 */
public class Constant {
    //添加微博内容编辑框最多字数
    public final static int fontCount = 140;

    //网络连接标记
    public final static int netConnSucess = 0;

    //获取相机照片请求码
    public final static int cameraRequestCode = 0;

    //本地照片请求码
    public final static int locationRequestCode = 1;

    //页面数量
    public final static int pageCount = 3;

    //页面标题
    public final static String[] pageTitle = {"微博", "原创", "相册"};

    //信息页面的子页面标题
    public final static String[] inforPageTitle = {"我", "私信"};

    //信息页面的子页面的数量
    public final static int inforPageCount = 2;

    //网络信息参数码
    public final static String errcode = "0";

    //网络信息参数码
    public final static String ret = "0";

    //是否还能从网络中拉去数据标记
    public final static String isPullData = "0";

    //是否还能从网络中拉去数据已经拉去完
    public final static String PullDataEnd = "1";

    //返回值类型
    public final static String formatKey = "format";

    public final static String formatValue = "json";

    public final static String oauthConsumerKeyKey = "oauth_consumer_key";

    public final static String accessTokenKey = "access_token";

    public final static String openIdKey = "openid";

    public final static String reqnumKey = "reqnum";

    public final static String startindexKey = "startindex";

    //获取2：转播列表和点评列表都获取。
    public final static String flagValue="2";

    //获取每一页条目的取值
    public final static String flagKey= "flag";

    public final static String rootidKey = "rootid";

    public final static String pagetimeKey = "pagetime";

    public final static String pageflagKey = "pageflag";

    public final static String twitteridKey="twitterid";

    public final static String reqnumValue = "100";

    public final static int FriendItemCount = 3;

    public final static String [] strTitle ={"转发","评论","赞"};

    public final static String contentKey = "content";

    public final static String syncFlagKey = "syncflag";

    public static final String ZHUANFA_KEY = "item";

    public static final String ZHUANFA_VAUE = "zhuanfa";

    public static final String ITEM_KEY = "item_id";

    public static CharSequence[] IndexPageTitle ={"微博","原创","相册"};

    //大号字体
    public static int maxFontSize = 20;

    //较大字体
    public static int manFontSize = 11;

    //默认字体
    public static int defualtFontSize = 15;

    //广播过滤
    public static String downLoadImg = "downLoadImg";

    public static String getUrl = "http://"+ StringUtils.getIP()+"/MeagerWebService/ZhuanfaServlet";
}
