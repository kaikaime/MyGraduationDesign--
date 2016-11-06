package com.example.lvkaixue.appmeager.base;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.baoyz.actionsheet.ActionSheet;
import com.example.lvkaixue.appmeager.activity.LoginActivity;
import com.example.lvkaixue.appmeager.activity.MainActivity;
import com.example.lvkaixue.appmeager.single.ActivityCollectorManger;
import com.example.lvkaixue.appmeager.single.SingleUser;
import com.example.lvkaixue.appmeager.utils.AppBaseApplication;
import com.example.lvkaixue.appmeager.utils.StringUtils;
import com.example.lvkaixue.appmeager.utils.ToastUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 开发账号：愚弄
 * APP ID:1105581496
 * APP KEY:JiXvj97t6VaHZFVI
 * Android 包名：com.example.lvkaixue.appmeager
 * Android 签名:fd83463b30a43843fd9c8a8de8e53542
 */
public abstract class BaseActivity extends AppCompatActivity{
    //主界面唯一的handler
    protected AppBaseApplication.MainHandler mMainHandler;
    private static final Object object = new Object();
    private boolean isLeave = true;
    private SingleUser singleUser;
    private SharedPreferences sp;
    private String packageName;
    private ActivityManager activityManager;
    private ActionSheet actionSheet;
    private List<ActivityManager.RunningAppProcessInfo> appProcesses;
    private long TIME =2*30*30*1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //保存登录时间
        sp = getSharedPreferences("config", MODE_PRIVATE);

        //检查用户是否登录
        checkUserIsLogin();

        synchronized (object){
                ActivityCollectorManger.addActivity(this);
        }

        //当视图界面加载之前获取到其他页面传送的数据
        beforeLoadingData();
        //初始化视图
        initView();
        initData();
    }

    protected void beforeLoadingData() {}

    protected  void checkUserIsLogin(){
        if(!StringUtils.sunafu(sp.getString("time",
                new SimpleDateFormat("yyyyMMdd").format(new Date())))){
            System.out.println("=======================");
            //需要重新登录
            //检测是否登录过期
            System.out.println("我的token: "+SingleUser.getSingleUser().getAccessToken());
            SingleUser.getSingleUser().setAccessToken("");
            //如果是在登录页面，就不需要在此跳转到LoginActivity
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }else {
            return;
        }
    }

    /**
     * 程序是否在前端运行,通过枚举运行的app实现。防止重复超时检测多次,保证只有一个activity进入超时检测
     *当用户按home键时，程序进入后端运行，此时会返回false，其他情况引起activity的stop函数的调用，会返回true
     * @return
     */
    public boolean isOnForeground() {
        activityManager = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        packageName = getApplicationContext().getPackageName();
        appProcesses = activityManager.getRunningAppProcesses();
        if (appProcesses == null){ return false;}
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(packageName)
                    && appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }
        return false;
    }

    //更新消息
    public void initData() {}
    public abstract void initView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        synchronized (object){
            ActivityCollectorManger.removeActivity(this);
        }
    }

    /**
     * 两次退出，每按下一次时候如果与上次时间差值小于3秒时候才能退出，大则不能退出
     */
    private long startTime = 0;
    private long endTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(this.getClass().getSimpleName().equals(MainActivity.activityName)){
                if(actionSheet != null){
                    actionSheet.dismiss();
                    actionSheet = null;
                    return false;
                }else {
                    if ((endTime = System.currentTimeMillis()) - startTime > 3000) {
                        ToastUtils.showToast("请再按一次退出应用!");
                        startTime = endTime;
                        return false;
                    } else {
                        isOnForeground();
                        finish();
                        return true;
                    }
                }
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    public void getActionSheet(ActionSheet actionSheet){
        this.actionSheet = actionSheet;
    }
}
