package com.example.lvkaixue.appmeager.fragment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lvkaixue.appmeager.R;
import com.example.lvkaixue.appmeager.activity.CollectionActivity;
import com.example.lvkaixue.appmeager.activity.DraftActivity;
import com.example.lvkaixue.appmeager.activity.FansItemActivity;
import com.example.lvkaixue.appmeager.activity.PerProItemActivity;
import com.example.lvkaixue.appmeager.activity.PersonalActivity;
import com.example.lvkaixue.appmeager.activity.SettingActivity;
import com.example.lvkaixue.appmeager.base.BaseFragment;
import com.example.lvkaixue.appmeager.base.BasePager;
import com.example.lvkaixue.appmeager.bean.MeaterBean;
import com.example.lvkaixue.appmeager.protocol.childprotcol.SelfsDataProtocol;
import com.example.lvkaixue.appmeager.utils.HandlerUtils;
import com.example.lvkaixue.appmeager.utils.LoadDrawableUtils;
import com.example.lvkaixue.appmeager.utils.ThreadUtils;
import com.thinkcool.circletextimageview.CircleTextImageView;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by lvkaixue on 2016/8/1.
 */
public class MyPage extends BaseFragment {
    private View mView;
    private MeaterBean meaterBean;

    //用户度头像
    @ViewInject(R.id.user_photo)
    private CircleTextImageView mUserPhoto;

    @ViewInject(R.id.user_tv_name)
    private TextView mTextUserName;//用户名

    @ViewInject(R.id.user_iv_sex)
    private ImageView mIvSex;//用户性别

    @ViewInject(R.id.user_tv_weibo_count)
    private TextView mItemCount; //微博数量

    @ViewInject(R.id.user_tv_follow_count)
    private TextView mAbout;//关注数量

    @ViewInject(R.id.user_tv_fans_count)
    private TextView mFanSun;//粉丝数量

    @Override
    protected View initToolBarView() {
        mView = View.inflate(getActivity(), R.layout.user_data_main, null);
        x.view().inject(this, mView);
        ThreadUtils.mThreadUI(new Runnable() {
            @Override
            public void run() {
                 meaterBean = new SelfsDataProtocol().GetPost();
                if (meaterBean != null) {
                    HandlerUtils.mHandlerRefrenshUI(new Runnable() {
                        @Override
                        public void run() {
                            refrenshUI();
                        }
                    });
                }
            }
        });
        return mView;
    }

    @Override
    public BasePager.ResultState getResultState() {
        return BasePager.ResultState.SUCCESS;
    }

    @Override
    public View initSuccessView() {
        return null;
    }

    private void refrenshUI() {
        if(meaterBean.data!=null){
            //设置用户图像
            LoadDrawableUtils.newInstanceDrawable(meaterBean.data.head+"/100",mUserPhoto);
            //用户名设置
            mTextUserName.setText(meaterBean.data.nick);
            //用户性别
            mIvSex.setImageResource((meaterBean.data.sex == "1")?R.mipmap.myinfo_headerview_male:R.mipmap.myinfo_headerview_female);
            //微博数量
            mItemCount.setText(meaterBean.data.tweetnum);
            //微博关注数量
            mAbout.setText(meaterBean.data.idolnum);
            //粉丝数量
            mFanSun.setText(meaterBean.data.fansnum);
        }

    }

    @Event(value = {R.id.user_ll_exit,
            R.id.user_ll_box,
            R.id.up_my_thing,
            R.id.user_ll_weibo,
            R.id.user_ll_fans,
            R.id.user_ll_setting,
            R.id.user_ll_collection}, type = View.OnClickListener.class)
    private void click(View v) {
        switch (v.getId()) {
            case R.id.user_ll_collection:
                startActivity(new Intent(mContext,CollectionActivity.class));
                break;
            case R.id.user_ll_setting:
                startActivity(new Intent(mContext, SettingActivity.class));
            break;
            case R.id.user_ll_box:
                startActivity(new Intent(mContext, DraftActivity.class));
                ((AppCompatActivity) mContext).overridePendingTransition(R.anim.activity_in_translate, R.anim.activity_out_translate);
                break;
            case R.id.user_ll_fans:
                Intent intent  = new Intent(mContext,FansItemActivity.class);
                startActivity(intent);
                ((AppCompatActivity) mContext).overridePendingTransition(R.anim.activity_in_translate, R.anim.activity_out_translate);
                break;
            case R.id.user_ll_weibo:
                //微博资料
                Intent mWeiBoItem = new Intent(mContext,PerProItemActivity.class);
                mWeiBoItem.putExtra("MeaterBean",meaterBean);
                startActivity(mWeiBoItem);
                ((AppCompatActivity) mContext).overridePendingTransition(R.anim.activity_in_translate, R.anim.activity_out_translate);
                break;
            case R.id.user_ll_exit:
                DialogFragmentCancel dialogFragmentExit = DialogFragmentCancel.newInstance();
                dialogFragmentExit.show(getActivity().getSupportFragmentManager(), null);
                break;
            case R.id.up_my_thing:
                Intent mPersonalIntent = new Intent(getActivity(), PersonalActivity.class);
                mPersonalIntent.putExtra("MeaterBean", meaterBean);
                startActivity(mPersonalIntent);
                break;
        }
    }
}
