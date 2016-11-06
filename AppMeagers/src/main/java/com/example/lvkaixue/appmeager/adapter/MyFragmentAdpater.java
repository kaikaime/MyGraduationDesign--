package com.example.lvkaixue.appmeager.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.lvkaixue.appmeager.base.BaseFragment;
import com.example.lvkaixue.appmeager.fragment.IndexPage;
import com.example.lvkaixue.appmeager.fragment.MyPage;
import com.example.lvkaixue.appmeager.fragment.SelfPhotoPage;

/**
 * Created by lvkaixue on 2016/8/1.
 */
public class MyFragmentAdpater  extends FragmentStatePagerAdapter{
    private final static  int framgnetCount = 3;
    public MyFragmentAdpater(FragmentManager fm) {
        super(fm);
    }

    public void setFragmentRefrensh(){
        notifyDataSetChanged();
    }
    @Override
    public Fragment getItem(int position) {
        BaseFragment mBaseFragment = null;
        switch (position) {
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
        return mBaseFragment;
    }

    @Override
    public int getCount() {
        return framgnetCount;
    }
}
