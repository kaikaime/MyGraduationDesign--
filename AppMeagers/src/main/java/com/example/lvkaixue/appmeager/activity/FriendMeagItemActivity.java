package com.example.lvkaixue.appmeager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lvkaixue.appmeager.R;
import com.example.lvkaixue.appmeager.adapter.FriendItemFragAdapter;
import com.example.lvkaixue.appmeager.bean.IdolBean;
import com.example.lvkaixue.appmeager.bean.MeagerMcountBean;
import com.example.lvkaixue.appmeager.listeners.ListenerClass;
import com.example.lvkaixue.appmeager.single.SingleSaveFriendItem;
import com.example.lvkaixue.appmeager.utils.Constant;
import com.example.lvkaixue.appmeager.utils.LoadDrawableUtils;
import com.example.lvkaixue.appmeager.utils.StringUtils;
import com.example.lvkaixue.appmeager.utils.ToastUtils;
import com.thinkcool.circletextimageview.CircleTextImageView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_friend_meag_item)
public class FriendMeagItemActivity extends AppCompatActivity implements ListenerClass.OnRecyItemListener<Object>{
    @ViewInject(R.id.fl_body)
    private FrameLayout mAllBody;

    @ViewInject(R.id.user_photo_item)
    private CircleTextImageView ci;

    @ViewInject(R.id.item_tv_name)
    private TextView nick;

    @ViewInject(R.id.item_tv_bir)
    private TextView bir;

    @ViewInject(R.id.tv_content)
    private TextView content;

    @ViewInject(R.id.item_tv_time)
    private TextView tvTime;

    @ViewInject(R.id.loaction)
    private TextView location;

    @ViewInject(R.id.tab)
    private TabLayout tabLayout;

    @ViewInject(R.id.vp_pagme)
    private ViewPager pager;

    @ViewInject(R.id.fl_show)
    private FrameLayout mFlButShow;

    @ViewInject(R.id.ll_show)
    private LinearLayout mLiear;

    @ViewInject(R.id.fl_show)
    private FrameLayout mFram;

    @ViewInject(R.id.btn_shadow)
    private Button mBtnShadow;

    private int mButShowHeight;
    private boolean ismButShow = false;
    private IdolBean.Data.Info info;
    private MeagerMcountBean mcountBean;
    private Animation shadow;
    private Animation transparent;
    private TranslateAnimation out;
    private TranslateAnimation in;
    private boolean isFinish = false;

    public MeagerMcountBean getMcountBean(){
        return  mcountBean;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeLoadingData();
        initView();
    }

  // @Override
    protected void beforeLoadingData() {
       // super.beforeLoadingData();
        info = (IdolBean.Data.Info) getIntent().getSerializableExtra("info");
        mcountBean = (MeagerMcountBean) getIntent().getSerializableExtra("mcountBean");
    }

   //@Override
    public void initView() {
        x.view().inject(this);
        createAnimation();//创建动画
        setRollPage();
        initViewData();
        setClickListener();
    }

