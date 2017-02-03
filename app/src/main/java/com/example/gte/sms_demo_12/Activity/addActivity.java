package com.example.gte.sms_demo_12.Activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gte.sms_demo_12.DataBase.MyDataBaseHelper;
import com.example.gte.sms_demo_12.R;

/**
 * Created by GTE on 2016/11/17.
 */

public class addActivity extends Activity implements View.OnClickListener {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    public String KEY = "num";
    private Button add_finish;
    private EditText add_machine_num;
    private EditText add_phone_num;
    private EditText add_beizhu_name;


    private String machine_num;
    private String phone_num;
    private String beizhu_name;
    private Toolbar add_toolbar;

    private MyDataBaseHelper dbHelper;
    private SQLiteDatabase db;
    private ImageView tv;

    private String name;
    private String num;
    private String beizhu;
    private TextView tv_add;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add);

        add_machine_num = (EditText) findViewById(R.id.add_Machine_Num);
        add_phone_num = (EditText) findViewById(R.id.add_Phone_Num);
        add_beizhu_name = (EditText) findViewById(R.id.add_beizhu_Name);


        add_toolbar = (Toolbar) findViewById(R.id.add_toolbar);

        tv = (ImageView)findViewById(R.id.add_img_top);
        tv_add = (TextView)findViewById(R.id.tv_add_change);
        tv_add.setText("添加");

        dbHelper = new MyDataBaseHelper(this,"Contact.db",null,1);
        db = dbHelper.getWritableDatabase();
//        getWindow().setNavigationBarColor(Color.TRANSPARENT);
//        getWindow().setStatusBarColor(Color.TRANSPARENT);
        //设置隐藏虚拟按键
//        tv.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
//        //设置隐藏状态栏
//        tv.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
//
//        tv.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
//        tv.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//        tv.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        init();


    }

    private void init() {



        add_toolbar.setNavigationIcon(R.mipmap.icon_back);
        add_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        //实现完成按钮的点击事件，并返回数据给上一级的fragment_2
        add_finish = (Button) findViewById(R.id.add_finish);

        add_finish.setOnClickListener(this);


    }

    public void onClick(View v) {
            switch (v.getId()){
                case R.id.add_finish:


                    machine_num = add_machine_num.getText().toString();
                    phone_num = add_phone_num.getText().toString();
                    beizhu_name = add_beizhu_name.getText().toString();
                    if(machine_num.length() == 0 || phone_num.length() !=11){
                        Toast.makeText(this,"请输入正确的设备号或手机号",Toast.LENGTH_SHORT).show();
                    }else {
                        ContentValues values = new ContentValues();
                        //add first data
                        values.put("name",machine_num);
                        values.put("num",phone_num);
                        values.put("beizhu",beizhu_name);
                        db.insert("Contact", null, values);
                        values.clear();
                        finish();
                    }

                    break;
            }

    }

}