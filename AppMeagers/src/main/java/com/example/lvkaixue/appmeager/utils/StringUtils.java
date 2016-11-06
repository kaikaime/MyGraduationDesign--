package com.example.lvkaixue.appmeager.utils;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

import com.example.lvkaixue.appmeager.single.SingleUser;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lvkaixue on 2016/9/2.
 */
public class StringUtils {
    public static Map<String,String> getMap(){
        Map<String,String> mMap = new HashMap<>();
        mMap.put(Constant.formatKey, Constant.formatValue);
        mMap.put(Constant.openIdKey,SingleUser.getSingleUser().getOpenId());
        mMap.put(Constant.accessTokenKey,SingleUser.getSingleUser().getAccessToken());
        mMap.put(Constant.oauthConsumerKeyKey,SingleUser.getSingleUser().getOauthConsumerKey());
        return mMap;
    }

    //wifiIp检查
    public static String checkWifiIp(Context context){
        String wifiIp = XUtil.getWifiIP(context);
        if(wifiIp == null){
            return null;
        }
        return "http://192.168.3.9/MeagerWebService/LoginServletIndex";
    }
    //将long类型转化为日期date类型
    public static String getDate(String time){
        Calendar c= Calendar.getInstance();
        c.setTimeInMillis(Long.parseLong(time));
        return new SimpleDateFormat("mm-dd hh:mm").format(c.getTime());
    }

    //设置文本字体大小
    public static int getFontSize(){
        return AppBaseApplication.getFontSize();
    }
    public static void setFontSize(int fontSize){
        AppBaseApplication.setFontSize(fontSize);
    }
    //设置apk路劲
    public static String apkUri(){
        return new File("sdcard/IndexAppApk").getPath()+".apk";
    }
    //获取手机屏幕的大小
    public static int[] getPhoneSize(Context mCt){
        int[] psize = new int[2];
        DisplayMetrics metrics = new DisplayMetrics();
        ((AppCompatActivity) mCt).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        psize[0] = metrics.widthPixels;
        psize[1] = metrics.heightPixels;
        return psize;
    }

    /**时间换算比较**/
    public static boolean sunafu(String beforTime){
            int befor = Integer.parseInt(beforTime);
            int now = Integer.parseInt(new SimpleDateFormat("yyyyMMdd").format(new Date()));
            if(now - befor<=300){
                return true;
            }else {
                return false;
            }
    }

    /**图片下载地址**/
    public static String saveFilePath() {
        String path = Environment.getExternalStorageDirectory().getPath();
        path = path + "/mImg/";
        File file = new File(path);
        if(!file.exists()){
            file.mkdir();
        }
        return file.getPath();
    }

    //ip
    public static String getIP(){
        return "192.168.43.71";
    }
}
