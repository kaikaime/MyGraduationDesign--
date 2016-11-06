package com.example.lvkaixue.appmeager.single;

import com.example.lvkaixue.appmeager.bean.IdolBean;

import java.util.ArrayList;

/**
 * 保存用户收藏的好友微博
 */
public class SingleSaveFriendItem {
    private SingleSaveFriendItem(){}
    private static SingleSaveFriendItem mSingleFriendItem = new SingleSaveFriendItem();
    private static ArrayList<IdolBean.Data.Info> mSimleArrayMap = new ArrayList<>();

    public  static ArrayList<IdolBean.Data.Info> getmSimleArrayMap(){
        return mSimleArrayMap;
    }
    public static void removeObject(String key){
        mSimleArrayMap.remove(key);
    }
    public static boolean addObject(IdolBean.Data.Info info){
        mSimleArrayMap.add(info);
        for(int i = 0;i<mSimleArrayMap.size();i++){
                if(mSimleArrayMap.get(i).tweet.get(0).id!=null){
                return true;
            }
        }
        return false;
    }
}
