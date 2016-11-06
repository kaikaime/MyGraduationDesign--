package com.example.lvkaixue.appmeager.database;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lvkaixue.appmeager.base.BaseActivity;
import com.example.lvkaixue.appmeager.bean.SaveDataBean;
import com.example.lvkaixue.appmeager.utils.AppBaseApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvkaixue on 2016/9/17.
 */
public class DBDao {
    private  DBHelper mDBHelper;

     public DBDao(Context context){
        mDBHelper = new DBHelper(context);
    }
    public void save(SaveDataBean saveDataBean)
    {
        SQLiteDatabase database =  mDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBHelper.Colums._ID,saveDataBean.getId());
        values.put(DBHelper.Colums.mContent,saveDataBean.getmConData());
        values.put(DBHelper.Colums.mContentDate,saveDataBean.getmConDate());
        database.insert(DBHelper.DB_NAME,null,values);
        database.close();
    }

    /**
     * 查询数据
     * @return
     */
    public  List<SaveDataBean> query()
    {
        List<SaveDataBean> saveDataBeanList = new ArrayList<>();
        SQLiteDatabase database =  mDBHelper.getReadableDatabase();
        Cursor cursor = database.query(DBHelper.DB_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext())
        {
            SaveDataBean saveDataBean = new SaveDataBean();
            saveDataBean.setId(cursor.getString(0));
            saveDataBean.setmConData(cursor.getString(1));
            saveDataBean.setmConDate(cursor.getString(2));
            saveDataBeanList.add(saveDataBean);
            System.out.println("我的数据:"+cursor.getString(1));
        }
        cursor.close();
        database.close();
        return saveDataBeanList;
    }
    /**
     * 删除某一条数据
     */
    public boolean delete(SaveDataBean bean){
        SQLiteDatabase database = mDBHelper.getWritableDatabase();
        int id = database.delete(DBHelper.DB_NAME, DBHelper.Colums._ID+"= ?", new String[]{bean.getId()});
        database.close();
        return (id>0?true:false);
    }

    /**
     * 修改数据
     */
    public boolean update(SaveDataBean bean){
        SQLiteDatabase database = mDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBHelper.Colums.mContent,bean.getmConData());
        values.put(DBHelper.Colums.mContentDate, bean.getmConDate());
        int update = database.update(DBHelper.DB_NAME, values, "_id=?", new String[]{bean.getId()});
        return (update>0?true:false);
    }
}
