package com.example.lvkaixue.appmeager.single;

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvkaixue on 2016/8/8.
 */
public class ActivityCollectorManger {
    private static List<AppCompatActivity> activityList = new ArrayList<AppCompatActivity>();
    public static List<AppCompatActivity> getActivityList(){
        return activityList;
    }
    public static void addActivity(AppCompatActivity activity){
        activityList.add(activity);
    }
    public static void removeActivity(AppCompatActivity activity){
        activityList.remove(activity);
    }


    public static void finishAll(){
        for (AppCompatActivity activity: activityList){
           if (!activity.isFinishing()){
                activity.finish();
           }
        }
        activityList.clear();
        //退出整个应用程序
        android.os.Process.killProcess(android.os.Process.myTid());
    }
}