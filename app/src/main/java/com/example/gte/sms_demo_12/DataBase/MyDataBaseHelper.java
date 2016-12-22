package com.example.gte.sms_demo_12.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by GTE on 2016/12/22.
 */

public class MyDataBaseHelper extends SQLiteOpenHelper {

    public static final String CREATE_CONTACTS = "create table Contact("
            +" id integer primary key autoincrement, "
            +" name text, "
            +" num text,"
            +" beizhu text)";

    private Context mContxt;

    public MyDataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContxt = context;
    }



    @Override
    public void onCreate(SQLiteDatabase db) {//数据库第一次被创建的时候调用
        db.execSQL(CREATE_CONTACTS);
        Toast.makeText(mContxt,"创建成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
