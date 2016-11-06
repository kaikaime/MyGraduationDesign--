package com.example.lvkaixue.appmeager.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by lvkaixue on 2016/8/8.
 */
public class ToastUtils {
    public static Context getContext(){
        return AppBaseApplication.getmContext();
    }
    public static void showToast(String str){
        Toast.makeText(getContext(),str,Toast.LENGTH_SHORT).show();
    }

}
