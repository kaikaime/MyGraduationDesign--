package com.example.lvkaixue.appmeager.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.example.lvkaixue.appmeager.R;


/**
 * Created by lvkaixue on 2016/9/2.
 */
public class SettingNetWorkFragment extends DialogFragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setCanceledOnTouchOutside(false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        view = View.inflate(getActivity(), R.layout.setting_network, null);

        (((TextView) view.findViewById(R.id.ok))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置网络
                getActivity().startActivity(new Intent(Settings.ACTION_SETTINGS));
                SettingNetWorkFragment.this.dismiss();
            }
        });

        (((TextView) view.findViewById(R.id.cancel))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingNetWorkFragment.this.dismiss();
            }
        });
        return view;
    }

}
