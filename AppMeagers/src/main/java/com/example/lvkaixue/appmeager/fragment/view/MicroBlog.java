package com.example.lvkaixue.appmeager.fragment.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chanven.lib.cptr.PtrClassicFrameLayout;
import com.example.lvkaixue.appmeager.R;
import com.example.lvkaixue.appmeager.adapter.PersProRecyItemAdapter;
import com.example.lvkaixue.appmeager.base.BasePager;
import com.example.lvkaixue.appmeager.base.BaseView;
import com.example.lvkaixue.appmeager.bean.IdolBean;
import com.example.lvkaixue.appmeager.customview.RecycleViewDivider;
import com.example.lvkaixue.appmeager.protocol.childprotcol.IdolDataProtocol;
import com.example.lvkaixue.appmeager.utils.HandlerUtils;

import java.util.List;

/**
 * Created by lvkaixue on 2016/10/6.
 */
public class MicroBlog extends BaseView{
    private PtrClassicFrameLayout mPtrFrame;
    private RecyclerView mRecy;
    private List<IdolBean.Data.Info> list;
    private IdolBean idolBean;
    private PersProRecyItemAdapter mPerProItem;

    public MicroBlog(Context context) {
        super(context);
    }

    @Override
    protected BasePager.ResultState initResultState() {
        //先检查网络，如果网络不存在首先提醒用户联网
        if (HandlerUtils.checkNetWork()) {
            idolBean = new IdolDataProtocol("30","0").GetPost();
            if(idolBean !=null){
                return BasePager.ResultState.SUCCESS;
            }else {
                return BasePager.ResultState.EMPTY;
            }
        }
        return BasePager.ResultState.ERROR;
    }

    @Override
    protected View initView() {
        mView = View.inflate(mContext, R.layout.micro_blog_view,null);
        mRecy = (RecyclerView) mView.findViewById(R.id.mb_rcl);
       setRecylearViewAction();
        return mView;
    }


    private void setRecylearViewAction() {
        mRecy.addItemDecoration(new RecycleViewDivider(mContext, LinearLayoutManager.VERTICAL, 50, Color.BLACK));
        mRecy.setLayoutManager(new LinearLayoutManager(mContext));
        setRecyAdapter();
    }

    private void setRecyAdapter(){
        if(mPerProItem == null && idolBean.data != null){
            mPerProItem = new PersProRecyItemAdapter(idolBean.data.info,mContext);
            mRecy.setAdapter(mPerProItem);
        }else if(idolBean.data != null){
            mPerProItem.notifyDataSetChanged(idolBean.data.info);
        }
    }
}
