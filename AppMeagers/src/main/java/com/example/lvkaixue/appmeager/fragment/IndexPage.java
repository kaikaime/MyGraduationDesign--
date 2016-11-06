package com.example.lvkaixue.appmeager.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.baoyz.actionsheet.ActionSheet;
import com.example.lvkaixue.appmeager.R;
import com.example.lvkaixue.appmeager.activity.MainActivity;
import com.example.lvkaixue.appmeager.activity.WriterInforActivity;
import com.example.lvkaixue.appmeager.adapter.IndexRecyAdapter;
import com.example.lvkaixue.appmeager.base.BaseFragment;
import com.example.lvkaixue.appmeager.base.BasePager;
import com.example.lvkaixue.appmeager.bean.IdolBean;
import com.example.lvkaixue.appmeager.bean.MeagerMcountBean;
import com.example.lvkaixue.appmeager.bean.MeaterBean;
import com.example.lvkaixue.appmeager.customview.RecycleViewDivider;
import com.example.lvkaixue.appmeager.listeners.ListenerClass;
import com.example.lvkaixue.appmeager.protocol.childprotcol.IdolDataProtocol;
import com.example.lvkaixue.appmeager.protocol.childprotcol.SelfsDataProtocol;
import com.example.lvkaixue.appmeager.utils.AppBaseApplication;
import com.example.lvkaixue.appmeager.utils.HandlerUtils;
import com.example.lvkaixue.appmeager.utils.ThreadUtils;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvkaixue on 2016/8/1.
 */
public class IndexPage extends BaseFragment implements ListenerClass.HandlerFace, ActionSheet.ActionSheetListener, SwipeRefreshLayout.OnRefreshListener {
    private View mView;

    @ViewInject(R.id.my_lists)
    private RecyclerView mRecyclerView;

    @ViewInject(R.id.fl_recomm)
    private SwipeRefreshLayout swipeRefreshLayout;

    private ActionSheet.Builder builder;
    //实名
    private final static int REAL_NAME = 0;
    //匿名
    private final static int ANONYMOUS_NAME = 1;

    //当前页面
    private int pageCurrent = 1;

    //条目数量取值范围
    private int itemCount = 5;

    //当前页面索引
    private int pageCurrentIndex = 0;

    //是否刷新出来用户数据
    private boolean isSelfData = false;

    private List<IdolBean.Data.Info> list;

    private AppBaseApplication.MainHandler handler;

    private View toolbarView;

    private ActionSheet actionSheet;

    private IdolBean idolBean;
    private List<IdolBean.Data.Info> infoList;
    private MeagerMcountBean meagerMcountBean;
    private boolean flag = false;
    private MeaterBean meaterBean;
    private List<MeaterBean> mMeaterList;
    private IndexRecyAdapter mIndexRecyAdapter;


    @Override
    protected View initToolBarView() {
        toolbarView = View.inflate(getActivity(), R.layout.index_recommed, null);
        x.view().inject(this, toolbarView);
        return toolbarView;
    }

    private boolean ifAutoRefrensh = false;//判断是否手动刷新
    @Override
    public BasePager.ResultState getResultState() {
        //先检查网络，如果网络不存在首先提醒用户联网
        if (HandlerUtils.checkNetWork()) {
            idolBean = new IdolDataProtocol(itemCount+"",pageCurrentIndex+"").GetPost();
            if(idolBean.data!=null&&idolBean.data.info.size()>0){
                        return BasePager.ResultState.SUCCESS;
                    } else {
                        return BasePager.ResultState.EMPTY;
                    }
                }
        return BasePager.ResultState.ERROR;
    }

    @Override
    public View initSuccessView() {
        mView = View.inflate(mContext, R.layout.index_body, null);
        x.view().inject(this, mView);
        //设置适配器

        mRecyclerView.addItemDecoration(new RecycleViewDivider(mContext,LinearLayoutManager.VERTICAL,50,Color.BLACK));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        if (idolBean.data != null) {
            list = idolBean.data.info;
            //设置适配器
            setAdapter();
        }
        //设置刷新控件的背景颜色
        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(Color.YELLOW);
        //设置刷新进度的动画
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.BLACK);
        //单位Px   向下滑动多少单位  出现这个刷新
        swipeRefreshLayout.setDistanceToTriggerSync(50);
        swipeRefreshLayout.setOnRefreshListener(this);

        //主线程中handler
        handler = HandlerUtils.getHandler();
        handler.setmInterfaces(IndexPage.this);
        return mView;
    }

    private void setAdapter() {
        if(mIndexRecyAdapter == null){
            mIndexRecyAdapter = new IndexRecyAdapter(list,mContext);
            mRecyclerView.setAdapter(mIndexRecyAdapter);
        }else {
            mIndexRecyAdapter.setNotifyData(list);
        }
    }

    /**
     * actionbar控件点击事件
     *
     * @param v
     */
    @Event(value = {
            R.id.index_iv_writer
    }, type = View.OnClickListener.class)
    private void onTextClick(View v) {
        switch (v.getId()) {
            case R.id.index_iv_writer:
                builder = ActionSheet.createBuilder(mContext, ((MainActivity) mContext).getSupportFragmentManager());
                builder.setListener(this);
                builder.setCancelButtonTitle("取消");
                builder.setOtherButtonTitles("实名微博");
                builder.setCancelableOnTouchOutside(true);
                actionSheet = builder.show();
                ((MainActivity) mContext).getActionSheet(actionSheet);
                break;
        }
    }

    @Override
    public void onDismiss(ActionSheet actionSheet, boolean isCancel) {}

    @Override
    public void onOtherButtonClick(ActionSheet actionSheet, int index) {
        if (index == REAL_NAME) {
            //实名写微博数据
            Intent intent = new Intent(mContext, WriterInforActivity.class);
            startActivityForResult(intent,0);
        } else if (index == ANONYMOUS_NAME) {
        }
    }


    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        //请求网络数据
        ThreadUtils.mThreadUI(new Runnable() {
            @Override
            public void run() {
                //刷新的时候，模拟自己的数据也从网络中获取出来
                if(isSelfData){
                    MeaterBean mMeaterBean = new SelfsDataProtocol().GetPost();
                    System.out.println("meager: "+mMeaterBean.data.tweetnum);
                    if(mMeaterBean != null){
                        mMeaterList = new ArrayList<MeaterBean>();
                        mMeaterList.add(mMeaterBean);
                        mIndexRecyAdapter.setMeaterBean(mMeaterList);
                    }
                    isSelfData = false;
                }

                if("0".equals(idolBean.data.hasnext)&&idolBean.data.hasnext!=null){
                        pageCurrent++;
                        pageCurrentIndex = itemCount * (pageCurrent - 1);
                    if (getResultState().getLoadEmpty() == BasePager.LOAD_SUCCESS) {
                        Message message = handler.obtainMessage();
                        handler.sendMessage(message);
                    }
                }else {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });
    }

    @Override
    public void sendInfor(Message msg) {
        //关闭下拉刷新
        swipeRefreshLayout.setRefreshing(false);
        if (list != null && list.size() > 0) {
            infoList = new ArrayList<>(list);
        }
        if(idolBean.data!=null){
            list = idolBean.data.info;
            if (list != null && list.size() > 0) {
                if (infoList != null && infoList.size() > 0) {
                    for (IdolBean.Data.Info idolBean : infoList) {
                        list.add(idolBean);
                    }
                }
                mIndexRecyAdapter.setNotifyData(list);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == 0){
            flag = true;
            isSelfData = true;
            onRefresh();
        }
    }
}
