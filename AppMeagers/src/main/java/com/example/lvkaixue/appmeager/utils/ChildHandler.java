package com.example.lvkaixue.appmeager.utils;

import android.os.Handler;
import android.os.Message;

import com.example.lvkaixue.appmeager.listeners.ListenerClass;

/**
 * Created by lvkaixue on 2016/9/2.
 */
public class ChildHandler {
    private static StaticChildHandler staticChildHandler;
    public static StaticChildHandler getHandler(ListenerClass.RefrenshUIHandler refrenshUIHandler){
        if(staticChildHandler == null){
            staticChildHandler = new StaticChildHandler(refrenshUIHandler);
        }else {
            staticChildHandler.setHandler(refrenshUIHandler);
        }
        return staticChildHandler;
    }
    public static class StaticChildHandler extends Handler{
        private ListenerClass.RefrenshUIHandler handler;
        private StaticChildHandler(ListenerClass.RefrenshUIHandler handler){
            this.handler = handler;
        }

        private void setHandler(ListenerClass.RefrenshUIHandler handler){
            this.handler = handler;
        }
        @Override
        public void handleMessage(Message msg) {
            if(msg != null && handler != null){
                handler.mUIHandler(msg);
            }else {
                super.handleMessage(msg);
            }
        }
    }
}
