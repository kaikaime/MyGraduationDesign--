package com.example.lvkaixue.appmeager.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.FrameLayout;

import com.example.lvkaixue.appmeager.R;

/**
 * Created by lvkaixue on 2016/9/4.
 */
public class ImageCustomView extends FrameLayout {
    private float mPicRatio =0.9f;//图片宽高比
    private boolean flag;
    private  int phoneWidht;
    private  int phoneHeight;

    public ImageCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ImageCustomView);
        mPicRatio =  typedArray.getFloat(R.styleable.ImageCustomView_picRatio, mPicRatio);
        flag = typedArray.getBoolean(R.styleable.ImageCustomView_pWdith,false);
        typedArray.recycle();

        if(flag){
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((AppCompatActivity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            phoneWidht = displayMetrics.widthPixels;
            phoneHeight = displayMetrics.heightPixels;
            mPicRatio = phoneWidht/Float.parseFloat(phoneHeight+".0");
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int parentMode = MeasureSpec.getMode(widthMeasureSpec);
            if (parentMode == MeasureSpec.EXACTLY) {
            //得到父容器的宽度
            int parentWidth = MeasureSpec.getSize(widthMeasureSpec);

            //得到孩子的宽度
            int childWidth = parentWidth-getPaddingLeft()-getPaddingRight();

            //计算孩子的高度
            int childHeight = (int) (childWidth/mPicRatio+.5f);

            //计算父容器的高度
            int parentHeight = childHeight+getPaddingBottom()+getPaddingTop();

            int childWidhtMeasureSpec = MeasureSpec.makeMeasureSpec(childWidth,MeasureSpec.EXACTLY);
            int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(childHeight,MeasureSpec.EXACTLY);
            //测绘孩子的大小
            measureChildren(childWidhtMeasureSpec,childHeightMeasureSpec);
            setMeasuredDimension(parentWidth,parentHeight);
        }else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
