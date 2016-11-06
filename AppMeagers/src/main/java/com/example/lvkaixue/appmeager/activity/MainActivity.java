package com.example.lvkaixue.appmeager.activity;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lvkaixue.appmeager.R;
import com.example.lvkaixue.appmeager.base.BaseActivity;
import com.example.lvkaixue.appmeager.base.BaseFragment;
import com.example.lvkaixue.appmeager.fragment.IndexPage;
import com.example.lvkaixue.appmeager.fragment.MyPage;
import com.example.lvkaixue.appmeager.fragment.SelfPhotoPage;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity implements View.OnClickListener {
    @ViewInject(R.id.main_fl_add_frag)
    private FrameLayout mFraLaFrag;

    @ViewInject(R.id.main_ll_btn)
    private LinearLayout mLinLaBtn;
    private static final TextView[] mTextArr = new TextView[3];
    private int index = 0;
    //界面类型
    private int btn = 0;
    private BaseFragment mBaseFragment;
    public final static String activityName = MainActivity.class.getSimpleName();

    @Override
    public void initView() {
        x.view().inject(this);
        //设置底部挡航
        setBtnPressed();
    }


    /**
     * 设置点击事件
     */
    private void setBtnPressed() {
        for (int i = 0; i < mTextArr.length; i++) {
            mTextArr[i] = (TextView) mLinLaBtn.getChildAt(i);
            mTextArr[i].setOnClickListener(this);
        }
        //设置第一个为默认
        setBtnTrueOrFalse(0);
        setBtnTruePage(0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_tv_index:
                index = 0;
                break;
            case R.id.main_tv_infor:
                index = 1;
                break;
            case R.id.main_tv_me:
                index = 2;
                break;
        }
        setBtnTrueOrFalse(index);
        setBtnTruePage(index);
    }

    private void setBtnTrueOrFalse(int index) {
        mTextArr[btn].setSelected(false);
        mTextArr[index].setSelected(true);
        btn = index;
    }

    private void setBtnTruePage(int index) {
        switch (index) {
            case 0:
                mBaseFragment = new IndexPage();
                break;
            case 1:
                mBaseFragment = new SelfPhotoPage();
                break;
            case 2:
                mBaseFragment = new MyPage();
                break;
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_fl_add_frag, mBaseFragment).commit();
    }
}
