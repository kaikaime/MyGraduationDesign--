package com.example.lvkaixue.appmeager.utils;

import android.content.Context;

import com.example.lvkaixue.appmeager.treadfact.ThreadManger;

/**
 * Created by lvkaixue on 2016/7/31.
 */
public class ThreadUtils {
    public static Context getContext(){
        return AppBaseApplication.getmContext();
    }
    public static Thread getThread(){
        return AppBaseApplication.getThread();
    }
    public static void mThreadUI(Runnable task){
        ThreadManger.newInstanceThreadPool(task);
    }


}
