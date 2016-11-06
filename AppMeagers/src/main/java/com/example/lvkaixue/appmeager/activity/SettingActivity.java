package com.example.lvkaixue.appmeager.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lvkaixue.appmeager.R;
import com.example.lvkaixue.appmeager.base.BaseActivity;
import com.example.lvkaixue.appmeager.utils.ClearDataCachManager;
import com.example.lvkaixue.appmeager.utils.ToastUtils;

public class SettingActivity extends BaseActivity {
    private ImageView gobackImg;
    private TextView mFontSize;
    private TextView mScreenClen;
    private TextView mCachClean;
    private TextView mOpinoin;
    private TextView mAbout;


    @Override
    public void initView() {
        setContentView(R.layout.activity_setting);

        gobackImg = (ImageView) findViewById(R.id.goback_iv);
        mFontSize  = (TextView) findViewById(R.id.tv_size);
        mScreenClen  = (TextView) findViewById(R.id.tv_clean);
        mCachClean  = (TextView) findViewById(R.id.tv_clean_cach);
        mOpinoin  = (TextView) findViewById(R.id.tv_opinion);
        mAbout  = (TextView) findViewById(R.id.tv_about);
        //设置监听事件
        setViewOnclickListener();
    }
    private void setViewOnclickListener() {
        //界面回退
        gobackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //调制字体大小
        mFontSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this,AdjustMentFontSizeActivity.class));
            }
        });
        //清理缓存
        mCachClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(ClearDataCachManager.clearAllCache(SettingActivity.this)){
                                ToastUtils.showToast("清除缓存成功!");
                            }else {
                                ToastUtils.showToast("清除缓存失败!");
                            }
                        }
                    });
            }
        });
    }
}
