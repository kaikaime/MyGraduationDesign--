package com.example.lvkaixue.appmeager.activity;

import android.graphics.Color;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.lvkaixue.appmeager.R;
import com.example.lvkaixue.appmeager.adapter.FansRecyViewAdapter;
import com.example.lvkaixue.appmeager.base.BaseActivity;
import com.example.lvkaixue.appmeager.base.BasePager;
import com.example.lvkaixue.appmeager.bean.FansBean;
import com.example.lvkaixue.appmeager.customview.DividerItemDecoration;
import com.example.lvkaixue.appmeager.listeners.ListenerClass;
import com.example.lvkaixue.appmeager.protocol.childprotcol.FansDataProtocol;
import com.example.lvkaixue.appmeager.utils.ChildHandler;
import com.example.lvkaixue.appmeager.utils.Constant;
import com.example.lvkaixue.appmeager.utils.HandlerUtils;
import com.example.lvkaixue.appmeager.utils.ThreadUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_fans_item)
public class FansItemActivity extends BaseActivity implements ListenerClass.RefrenshUIHandler,  SwipeRefreshLayout.OnRefreshListener{
    @ViewInject(R.id.fans_item_swip)
     SwipeRefreshLayout swipeRefreshLayout;

    @ViewInject(R.id.fans_item_recyl)
     RecyclerView recyclerView;

    @ViewInject(R.id.all_lrl)
     RelativeLayout lrlAll;

    private FansBean fansBean;

    private View dataView; //当前页面
    private int pageCurrent = 1;

    //条目数量取值范围
    private int itemCount = 5;

    //当前页面索引
    private int pageCurrentIndex = 0;
    private List<FansBean.Data.Info> info =new ArrayList<>();


    private BasePager basePager;
    private List<FansBean.Data.Info> infoList;
    private long startTime;
    private ChildHandler.StaticChildHandler handler;
    private FansRecyViewAdapter mFansRecy;

    @Override
    public void initView() {
        x.view().inject(this);
        handler = HandlerUtils.getUIHandler(this);
        //网络界面加载图
        netWorkLoadingView();
    }

    private void netWorkLoadingView() {
        basePager = new BasePager(this) {
            @Override
            public void OnRefrenshUI() {
                ThreadUtils.mThreadUI(new Runnable() {
                    @Override
                    public void run() {
                        //刷新界面
                      if(BasePager.LOAD_SUCCESS ==getResultState().getLoadEmpty()){
                          //发送给主线程中的handler
                          handler.sendMessage(new Message());
                      }
                    }
                });
            }

            @Override
            protected View initSuccessView() {
                //显示数据页面
                dataView = View.inflate(FansItemActivity.this, R.layout.recyl_main, null);
                x.view().inject(FansItemActivity.this, dataView);

                //设置下拉刷新头的监听
                swipeRefreshLayout.setOnRefreshListener(FansItemActivity.this);

                recyclerView.addItemDecoration(new DividerItemDecoration(FansItemActivity.this,
                        DividerItemDecoration.VERTICAL_LIST));
                recyclerView.setLayoutManager(new LinearLayoutManager(FansItemActivity.this));
                if(info!=null){
                    //设置适配器
                    setAdapters();
                }
                //设置刷新控件的背景颜色
                swipeRefreshLayout.setProgressBackgroundColorSchemeColor(Color.YELLOW);
                //设置刷新进度的动画
                swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.BLACK);
                //单位Px   向下滑动多少单位  出现这个刷新
                swipeRefreshLayout.setDistanceToTriggerSync(50);
                return dataView;
            }
            @Override
            public ResultState getResultState() {
                //检查网络是有或者是是否已经连接上了
                if(HandlerUtils.checkNetWork()){
                    //请求网络数据
                    fansBean = new FansDataProtocol(""+pageCurrentIndex,""+itemCount).GetPost();
                    if(Constant.errcode.equals(fansBean.errcode)&&fansBean.data.info !=null && fansBean.data.info.size()>0){
                        //获取上一次的数据，将上一次的变化了的数据在一次添加
                        if(mFansRecy != null){
                            infoList = new ArrayList<>(mFansRecy.getList());
                        }
                        info.clear();
                       for(FansBean.Data.Info infos:fansBean.data.info){
                            info.add(infos);
                        }
                        if(mFansRecy != null){
                            for(FansBean.Data.Info infos:infoList){
                                info.add(infos);
                            }
                        }
                        return ResultState.SUCCESS;
                    }else {
                        return ResultState.EMPTY;
                    }
                }
                return ResultState.ERROR;
            }
        };

        //开启线程事物
        basePager.startThread();
        //添加之前首先移除原有的视图
        if(lrlAll.getChildCount() != 0){
            lrlAll.removeAllViews();
        }
        //添加当前页面
        lrlAll.addView(basePager);
    }

    /**适配器**/
    private void setAdapters() {
        if(mFansRecy == null){
            mFansRecy = new FansRecyViewAdapter(info,FansItemActivity.this);
            recyclerView.setAdapter(mFansRecy);
        }else {
            mFansRecy.setNotifyData(info);
        }
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        if(fansBean.data.hasnext.equals(Constant.isPullData)){
            //记载下拉时间
            startTime = System.currentTimeMillis();
            //还可以下拉数据
            pageCurrent++;
            pageCurrentIndex = itemCount*(pageCurrent - 1);
            swipeRefreshLayout.setRefreshing(true);
            basePager.OnRefrenshUI();
        }else if(fansBean.data.hasnext.equals(Constant.PullDataEnd)){
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void mUIHandler(Message msg) {
        swipeRefreshLayout.setRefreshing(false);
        if(info!=null){
            setAdapters();
        }
    }

    @Event(value = {R.id.goback_iv},type = View.OnClickListener.class)
    private void onclick(View v){
        switch (v.getId()){
            case R.id.goback_iv:
                finish();
            break;
        }
    }
}
