package com.example.lvkaixue.appmeager.activity;

import android.support.design.widget.TabLayout;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lvkaixue.appmeager.R;
import com.example.lvkaixue.appmeager.base.BaseActivity;
import com.example.lvkaixue.appmeager.bean.MeaterBean;
import com.example.lvkaixue.appmeager.customview.ObserScrolView;
import com.example.lvkaixue.appmeager.fragment.view.MicroBlog;
import com.example.lvkaixue.appmeager.utils.LoadDrawableUtils;
import com.thinkcool.circletextimageview.CircleTextImageView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_my_wei_bo_item)
public class PerProItemActivity extends BaseActivity {
    @ViewInject(R.id.tab)
    TabLayout mTabLayout ;

    @ViewInject(R.id.scroll)
    private ObserScrolView obs;

    @ViewInject(R.id.head_ll)
    private LinearLayout mHead;

    @ViewInject(R.id.body_ll)
    private LinearLayout mBody;

    @ViewInject(R.id.pager)
    private FrameLayout viewPager;

    //用户图像
    @ViewInject(R.id.user_photo)
    private CircleTextImageView circleTextImageView;

    //用户名称
    @ViewInject(R.id.tv_nick)
    private TextView mUserNick;

    //关注数量
    @ViewInject(R.id.tv_about)
    private TextView mAboutCount;

    //粉丝数量
    @ViewInject(R.id.tv_fans)
    private TextView mFansCount;

    //简介
    @ViewInject(R.id.tv_biref)
    private TextView mBiref;

    private MeaterBean meaterBean;

   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeLoadingData();
        initView();
        obs.addOnObScrollChangedListener(this);
    }*/

    @Override
    protected void beforeLoadingData() {
        //获取上一个activity传送过来的数据
        meaterBean = (MeaterBean) getIntent().getSerializableExtra("MeaterBean");
    }

   @Override
    public void initView() {
        x.view().inject(this);
        showDataUI();
        setViewOnClickListener();
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.addTab(mTabLayout.newTab().setText("微博"), 0);
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.removeAllViews();
                if (tab.getPosition() == 0) {
                    viewPager.addView(new MicroBlog(PerProItemActivity.this).getBasePagerView());
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        viewPager.addView(new MicroBlog(PerProItemActivity.this).getBasePagerView());
        setSreen();
    }


    private void showDataUI() {
        if(meaterBean != null && meaterBean.data !=null){
            //设置用户图像
            LoadDrawableUtils.newInstanceDrawable(meaterBean.data.head+"/100",circleTextImageView);
            mUserNick.setText(meaterBean.data.nick);//设置用户昵称
            mAboutCount.setText("关注 "+meaterBean.data.idolnum);//设置关注数量
            mFansCount.setText("粉丝 "+meaterBean.data.fansnum);//设置粉丝数量
            mBiref.setText("简介 "+meaterBean.data.introduction);
        }
    }

    private void setViewOnClickListener() {
        //回退按钮
        ((ImageView) findViewById(R.id.goback_iv)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setSreen(){
        //设置内容容器的高度
        DisplayMetrics di = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(di);
        obs.setBodySize(di.widthPixels,di.heightPixels);
    }
}
