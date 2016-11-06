package com.example.lvkaixue.appmeager.fragment;

import android.graphics.Color;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.lvkaixue.appmeager.R;
import com.example.lvkaixue.appmeager.adapter.PhotoViewAdapter;
import com.example.lvkaixue.appmeager.base.BaseFragment;
import com.example.lvkaixue.appmeager.base.BasePager;
import com.example.lvkaixue.appmeager.bean.PhotoBean;
import com.example.lvkaixue.appmeager.listeners.ListenerClass;
import com.example.lvkaixue.appmeager.protocol.childprotcol.SelfPhotoProtocol;
import com.example.lvkaixue.appmeager.utils.ChildHandler;
import com.example.lvkaixue.appmeager.utils.HandlerUtils;
import com.example.lvkaixue.appmeager.utils.ThreadUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by lvkaixue on 2016/8/1.
 */
public class SelfPhotoPage extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,ListenerClass.RefrenshUIHandler {
    private View inforView;

    @ViewInject(R.id.swipe)
    private SwipeRefreshLayout swipeRefreshLayout;

    @ViewInject(R.id.recy_photo)
    private RecyclerView recyclerView;

    @ViewInject(R.id.album_count)
    private TextView album_count;

    private View toolabarView;
    private PhotoBean photoBean;

    private ChildHandler.StaticChildHandler staticChildHandler;
    @Override
    protected View initToolBarView() {
        toolabarView = View.inflate(mContext, R.layout.infor_page, null);
        x.view().inject(this, toolabarView);
        staticChildHandler = HandlerUtils.getUIHandler(this);
        return toolabarView;
    }


    @Override
    public BasePager.ResultState getResultState() {
        if(HandlerUtils.checkNetWork()){
            photoBean = new SelfPhotoProtocol().GetPost();
            if(photoBean != null){
                return BasePager.ResultState.SUCCESS;
            }else{
                return BasePager.ResultState.EMPTY;
            }
        }
        return BasePager.ResultState.ERROR;
    }

    @Override
    public View initSuccessView() {
        inforView = View.inflate(mContext, R.layout.infor_body, null);
        x.view().inject(this, inforView);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        if(photoBean.album!=null&&photoBean.album.size()>0){
            recyclerView.setAdapter(PhotoViewAdapter.newInstanceRecyViewApdater(mContext,photoBean));
            album_count.setText("（"+photoBean.albumnum+"）");
        }

        //设置刷新控件的背景颜色
        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(Color.YELLOW);
        //设置刷新进度的动画
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.BLACK);
        //单位Px   向下滑动多少单位  出现这个刷新
        swipeRefreshLayout.setDistanceToTriggerSync(50);
        swipeRefreshLayout.setOnRefreshListener(this);
        return inforView;
    }


    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        ThreadUtils.mThreadUI(new Runnable() {
            @Override
            public void run() {
                if(HandlerUtils.checkNetWork()){
                    if (getResultState().getLoadEmpty() == BasePager.LOAD_SUCCESS) {
                        staticChildHandler.sendMessage(new Message());
                    }
                }
            }
        });
    }

    @Override
    public void mUIHandler(Message message) {
        if(photoBean.album!=null&&photoBean.album.size()>0){
            swipeRefreshLayout.setRefreshing(false);
            recyclerView.setAdapter( PhotoViewAdapter.newInstanceRecyViewApdater(mContext,photoBean));
        }
    }
}

