package com.example.lvkaixue.appmeager.utils;

import android.os.Message;

import org.xutils.common.Callback;

import java.io.File;

/**
 * Created by ll on 2016/8/12.
 */
public class BaseCallbackUtil {
    public  static BaseCallback newInstanceBaseCallback(){
        return new BaseCallback();
    }

    public  static BaseCallback newInstanceBaseCallback(ChildHandler.StaticChildHandler handler){
        return new BaseCallback(handler);
    }
    private  static class BaseCallback implements Callback.CommonCallback<String>{
        private ChildHandler.StaticChildHandler handler;
        public BaseCallback(ChildHandler.StaticChildHandler handler){
            this.handler = handler;
        }
        public BaseCallback(){}
        @Override
        public void onSuccess(String t) {
            Message message = new Message();
            message.obj = t;
            message.what = Constant.netConnSucess;
            handler.handleMessage(message);
        }
        @Override
        public void onError(Throwable throwable, boolean b) {}
        @Override
        public void onCancelled(CancelledException e) {}
        @Override
        public void onFinished() {}}

    public static abstract class DownLoadCallback implements Callback.ProgressCallback<File> {
        @Override
        public void onWaiting() {}
        @Override
        public void onStarted() {}
        @Override
        public void onLoading(long total, long current, boolean b) {onDownLoad(total, current, b);}
        @Override
        public void onSuccess(File file) {onDownLoadSuccess(file);}
        @Override
        public void onError(Throwable throwable, boolean b) {onDownLoadError();}
        @Override
        public void onCancelled(CancelledException e) {}
        @Override
        public void onFinished() {onDownLoadFinish();}

        protected  void onDownLoadFinish(){}
        protected  void onDownLoad(long total, long current, boolean b){}
        protected  void onDownLoadSuccess(File file){}
        protected  void onDownLoadError(){}
    }
}
