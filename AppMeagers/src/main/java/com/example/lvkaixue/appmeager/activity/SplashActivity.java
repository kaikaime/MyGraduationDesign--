package com.example.lvkaixue.appmeager.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.example.lvkaixue.appmeager.R;
import com.example.lvkaixue.appmeager.base.BaseActivity;
import com.example.lvkaixue.appmeager.bean.NewApkBean;
import com.example.lvkaixue.appmeager.fragment.UpdateApkDialogFragment;
import com.example.lvkaixue.appmeager.protocol.childprotcol.NewApkProtocol;
import com.example.lvkaixue.appmeager.single.ActivityCollectorManger;
import com.example.lvkaixue.appmeager.single.SingleUser;
import com.example.lvkaixue.appmeager.utils.BaseCallbackUtil;
import com.example.lvkaixue.appmeager.utils.HandlerUtils;
import com.example.lvkaixue.appmeager.utils.StringUtils;
import com.example.lvkaixue.appmeager.utils.ThreadUtils;
import com.example.lvkaixue.appmeager.utils.ToastUtils;
import com.example.lvkaixue.appmeager.utils.XUtil;

import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

@ContentView(R.layout.activity_splash)
public class SplashActivity extends BaseActivity {
    @ViewInject(R.id.iv)
    private ImageView imageView;
    private AlphaAnimation animation;
    private NewApkBean newApkBean;
    private UpdateApkDialogFragment dialogFragment;
    private ProgressDialog dialog;
    private String wifiIp = "";
    private Object wifi;

    @Override
    public void initView() {
        x.view().inject(this);
        animation = new AlphaAnimation(0.1f,1.0f);
        animation.setFillAfter(true);
        animation.setDuration(3000);
        imageView.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //检查是否有更新的apk
                ThreadUtils.mThreadUI(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            //获取wifiip
                            PackageManager manager = getPackageManager();
                            PackageInfo packageInfo = manager.getPackageInfo(getPackageName(), 0);
                            int versionCode = packageInfo.versionCode;
                            String versionName = packageInfo.versionName;
                            System.out.println(" version: " + versionCode + " versionName " + versionName);
                            //version 1 versionName = 1.0
                            //检查是否有新版本
                          //  getServiceVersionCode(versionCode, versionName);
                        } catch (PackageManager.NameNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    private void getServiceVersionCode(int versionCode, String versionName) {
        if(HandlerUtils.checkNetWork()){
            newApkBean = NewApkProtocol.getNewApkBean();
            if(newApkBean != null) {
                if (-1 < versionCode && versionCode < newApkBean.versionCode) {
                    //发现新版本
                    getVersionCode(newApkBean);
                }else {
                    SingleUser singleUser = SingleUser.getSingleUser();
                    if (!"".equals(singleUser.getAccessToken()) && singleUser.getAccessToken() != null) {
                        imageView.clearAnimation();
                        //跳转到主界面
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        finish();
                    } else {
                        imageView.clearAnimation();
                        //跳转到登录界面
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                        finish();
                    }
                }
            }
        }else{
            //当用户登录成功以后使用过后如果用户没有自己退出账号，下一次进入的时候如果没有网就直接跳转到首页界面
            SingleUser singleUser = SingleUser.getSingleUser();
            if (!"".equals(singleUser.getAccessToken()) && singleUser.getAccessToken() != null) {
                imageView.clearAnimation();
                //跳转到主界面
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
        }
     }
    }

    private void getVersionCode(final NewApkBean newApk) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Bundle bundle = new Bundle();
                bundle.putString("newApkDeic", newApk.desci);
                //使用用户自定义选择
                dialogFragment = new UpdateApkDialogFragment();
                dialogFragment.setArguments(bundle);
                dialogFragment.show(getSupportFragmentManager(), "dialogFragment");
            }
        });
    }

    public void downLoadApkFinish(){
        if(newApkBean.apkUrl==null){
            return;
        }
        RequestParams r = new RequestParams(newApkBean.apkUrl);
        r.setSaveFilePath(StringUtils.apkUri());
        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setMessage("亲，努力下载中。。。");
        dialog.show();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                finish();
            }
        });
        x.http().get(r, new BaseCallbackUtil.DownLoadCallback() {

            @Override
            protected void onDownLoad(long total, long current, boolean b) {
                dialog.setMax((int) total);
                dialog.setProgress((int) current);
            }

            @Override
            protected void onDownLoadSuccess(File file) {
                dialog.dismiss();
                ToastUtils.showToast("下载完成!");
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(file)
                        , "application/vnd.android.package-archive");
                startActivity(intent);
                ActivityCollectorManger.finishAll();
                finish();
            }

            @Override
            protected void onDownLoadError() {
                ToastUtils.showToast("下载失败!");
            }

            @Override
            protected void onDownLoadFinish() {
                finish();
            }
        });
    }

}
