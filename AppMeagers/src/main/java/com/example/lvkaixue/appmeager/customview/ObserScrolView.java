package com.example.lvkaixue.appmeager.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.lvkaixue.appmeager.R;

/**
 * Created by lvkaixue on 2016/10/8.
 */
public class ObserScrolView extends ScrollView {
    private LinearLayout onScrollChangedListener;
    private LinearLayout mLinHead;
    private int height;
    private LinearLayout mLinBody;
    private int newTop;
    private int oldTop;
    private int parentDown;

    public ObserScrolView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(t>=mLinHead.getMeasuredHeight()){
            scrollTo(0, mLinHead.getMeasuredHeight());
        }
        postInvalidate();
        newTop = t;
        oldTop = oldt;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mLinHead = (LinearLayout) findViewById(R.id.head_ll);
        mLinBody = (LinearLayout) findViewById(R.id.body_ll);
        height = mLinHead.getLayoutParams().height;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int mHeight =  MeasureSpec.makeMeasureSpec(height,MeasureSpec.EXACTLY);
        mLinHead.measure(widthMeasureSpec,mHeight);
        measureChild(mLinHead, widthMeasureSpec, mHeight);
        setMeasuredDimension(widthMeasureSpec,heightMeasureSpec);
    }

    public void setBodySize(int width,int height){
        LinearLayout.LayoutParams ls = new LinearLayout.LayoutParams(-1,-2);
        ls.height =height+ mLinHead.getMeasuredHeight();
        ls.width =width;
        mLinBody.setLayoutParams(ls);
    }

    public interface  OnObScrollChangedListener{
        void onObScrollChanged(int l, int t, int oldl, int oldt);
    }
}
