package com.example.lvkaixue.appmeager.fragment;

import android.view.View;
import android.widget.TextView;

import com.example.lvkaixue.appmeager.base.BaseFragment;
import com.example.lvkaixue.appmeager.base.BasePager;

/**
 * Created by Administrator on 2016/8/19 0019.
 */
public class AboutPage extends BaseFragment {
    @Override
    protected View initToolBarView() {
        return null;
    }

    @Override
    public BasePager.ResultState getResultState() {
        return BasePager.ResultState.SUCCESS;
    }

    @Override
    public View initSuccessView() {
        TextView textView = new TextView(mContext);
        textView.setText(this.getClass().getSimpleName());
        return textView;
    }
    /*@Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText(this.getClass().getSimpleName());
        return textView;
    }*/
}
