package com.example.gte.sms_demo_12.Activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gte.sms_demo_12.Control_Interface_Activity.Control_MainActivity;
import com.example.gte.sms_demo_12.DataBase.MyDataBaseHelper;
import com.example.gte.sms_demo_12.Fragment.SecondFragment;
import com.example.gte.sms_demo_12.R;
import com.example.gte.sms_demo_12.mulu_list.Person;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

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
    private TextView tv;

    private String name;
    private String num;
    private String beizhu;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add);

        add_machine_num = (EditText) findViewById(R.id.add_Machine_Num);
        add_phone_num = (EditText) findViewById(R.id.add_Phone_Num);
        add_beizhu_name = (EditText) findViewById(R.id.add_beizhu_Name);


        add_toolbar = (Toolbar) findViewById(R.id.add_toolbar);

        tv = (TextView)findViewById(R.id.add_img_top);
        dbHelper = new MyDataBaseHelper(this,"Contact.db",null,1);
        db = dbHelper.getWritableDatabase();
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


//            public void onClick(View view) {
//
//                saveData();
//                Toast.makeText(getApplicationContext(),in,Toast.LENGTH_LONG).show();
//              finish();

//                 }
//        });


    }

    public void onClick(View v) {
            switch (v.getId()){
                case R.id.add_finish:

                    machine_num = add_machine_num.getText().toString();
                    phone_num = add_phone_num.getText().toString();
                    beizhu_name = add_beizhu_name.getText().toString();

                    ContentValues values = new ContentValues();
                    //add first data
                    values.put("name",machine_num);
                    values.put("num",phone_num);
                    values.put("beizhu",beizhu_name);
                    db.insert("Contact", null, values);
                    values.clear();

                    finish();
//                    //查询表中数据
//                    Cursor cursor = db.query("Contact",null,null,null,null,null,null);
//                    if (cursor.moveToLast()){
//
//                            //遍历Cursor对象，取出数据
//                            name = cursor.getString(cursor.getColumnIndex("name"));
//                            num = cursor.getString(cursor.getColumnIndex("num"));
//                            beizhu = cursor.getString(cursor.getColumnIndex("beizhu"));
//
//                    }
//                    cursor.close();

//                    Intent intent = new Intent(this, SecondFragment.class);
//                    String Name = name;
//                    String pNum = num;
//                    String mBei = beizhu;
//                    intent.putExtra("Name",Name);
//                    intent.putExtra("pNum",pNum);
//                    intent.putExtra("mBei",mBei);
//                    startActivity(intent);


                    break;
            }

    }
}