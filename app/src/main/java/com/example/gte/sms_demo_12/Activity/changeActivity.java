package com.example.gte.sms_demo_12.Activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gte.sms_demo_12.DataBase.MyDataBaseHelper;
import com.example.gte.sms_demo_12.R;

/**
 * Created by GTE on 2016/11/17.
 */

public class changeActivity extends Activity implements View.OnClickListener {



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
    private TextView tv;

    private String name;
    private String num;
    private String beizhu;

    private ContentValues values;
    private TextView tv_change;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add);

        add_machine_num = (EditText) findViewById(R.id.add_Machine_Num);
        add_phone_num = (EditText) findViewById(R.id.add_Phone_Num);
        add_beizhu_name = (EditText) findViewById(R.id.add_beizhu_Name);


        add_toolbar = (Toolbar) findViewById(R.id.add_toolbar);

        tv = (TextView)findViewById(R.id.add_img_top);
        tv_change = (TextView)findViewById(R.id.tv_add_change);
        tv_change.setText("修改");

        dbHelper = new MyDataBaseHelper(this,"Contact.db",null,1);
        db = dbHelper.getWritableDatabase();
        init();
//        add_finish.setClickable(false);

        machine_num = add_machine_num.getText().toString();
        phone_num = add_phone_num.getText().toString();
        beizhu_name = add_beizhu_name.getText().toString();
        values = new ContentValues();
//        if (machine_num != null || phone_num != null) {
//            add_finish.setClickable(true);
//        }

        Intent intent = getIntent();
        name = intent.getStringExtra("Name");
        num = intent.getStringExtra("pNum");
        beizhu = intent.getStringExtra("mBei");

        add_machine_num.setText(name);
        add_phone_num.setText(num);
        add_beizhu_name.setText(beizhu);

        //实现完成按钮的点击事件，并返回数据给上一级的fragment_2
        add_finish = (Button) findViewById(R.id.add_finish);

        add_finish.setOnClickListener(this);



    }

    private void init() {



//        add_toolbar.setNavigationIcon(R.mipmap.icon_back);
//        add_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //add first data
//                values.put("name",machine_num);
//                values.put("num",phone_num);
//                values.put("beizhu",beizhu_name);
//                db.insert("Contact", null, values);
//                values.clear();
//                finish();
//            }
//        });

    }

    public void onClick(View v) {
            switch (v.getId()){
                case R.id.add_finish:

                //添加数据
                values.put("name", machine_num);
                values.put("num", phone_num);
                values.put("beizhu", beizhu_name);
                db.insert("Contact", null, values);
                values.clear();
                finish();

                break;
            }

    }
}