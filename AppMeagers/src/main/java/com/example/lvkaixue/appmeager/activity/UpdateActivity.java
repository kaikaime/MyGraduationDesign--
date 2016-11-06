package com.example.lvkaixue.appmeager.activity;

import com.example.lvkaixue.appmeager.R;
import com.example.lvkaixue.appmeager.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;

@ContentView(R.layout.activity_update)
public class UpdateActivity extends BaseActivity {


    @Override
    public void initView() {
        x.view().inject(this);
    }
}
