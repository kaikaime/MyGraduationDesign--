package com.example.lvkaixue.appmeager.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by lvkaixue on 2016/9/13.
 */
public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "mConDB";
    private static final int VERSION = 1;
    public DBHelper(Context context) {
        super(context, DB_NAME+".db", null, VERSION);
    }

    public  class Colums implements BaseColumns {
        public static final String mContent ="content_data";
        public static final String mContentDate ="content_date";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table mConDB("+Colums._ID+" Integer primary key autoincrement,"+Colums.mContent+" text not null,"+Colums.mContentDate+" text not null)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF"+DB_NAME);
    }
}
