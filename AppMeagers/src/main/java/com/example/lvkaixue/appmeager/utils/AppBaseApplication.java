package com.example.lvkaixue.appmeager.utils;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.example.lvkaixue.appmeager.listeners.ListenerClass;

import org.xutils.x;

/**
 * Created by lvkaixue on 2016/7/31.
 */
public class AppBaseApplication extends Application  {
    private static Context mContext;
    private static MainHandler mHandler;
    private static Looper mLooper;
    private static Thread mThread;
    private static ListenerClass.OnAdujstFontListener onAdujstFontListener;
    private static int fontSize []=new int[1];//文本字体大小
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        mLooper = Looper.myLooper();
        mContext = getApplicationContext();
        mHandler = new MainHandler();
        mThread = new Thread();
        fontSize[0]=Constant.manFontSize;
    }

    public static void setFontSize(int Size){
        fontSize[0] = Size;
    }
    public static int getFontSize(){
        return fontSize[0];
    }
    public static Context getmContext(){
        return  mContext;
    }
    public static MainHandler getmHandler(){
        return mHandler;
    }
    public static Looper getmLooper(){
        return mLooper;
    }

    public static Thread getThread() {
        return mThread;
    }


    public class MainHandler extends Handler{
        ListenerClass.HandlerFace mInterfaces ;
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(mInterfaces!=null){
                mInterfaces.sendInfor(msg);
            }
        }
        public void setmInterfaces( ListenerClass.HandlerFace mInterfaces){
            this.mInterfaces = mInterfaces;
        }
    }

}
