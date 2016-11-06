package com.example.lvkaixue.appmeager.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.lvkaixue.appmeager.base.BaseFragment;
import com.example.lvkaixue.appmeager.fragment.CommentPage;
import com.example.lvkaixue.appmeager.fragment.ForwardPage;
import com.example.lvkaixue.appmeager.fragment.ZambiaPage;
import com.example.lvkaixue.appmeager.utils.Constant;

/**
 * Created by lvkaixue on 2016/9/8.
 */
public class FriendItemFragAdapter extends FragmentStatePagerAdapter {
    private String [] strTitle;

    public FriendItemFragAdapter(FragmentManager fm,String [] strTitle) {
        super(fm);
        this.strTitle =strTitle;
    }

    @Override
    public Fragment getItem(int position) {
        BaseFragment baseFragment = null;
        switch (position){
            case 0:
                baseFragment = new ForwardPage();
            break;
            case 1:
                baseFragment = new CommentPage();
            break;
            case 2:
                baseFragment = new ZambiaPage();
            break;
        }
        return baseFragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return strTitle[position];
    }

    @Override
    public int getCount() {
        return Constant.FriendItemCount;
    }
}
