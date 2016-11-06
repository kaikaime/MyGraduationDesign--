package com.example.lvkaixue.appmeager.base;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.lvkaixue.appmeager.R;
import com.example.lvkaixue.appmeager.treadfact.ThreadManger;
import com.example.lvkaixue.appmeager.utils.HandlerUtils;

/**
 * 开发者：吕凯雪
 */
public abstract class BasePager extends FrameLayout {
    public final static int LOAD_NOMAL = -1;//默认
    public final static int LOAD_SUCCESS = 0;//加载成功
    public final static int LOAD_ERROR = 1;//加载失败
    public final static int LOAD_EMPTY = 2;//加载为空
    public final static int LOAD_LOADING = 3;//加载中
    public int LOAD_STATE = LOAD_NOMAL;//设置默认的加载状态为正在加载中
    private final static int MATCH_PARENT = -1;
    private View loadView, errorView, emptyView;
    private View successView;
    private boolean flag = false;
    public BasePager(Context context) {
        super(context);
        this.setLayoutParams(new LayoutParams(MATCH_PARENT,MATCH_PARENT, Gravity.CENTER));
        initView(context);
    }

    private void initView(Context context) {
        loadView = View.inflate(context, R.layout.loading_main, null);
        this.addView(loadView);
        errorView = View.inflate(context, R.layout.error_main, null);
        this.addView(errorView);
        emptyView = View.inflate(context, R.layout.empty_main, null);
        this.addView(emptyView);
        loadViewState();
    }

    private void loadViewState() {
        //第一开始进入的时候设置为正在加载
        loadView.setVisibility((LOAD_STATE == LOAD_LOADING || LOAD_STATE == LOAD_NOMAL) ? VISIBLE : GONE);
        errorView.setVisibility((LOAD_STATE == LOAD_ERROR) ? VISIBLE : GONE);
        emptyView.setVisibility((LOAD_STATE == LOAD_EMPTY) ? VISIBLE : GONE);

        //点击重新加载
        if (LOAD_STATE == LOAD_ERROR) {
            Button mButton = (Button) errorView.findViewById(R.id.up_load_btn);
            mButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    //重新加载网络
                    LOAD_STATE = LOAD_LOADING;
                    flag = false;
                    loadViewState();
                    OnRefrenshUI();
                }
            });
        }
        //数据加载成功
        if (LOAD_STATE == LOAD_SUCCESS) {
            successView = initSuccessView();

            if (successView != null) {
                this.addView(successView);
            }
        }
        //将默认状态改成加载状态
        LOAD_STATE = LOAD_LOADING;
    }

    //启动线程
    public void startThread() {
        //防止在加载过程中再次被加载
        if (!flag && LOAD_STATE == LOAD_LOADING) {
            ThreadManger.newInstanceThreadPool(new ChildThread());
            flag = true;
        }
    }

    private class ChildThread implements Runnable {
        @Override
        public void run() {
            int mResultState = BasePager.this.getResultState().getLoadEmpty();
            //网络加载异常
            if (mResultState == LOAD_ERROR) {
                LOAD_STATE = LOAD_ERROR;
            } else if (mResultState == LOAD_EMPTY) {
                //加载的值是一个空的时候
                LOAD_STATE = LOAD_EMPTY;
            } else if (mResultState == LOAD_SUCCESS) {
                LOAD_STATE = LOAD_SUCCESS;
            }
            HandlerUtils.mHandlerRefrenshUI(new Runnable() {
                @Override
                public void run() {
                    //刷新界面
                    BasePager.this.loadViewState();
                    System.out.println("更新ui：" + LOAD_STATE);
                }
            });
        }
    }

    public abstract void OnRefrenshUI();

    protected abstract View initSuccessView();

    public abstract ResultState getResultState();

    public enum ResultState {
        EMPTY(LOAD_EMPTY), SUCCESS(LOAD_SUCCESS), ERROR(LOAD_ERROR);
        private int loadEmpty;

        ResultState(int loadEmpty) {
            this.loadEmpty = loadEmpty;
        }

        public int getLoadEmpty() {
            return loadEmpty;
        }
    }
}
