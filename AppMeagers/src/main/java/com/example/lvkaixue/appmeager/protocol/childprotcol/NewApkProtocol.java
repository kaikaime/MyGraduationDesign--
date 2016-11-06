package com.example.lvkaixue.appmeager.protocol.childprotcol;

import com.example.lvkaixue.appmeager.bean.NewApkBean;
import com.example.lvkaixue.appmeager.utils.StringUtils;

import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by lvkaixue on 2016/10/2.
 */
public class NewApkProtocol  {
    private final static String url="http://"+ StringUtils.getIP()+"/MeagerWebService/NewApkServlet";
    private final static String newApkToken = "newApk";
    public static NewApkBean getNewApkBean(){
        try {
            RequestParams r = new RequestParams(url);
            r.addQueryStringParameter("newApkToken", newApkToken);
            NewApkBean newApk =  x.http().getSync(r,NewApkBean.class);
            if(newApk !=null){
               return newApk;
            }else {
                return null;
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }
}
