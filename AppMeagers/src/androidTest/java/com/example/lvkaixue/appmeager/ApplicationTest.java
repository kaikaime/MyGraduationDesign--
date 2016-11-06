package com.example.lvkaixue.appmeager;

import android.app.Application;
import android.content.ContentValues;
import android.test.ApplicationTestCase;

import com.example.lvkaixue.appmeager.bean.SaveDataBean;
import com.example.lvkaixue.appmeager.database.DBDao;
import com.example.lvkaixue.appmeager.database.DBHelper;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public void testInsert(){
        ContentValues values = new ContentValues();
        values.put(DBHelper.Colums.mContent,"你好");
        values.put(DBHelper.Colums.mContentDate, "2015");
    }

    public void testQuery(){

        DBDao dbDao = new DBDao(getContext());
        dbDao.query();
    }
}