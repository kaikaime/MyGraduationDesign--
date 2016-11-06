package com.example.lvkaixue.appmeager.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lvkaixue.appmeager.R;
import com.example.lvkaixue.appmeager.base.BaseActivity;
import com.example.lvkaixue.appmeager.bean.MeaterBean;
import com.example.lvkaixue.appmeager.utils.LoadDrawableUtils;
import com.thinkcool.circletextimageview.CircleTextImageView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_update_acitivty)
public class PersonalActivity extends BaseActivity {

    @ViewInject(R.id.user_photo)
    CircleTextImageView ci;

    @ViewInject(R.id.user_bie_et)
    EditText mETnick; //用户昵称

    @ViewInject(R.id.user_account_tv)
    TextView mETaccount;  //用户账号

    @ViewInject(R.id.user_bie_biref_et)
    EditText mETbrief;   //简介

    @ViewInject(R.id.user_bir_et)
    TextView mETbiry;//生日

    @ViewInject(R.id.user_sex_tv)
    TextView mTVsex;  //性别

    @ViewInject(R.id.user_home_tv)
    TextView mTVhome; //家乡

    @ViewInject(R.id.user_location_tv)
    TextView mTVloaction;    //所在地

    @ViewInject(R.id.user_self_tv)
    TextView mTVself; //个性标签

    @ViewInject(R.id.user_school_tv)
    TextView mTVschoolTime;  //入学年份

    @ViewInject(R.id.read_school)
    TextView mTVschools;

    @ViewInject(R.id.user_industry_tv)
    TextView mTVindustr;   //感兴趣

    @ViewInject(R.id.work_infor)
    TextView mTVwork;//工作年份

    @ViewInject(R.id.user_addinfro_tv)
    TextView mTVaddinfro;  //添加工作信息

    @ViewInject(R.id.user_register_tv)
    TextView mTVregister;//注册时间
    private MeaterBean m;

    //返回当前的用户
    public MeaterBean getM(){return m;}

    @Override
    public void initView() {
        x.view().inject(this);
        m = (MeaterBean) getIntent().getSerializableExtra("MeaterBean");
        //刷新ui界面
        refrenshUI();
    }


    private void refrenshUI() {
        if(m !=null){
            mETnick.setText(m.data.nick);//用户昵称
            mETaccount.setText(m.data.name);//用户账号
            mETbiry.setText(m.data.birth_year + "-" + m.data.birth_month + "-" + m.data.birth_day + "  ");
            mTVsex.setText(m.data.sex !=null?((m.data.sex == "1") ? "男" : "女"):"");//性别
            mETbrief.setText(m.data.introduction);//简介
            mTVhome.setText(m.data.location);//家乡
            mTVloaction.setText(m.data.location);
            mTVself.setText(m.data.tag.get(0).name);//个性标签
            mTVschools.setText(m.data.edu.get(0).year+"年 入学");//入学年份
            mTVschoolTime.setText(m.data.edu.get(0).schoolid);//学校id
            mTVaddinfro.setText(m.data.comp.get(0).company_name);//工作信息
            mTVwork.setText(m.data.comp.get(0).begin_year+"年 开始工作");//工作时间
            //加载个人头片
            LoadDrawableUtils.newInstanceDrawable(m.data.head+"/30",ci);
        }
    }

    @Event(value = {R.id.goback_iv},type = View.OnClickListener.class)
    private void click(View v){
        switch (v.getId()){
            case R.id.goback_iv:
                //结束当前的activity
                finish();
            break;
        }
    }
}
