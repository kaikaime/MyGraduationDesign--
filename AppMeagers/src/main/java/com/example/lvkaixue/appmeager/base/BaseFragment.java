package com.example.lvkaixue.appmeager.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.lvkaixue.appmeager.utils.HandlerUtils;

/**
 * Created by lvkaixue on 2016/7/31.
 */
public abstract class BaseFragment extends Fragment  {
    public Context mContext;
    public AppCompatActivity mMainActivity;
    private BasePager mBasePager;
    private LinearLayout linearLayout;
    private View toolBarView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context != null) {
            mContext = context;
            mMainActivity = (AppCompatActivity) context;
        }
        linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1,-1));
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //添加toolbar视图
        if((toolBarView = initToolBarView()) != null){
            linearLayout.addView(toolBarView);
        }
        //添加身体
        mBasePager = new BasePager(HandlerUtils.getContext()) {
            @Override
            public void OnRefrenshUI() {
                //如果页面加载错误点击重新加载，照样可以加载数据
                getResultState();
                initSuccessView();
                startThread();
            }

            @Override
            protected View initSuccessView() {
                return BaseFragment.this.initSuccessView();
            }
            @Override
            public ResultState getResultState() {
                return BaseFragment.this.getResultState();
            }
        };
        //开启线程事物
        mBasePager.startThread();
        linearLayout.addView(mBasePager);
        return linearLayout;
    }

    protected abstract View initToolBarView();
    public abstract BasePager.ResultState getResultState();
    public abstract View initSuccessView();

    @Override
        public void setMenuVisibility(boolean menuVisible) {
            super.setMenuVisibility(menuVisible);
            if (getView() !=null) {
                getView().setVisibility(menuVisible==true?View.VISIBLE:View.GONE);
        }
    }
}
