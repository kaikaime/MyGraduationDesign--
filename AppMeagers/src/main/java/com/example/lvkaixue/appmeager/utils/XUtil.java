package com.example.lvkaixue.appmeager.utils;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

/**
 * 用于网络请求
 */
public class XUtil {
    private static Object d;

    private XUtil(){}
    public final static String POSTSYSC = "postSysc";
    public final static String GETSYSC = "postSysc";
    public final static String POST = "post";
    public final static String GET = "get";

    //发送get异步请求
    public static <T>Callback.Cancelable Get(String url,Map<String,String> mMap){
        return GetPost(url,mMap,GET, null);
    }

    //发送post异步请求
    public static <T>Callback.Cancelable post(String url, Map<String, String> mMap,ChildHandler.StaticChildHandler handler) {
        if (handler!=null) {
            return GetPost(url, mMap, POST,handler);
        }else{
            return GetPost(url, mMap, POST, null);
        }
    }


    //发送get同步请求
    public static  <T> T getSysc(String url,Map<String,String> mMap,Class<T> mClass) throws Throwable {
        return GetPostSysc(url, mMap, mClass, GETSYSC);
    }

    //发送post同步请求
    public static <T> T postSysc(String url,Map<String,String> mMap,Class<T> mClass) throws Throwable {
       return GetPostSysc(url,mMap,mClass,POSTSYSC);
    }

    //同步请求
    public static <T> T GetPostSysc(String url,Map<String,String> mMap,Class<T> mClass,String methodName) throws Throwable {
        if (url != null && !"".equals(url)&& mMap != null) {
            RequestParams requestParams = new RequestParams(url);
            for(Map.Entry<String,String>  mSet : mMap.entrySet()){
                String strName = mSet.getKey();
                String strValue = mSet.getValue();
                System.out.println("strName: "+strName+" strValue: "+strValue);
                requestParams.addBodyParameter(strName, strValue);
            }
            if(GETSYSC.equals(methodName)){
                return x.http().getSync(requestParams,mClass);
            }else if(POSTSYSC.equals(methodName)){
                return x.http().postSync(requestParams,mClass);
            }
        }
        return null;
    }


    //get异步请求
    private static <T> Callback.Cancelable GetPost(String url, Map<String, String> mMap, String methName, ChildHandler.StaticChildHandler handler){
        if (url != null && !"".equals(url)&& mMap != null) {
            RequestParams requestParams = new RequestParams(url);
            for(Map.Entry<String,String>  mSet : mMap.entrySet()){
                String strName = mSet.getKey();
                String strValue = mSet.getValue();
                requestParams.addBodyParameter(strName, strValue);
            }
            if(GET.equals(methName)){
                return x.http().get(requestParams, BaseCallbackUtil.newInstanceBaseCallback());
            }else if(POST.equals(methName) && handler == null){
                return x.http().post(requestParams, BaseCallbackUtil.newInstanceBaseCallback());
            }else if (POST.equals(methName)&&handler !=null) {
                return x.http().post(requestParams, BaseCallbackUtil.newInstanceBaseCallback(handler));
            }
        }
        return null;
    }

    public static Object getd() {
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            return inetAddress.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return d;
    }

    //网络请求方法返回值
    public  enum MethodName{
        POSTSYSC(XUtil.POSTSYSC),GETSYSC(XUtil.GETSYSC),GET(XUtil.GET),POST(XUtil.POST);
        private String method;
        MethodName(String method) {
            this.method = method;
        }
        public String getMethod(){
            return method;
        }
    }

    //获取wifiIp地址
    public static String getWifiIP(Context context){
        //获取WifiManager
        WifiManager wifimanage=(WifiManager)context.getSystemService(Context.WIFI_SERVICE);
        //检查wifi是否开启
        if(!wifimanage.isWifiEnabled())  {
            wifimanage.setWifiEnabled(true);
        }
        WifiInfo wifiinfo= wifimanage.getConnectionInfo();
        return intToIp(wifiinfo.getIpAddress());
    }

        //将获取的int转为真正的ip地址,参考的网上的，修改了下
        private static String intToIp(int i)  {
            return (i & 0xFF)+ "." + ((i >> 8 ) & 0xFF) + "." + ((i >> 16 ) & 0xFF) +"."+((i >> 24 ) & 0xFF );

    }
}
