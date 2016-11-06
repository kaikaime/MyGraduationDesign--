package com.example.lvkaixue.appmeager.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;

import com.example.lvkaixue.appmeager.listeners.ListenerClass;

/**
 * Created by lvkaixue on 2016/7/31.
 */
public class HandlerUtils {
    public static Context getContext(){
        return AppBaseApplication.getmContext();
    }

    public static AppBaseApplication.MainHandler getHandler(){
        return AppBaseApplication.getmHandler();
    }
    //更新ui
    public static void mHandlerRefrenshUI(Runnable task){
        getHandler().post(task);
    }

    public static void mHandlerRunUI(Runnable tast){
        new Handler().post(tast);
    }
    //检查网络
    public static boolean checkNetWork(){
        ConnectivityManager manager = (ConnectivityManager) getContext().getSystemService(getContext().CONNECTIVITY_SERVICE);
        if(manager != null){
            NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return activeNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * handler发送消息
     */
    public static ChildHandler.StaticChildHandler getUIHandler(ListenerClass.RefrenshUIHandler handler){
        return ChildHandler.getHandler(handler);
    }
}
