package com.example.lvkaixue.appmeager.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lvkaixue.appmeager.R;
import com.example.lvkaixue.appmeager.base.BaseActivity;
import com.example.lvkaixue.appmeager.bean.AddContentBean;
import com.example.lvkaixue.appmeager.bean.IdolBean;
import com.example.lvkaixue.appmeager.protocol.childprotcol.AddContentProtocol;
import com.example.lvkaixue.appmeager.treadfact.ThreadManger;
import com.example.lvkaixue.appmeager.utils.JsonResponseParser;
import com.example.lvkaixue.appmeager.utils.ToastUtils;

public class IntransitActivity extends BaseActivity {
    private TextView tvContent;
    private IdolBean.Data.Info info;
    private EditText etContent;
    private AddContentBean addContentBean;

    @Override
    public void initView() {
        info = (IdolBean.Data.Info) getIntent().getSerializableExtra("info");
        setContentView(R.layout.activity_intransit);
        tvContent = (TextView) findViewById(R.id.tv_content);
        etContent = (EditText) findViewById(R.id.et_content);
        etContent.setText(info.tweet.get(0).text);
        initViewListener();
    }

    private void initViewListener() {
        //取消
        ((TextView) findViewById(R.id.tv_cancel)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //发送
        ((TextView) findViewById(R.id.tv_send)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThreadManger.newInstanceThreadPool(new Runnable() {
                    @Override
                    public void run() {
                        addContentBean = new AddContentProtocol(new String[]{etContent.getText().toString(),"0"}).GetPost();
                       /* if(addContentBean!=null){
                            System.out.println("发送成功");
                            startActivity(new Intent(IntransitActivity.this, MainActivity.class));
                            finish();
                        }else {
                            System.out.println("发送失败");
                        }*/
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(addContentBean!=null){
                                    ToastUtils.showToast("发送成功!");
                                    startActivity(new Intent(IntransitActivity.this, MainActivity.class));
                                    finish();
                                }else {
                                    ToastUtils.showToast("发送成功!");
                                    finish();
                                }
                            }
                        });

                    }
                });
            }
        });
    }

}
