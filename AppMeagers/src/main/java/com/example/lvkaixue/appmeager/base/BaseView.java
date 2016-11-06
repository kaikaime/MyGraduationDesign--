package com.example.lvkaixue.appmeager.base;

import android.content.Context;
import android.view.View;

/**
 * Created by lvkaixue on 2016/10/6.
 */
public abstract class BaseView  {
    public Context mContext;
    private BasePager basePager;
    public View mView;
    public BaseView(Context context){
        this.mContext = context;
        initPageView();
    }

    private void initPageView() {
        basePager = new BasePager(mContext) {
            @Override
            public void OnRefrenshUI() {
                refreshChFace();
            }

            @Override
            protected View initSuccessView() {
                mView = initView();
                return mView;
            }

            @Override
            public ResultState getResultState() {
                return initResultState();
            }
        };
        basePager.startThread();
    }

    public void refreshChFace() {
    }

    protected abstract BasePager.ResultState initResultState();

    protected abstract View initView();

    public BasePager getBasePagerView() {
        return basePager;
    }

}
