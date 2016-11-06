package com.example.lvkaixue.appmeager.customview;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.lvkaixue.appmeager.R;

/**
 * Created by lvkaixue on 2016/9/9.
 */
public class DragHelperCustomView extends FrameLayout
{
    private  ViewDragHelper mDragger;
    private LinearLayout mMainContent;
    private int mContentDragRange;
    private int mContentChildDragRange;
    private LinearLayout mContentChild;
    private int oldTop;
    private int newTop;
    private int mMainContentWidht;

    public DragHelperCustomView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        mDragger = ViewDragHelper.create(this, 0.1f, callback);
    }

    ViewDragHelper.Callback callback = new ViewDragHelper.Callback()
    {
        @Override
        public boolean tryCaptureView(View child, int pointerId)
        {
            return mMainContent==child;
        }

        @Override
        public int getViewVerticalDragRange(View child) {
            return mContentDragRange;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy)
        {
            //得到移动前的top值
            oldTop = mMainContent.getTop();
            if (child == mMainContent)
            {
                if(top <= -(mContentChildDragRange+mContentChild.getPaddingTop()))
                {
                    return -(mContentChildDragRange+mContentChild.getPaddingTop());
                }
                else if(top>=0)
                {
                    return 0;
                }
            }
            return top;
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            newTop = top;
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            //主面板拖拽
            if(releasedChild == mMainContent)
            {
                //如果是向下滑动
                if(yvel>=0){
                        if(mMainContent.getTop() <=-(mContentChild.getPaddingTop()+mContentChildDragRange*0.5)){
                            close();
                        }else{
                            open();
                        }
                }else if(yvel<0){
                    if(mMainContent.getTop() >-(mContentChild.getPaddingTop()+mContentChildDragRange*0.5))
                    {
                        open();
                    }
                    else
                    {
                        close();
                    }
                }

                postInvalidate();
            }
        }

        //打开动画
        private void open()
        {
            DragHelperCustomView.this.open();
        }
        //关闭动画
        private void close()
        {
            DragHelperCustomView.this.close();
        }
    };

    @Override
    public void computeScroll()
    {
        super.computeScroll();
        if(mDragger.continueSettling(true))
        {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    private  void open()
    {
        if(mDragger.smoothSlideViewTo(mMainContent,0,0))
        {
            //如果是true则表示还没有滑到终点
            ViewCompat.postInvalidateOnAnimation(this);
        }
        else
        {
            mMainContent.layout(0,0,mMainContentWidht,mContentDragRange);
        }
    }

    protected  void close()
    {
        if(mDragger.smoothSlideViewTo(mMainContent,0,-(mContentChildDragRange+mContentChild.getPaddingTop())))
        {
            //如果是true则表示还没有滑到终点
            ViewCompat.postInvalidateOnAnimation(this);
        }
        else
        {
            mMainContent.layout(0,-(mContentChildDragRange+mContentChild.getPaddingTop()),mMainContentWidht,
                    mContentDragRange-mContentChild.getPaddingTop()-mContentChildDragRange);
        }
    }


    @Override
    protected void onFinishInflate()
    {
        super.onFinishInflate();
        mMainContent = (LinearLayout)findViewById(R.id.fra_head);
        mContentChild = (LinearLayout)findViewById(R.id.head_liner);
        mMainContent.measure(0,0);
        mContentChild.measure(0,0);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        if(mMainContent!=null)
        {
            mMainContentWidht = mMainContent.getMeasuredWidth();
            mContentDragRange =mMainContent.getMeasuredHeight();
            mContentChildDragRange =  mContentChild.getMeasuredHeight();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event)
    {
        return mDragger.shouldInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        mDragger.processTouchEvent(event);
        return true;
    }
}
