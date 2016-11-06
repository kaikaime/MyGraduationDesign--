package com.example.lvkaixue.appmeager.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.example.lvkaixue.appmeager.R;
import com.example.lvkaixue.appmeager.activity.LoginActivity;
import com.example.lvkaixue.appmeager.single.ActivityCollectorManger;
import com.example.lvkaixue.appmeager.single.SingleUser;

import org.xutils.view.annotation.Event;
import org.xutils.x;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogFragmentCancel extends DialogFragment {
    private static DialogFragmentCancel dialogFragmentExit;
    private View mDialogView;

    public static DialogFragmentCancel newInstance() {
        if(dialogFragmentExit == null){
            dialogFragmentExit = new DialogFragmentCancel();
        }
        return dialogFragmentExit;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(false);
        mDialogView = View.inflate(getActivity(), R.layout.fragment_dialog_fragment_exit, null);
        x.view().inject(this,mDialogView);
        return mDialogView;
    }

    @Event(value = {R.id.cancel_daliog,R.id.cancellation_tv},type = View.OnClickListener.class)
    private void click(View view){
        switch (view.getId()){
            case R.id.cancel_daliog:
                //取消对话框
                dismiss();
            break;
            case R.id.cancellation_tv:
                //退出当前账号，清空token
               SingleUser mSinglerUser =  SingleUser.getSingleUser();
                mSinglerUser.setAccessToken("");
                Intent logoutIntent = new Intent(getActivity(), LoginActivity.class);
                startActivity(logoutIntent);
                ((AppCompatActivity) getActivity()).overridePendingTransition(R.anim.activity_start_alpha, R.anim.activity_end_alpha);
                ActivityCollectorManger.finishAll();
                dismiss();
                break;
        }
    }
}
