package com.example.lvkaixue.appmeager.customview;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lvkaixue.appmeager.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lvkaixue on 2016/6/29.
 */
public class IndexListView extends ListView implements AbsListView.OnScrollListener {
    private Context mContext;
    private View mViewHead;
    private View mCustomView;
    private ImageView mRefrenPull;
    private TextView mRefrenText;
    private LinearLayout mRelTiLa;
    private RelativeLayout mHeadFram;
    private int mRefrenHeight;
    private int downY = -1;
    private int moveY;
    private int padY;
    private View searchView;
    private int firstItem;
    private RotateAnimation mRotate;
    private RotateAnimation mRotates;
    private ProgressBar mRrogre;
    private OnLoadDataFrenshUIHead mHeadListener;
    private OnLoadDataFrenshUIFooter mFooterListener;
    private boolean flag = true;
    private View mViewFooter;
    private TextView upTime;
    private LinearLayout mFooterAll;
    private FrameLayout mFramFooter;
    private TextView mFooterTitle;
    private ProgressBar mProgre;
    private int mFooterHeight;
    private boolean IS_SCOLL = false;
    private int padFlag= -mRefrenHeight;

    //往下拽的时候
    private int REFREN_PULL = 0;
    //释放刷新的时候
    private int REFREN_REFRENCE = 1;
    //正在刷新
    private int IS_REFREN = 2;
    //刷新动态
    private int STATE_REFRENCE = REFREN_PULL;


    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            System.out.println("正在刷新数据");
            if(STATE_REFRENCE == IS_REFREN){
                    STATE_REFRENCE = REFREN_PULL;
                    mRefrenText.setText("下拉松手刷新!");
                    mRefrenPull.setVisibility(VISIBLE);
                    mRefrenPull.startAnimation(mRotates);
                    mRefrenPull.clearAnimation();
                    mRrogre.setVisibility(GONE);
                    //隐藏头部
                    mHeadFram.setPadding(0, -mRefrenHeight, 0, 0);
            }
                if(IS_SCOLL){
                        //隐藏脚部
                        mFooterAll.setPadding(0, -mFooterHeight, 0, 0);
                        IS_SCOLL = false;
                }
        }
    };
    private SimpleDateFormat simpleDateFormat;

    //设置刷新时间
    private String setRefrenshTime() {
        return  simpleDateFormat.format(new Date());
    }

    public IndexListView(Context context) {
        super(context, null);
    }

    public IndexListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
        mContext = context;
        initHeadView();
        initFooterView();
        //设置默认时间
        upTime.setText("更新时间:" + setRefrenshTime());

        setOnItemClickListener(null);
    }

    /**
     * 添加脚部
     */
    private void initFooterView() {
        mViewFooter = View.inflate(mContext, R.layout.refren_footer_main, null);
        mFooterAll = (LinearLayout) mViewFooter.findViewById(R.id.footer_all);
        mFramFooter = (FrameLayout) mViewFooter.findViewById(R.id.footer_fram);
        mFooterTitle = (TextView) mViewFooter.findViewById(R.id.footer_title);
        mProgre = (ProgressBar) mViewFooter.findViewById(R.id.footer_progre);
        //测量脚部
        mFooterAll.measure(0, 0);
        //获取脚部视图的高度
        mFooterHeight = mFooterAll.getMeasuredHeight();

        //隐藏脚部
        mFooterAll.setPadding(0, -mFooterHeight, 0, 0);
        //添加到listview中
        this.addFooterView(mViewFooter);
    }

    /**
     *添加头部
     */
    private void initHeadView() {
        mViewHead = View.inflate(mContext, R.layout.refren_head_main, null);
        //更新时间
        upTime = (TextView) mViewHead.findViewById(R.id.pudate_title_time);
        mRelTiLa = (LinearLayout) mViewHead.findViewById(R.id.refren_all);
        //刷新头
        mHeadFram = (RelativeLayout) mViewHead.findViewById(R.id.refren_head);
        //刷新头镖
        mRefrenPull = (ImageView) mViewHead.findViewById(R.id.refren_pull);
        //刷新标题
        mRefrenText = (TextView) mViewHead.findViewById(R.id.refren_text);
        //刷新进度条
        mRrogre = (ProgressBar) mViewHead.findViewById(R.id.main_progre);
        this.setOnScrollListener(this);
        //测量控件大小
        mHeadFram.measure(0, 0);

        //拿到刷新头的高度
        mRefrenHeight = mHeadFram.getMeasuredHeight();

        mHeadFram.setPadding(0, -mRefrenHeight, 0, 0);
        this.addHeaderView(mViewHead);
        setRotateAnimation();
    }

    public void setCustomView(View view) {
        if (view != null) {
            mCustomView = view;
            //添加搜索view
            mRelTiLa.addView(view);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //记录按下时候的坐标
                downY = ((int) ev.getY());
                   break;
            case MotionEvent.ACTION_MOVE:
                moveY = ((int) ev.getY());
                //临界点
                if (downY == -1) {
                    downY =moveY;
                }
                if(mCustomView !=null){
                    //获取listview的纵坐标
                    int listViewLocation[] = new int[2];
                    this.getLocationOnScreen(listViewLocation);
                    int mListViewY = listViewLocation[1];

                    //获取customview的纵坐标
                    int customeViewLocation[] = new int[2];
                    mCustomView.getLocationOnScreen(customeViewLocation);
                    int mCustomViewY = customeViewLocation[1];

                    if(mCustomViewY < mListViewY){
                        return true;
                    }
                }
                //移动的距离
                padY = -mRefrenHeight+moveY-downY;
                if (padY > -mRefrenHeight && firstItem == 0 && flag)  {
                    //下拉刷新的时候
                    if (padY > 0 && STATE_REFRENCE == REFREN_PULL) {
                            STATE_REFRENCE = REFREN_REFRENCE;
                            refrenUI();
                    } else if (padY < 0 && STATE_REFRENCE == REFREN_REFRENCE) {
                        //释放刷新的时候
                        STATE_REFRENCE = REFREN_PULL;
                        refrenUI();
                    } else if(STATE_REFRENCE == IS_REFREN){
                        flag = false;
                         return  true;
                    }
                    mHeadFram.setPadding(0, padY, 0, 0);
                }
                break;
            case MotionEvent.ACTION_UP:
                //松开手的时候
                if(STATE_REFRENCE != IS_REFREN){
                        if (STATE_REFRENCE == REFREN_PULL) {
                            mHeadFram.setPadding(0, -mRefrenHeight, 0, 0);
                        } else if (STATE_REFRENCE == REFREN_REFRENCE) {
                            STATE_REFRENCE = IS_REFREN;
                            mHeadFram.setPadding(0, 0, 0, 0);
                            refrenUI();
                        }
                }
                //将游标设置为true，刷新完后不放手防止继续刷新
                flag  = true;
                break;
        }
        padFlag = padY;
        return super.onTouchEvent(ev);
    }


    private void refrenUI() {
        if (STATE_REFRENCE == REFREN_REFRENCE) {
            //释放刷新的时候
            mRefrenText.setText("松手立即刷新!");
            mRefrenPull.startAnimation(mRotate);
        } else if (STATE_REFRENCE == REFREN_PULL) {
            //下拉刷新的时候
            mRefrenText.setText("下拉松手刷新!");
            mRefrenPull.startAnimation(mRotates);
        } else if (STATE_REFRENCE == IS_REFREN) {
            //正在刷新的时候
            mRefrenText.setText("数据正在刷新!");
            mRefrenPull.clearAnimation();
            mRefrenPull.setVisibility(GONE);
            mRrogre.setVisibility(VISIBLE);
            //加载网络数据
             refrenshFinish();
        }
    }

    //加载网络数据
    private void refrenshFinish() {
        if(STATE_REFRENCE == IS_REFREN){
             //刷新UI界面
            if (mHeadListener != null) {
              if(mHeadListener.onLoadDataFrenshUIHead()){
                    mHandler.sendMessage(new Message());
                }
            }
            System.out.println("是否刷新过： "+mHeadListener.onLoadDataFrenshUIHead());
            System.out.println("开始刷新数据技术的积分技术的积分");
            //设置刷新时间
            upTime.setText("更新时间:"+setRefrenshTime());
            //模拟网络加载数据
           mHandler.sendEmptyMessageDelayed(0,3000);
        }
        if(IS_SCOLL){
            //刷新UI界面
            if (mFooterListener != null) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mFooterTitle.setText("正在加载！");
                            if (mFooterListener.onLoadDataFrenshUIFooter()) {
                                mFooterTitle.setText("加载完成！");
                                mHandler.sendEmptyMessageDelayed(0, 1000);
                            }
                        }
                    });
            }
        }
    }

    private void setRotateAnimation() {
        mRotate = new RotateAnimation(0, -180, Animation.RELATIVE_TO_SELF, 0.5f,Animation.RELATIVE_TO_SELF, 0.5f);
        mRotate.setDuration(500);
        mRotate.setFillAfter(true);
        mRotates = new RotateAnimation(-180,-360, Animation.RELATIVE_TO_SELF, 0.5f,Animation.RELATIVE_TO_SELF, 0.5f);
        mRotates.setFillAfter(true);
        mRotates.setDuration(500);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        //listview滚动时候
        //OnScrollListener.SCROLL_STATE_FLING; 飞速滚动
       // OnScrollListener.SCROLL_STATE_IDLE;滚动状态
       //  OnScrollListener.SCROLL_STATE_TOUCH_SCROLL;//触摸滚动
        if(SCROLL_STATE_FLING  ==scrollState || SCROLL_STATE_TOUCH_SCROLL==scrollState && !IS_SCOLL){
            if(this.getLastVisiblePosition() == getAdapter().getCount()-1&&getAdapter().getCount()>2){

                IS_SCOLL = true;
                //显示脚部
                mFooterAll.setPadding(0, 0, 0, 0);
            }
            refrenshFinish();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        firstItem = firstVisibleItem;
    }

    /**
     * 刷新头
     * @param mOnLoadHeadData
     */
    public void addFrenshUIHeadListener(OnLoadDataFrenshUIHead mOnLoadHeadData) {
        this.mHeadListener = mOnLoadHeadData;
    }
    public interface OnLoadDataFrenshUIHead {
        boolean onLoadDataFrenshUIHead();
    }
    /**
     * 刷新底部
     */
    public void addFrenshUIFooterListener(OnLoadDataFrenshUIFooter mFooterListener){
        this.mFooterListener = mFooterListener;
    }
    public interface OnLoadDataFrenshUIFooter{
        boolean onLoadDataFrenshUIFooter();
    }
}