    /**设置翻译界面**/
    private void setRollPage(){
        FriendItemFragAdapter friendItemFragAdapter =
                new FriendItemFragAdapter(getSupportFragmentManager(),Constant.strTitle);
        pager.setAdapter(friendItemFragAdapter);
        tabLayout.setupWithViewPager(pager);
    }
    /**创建动画**/
    private void createAnimation() {
        //测绘整个布局文件尺寸
        mAllBody.measure(0,0);
        //测绘阴影效果图尺寸
        mLiear.measure(0, 0);
        int mAllHeight = mAllBody.getMeasuredHeight();
        int mLiearHeight = mLiear.getMeasuredHeight();
        out = new TranslateAnimation(0,0,mAllHeight,mAllHeight - mLiearHeight);
        out.setDuration(500);
        out.setFillAfter(true);
        in = new TranslateAnimation(0,0,mAllHeight - mLiearHeight,mAllHeight);
        in.setDuration(500);
        in.setFillAfter(true);

        shadow = AnimationUtils.loadAnimation(this, R.anim.btn_shadow_alpha);
        transparent = AnimationUtils.loadAnimation(this, R.anim.btn_transparent_alpha);
        transparent.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                mBtnShadow.clearAnimation();
                mLiear.clearAnimation();
                mFram.setVisibility(View.GONE);
                if(isFinish){
                    finish();
                }
            }
            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
    }

    /**设置图片的点击事件**/
    private void setClickListener() {
        //结束当前的activity
        ((ImageView) findViewById(R.id.goback_iv)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mFram.getVisibility() == View.VISIBLE){
                    mBtnShadow.startAnimation(transparent);
                    mLiear.startAnimation(in);
                    isFinish = true;
                }
                if(!isFinish){
                    finish();
                }
            }
        });
        //弹出meun
        ((ImageView) findViewById(R.id.iv_meun)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ismButShow) {
                    mFram.setVisibility(View.VISIBLE);
                    mBtnShadow.startAnimation(shadow);
                    mLiear.startAnimation(out);
                } else {
                    mBtnShadow.startAnimation(transparent);
                    mLiear.startAnimation(in);
                }
                ismButShow = !ismButShow;
            }
        });

        //阴影button点击事件
        mBtnShadow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBtnShadow.startAnimation(transparent);
                mLiear.startAnimation(in);
                ismButShow = !ismButShow;
            }
        });

        //转发
        ((TextView) findViewById(R.id.zhuanfa)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FriendMeagItemActivity.this,IntransitActivity.class);
                intent.putExtra("info",info);
                startActivity(intent);
            }
        });
    }

    /**加载网络数据**/
    private void initViewData() {
        //加载页面数据
        if (info != null) {
            //设置用户图像
            if (info.head != null && !"".equals(info.head)) {
                LoadDrawableUtils.newInstanceDrawable(info.head + "/100", ci);
            } else {
                ci.setImageResource(R.mipmap.v5_bg_avatar);
            }

            //设置用户昵称
            nick.setText(info.nick);
            //个人简介
            bir.setText(info.tag != null && info.tag.size() > 0 ? info.tag.get(0).name : "");
            //微博内容
            content.setTextSize(StringUtils.getFontSize());
            content.setText(info.tweet != null && info.tweet.size() > 0 ? info.tweet.get(0).text : "");
            //设置发布时间
            tvTime.setText("发布时间: " + info.tweet != null && info.tweet.size() > 0 ? StringUtils.getDate(info.tweet.get(0).timestamp) : "");
            //设置内容发布地址
            location.setText("来自： " + info.tweet != null && info.tweet.size() > 0 ? info.tweet.get(0).from : "");
        }
    }


    /**每一个按钮点击事件**/
    public void btnOnclick(View v){
        switch (v.getId()){
            case R.id.tv_copy:
                //复制链接点击
                ToastUtils.showToast("连接已经复制!");
                break;
            case R.id.tv_weixin:
                //复制链接点击
                ToastUtils.showToast("微信好友!");
                break;
            case R.id.tv_frd:
                ToastUtils.showToast("朋友圈!");
            break;
            case R.id.tv_qq:
                ToastUtils.showToast("qq好友!");
                break;
            case R.id.tv_collect:
                if(SingleSaveFriendItem.addObject(info)){
                    ToastUtils.showToast("收藏成功!");
                }else {
                    ToastUtils.showToast("收藏失败!");
                }
                mBtnShadow.startAnimation(transparent);
                mLiear.startAnimation(in);
                ismButShow = !ismButShow;
                break;
            case R.id.tv_screen:
                ToastUtils.showToast("举报!");
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mBtnShadow.startAnimation(transparent);
        mLiear.startAnimation(in);
        ismButShow = !ismButShow;
    }


    @Override
    public void mSendRecyItem(Object o) {

    }
}
