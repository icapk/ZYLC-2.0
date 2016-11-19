package com.example.gte.sms_demo_12;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by GTE on 2016/11/17.
 */

public class addActivity extends Activity {

    Toolbar toolbar;
    private Button add_finish;
    private EditText add_machine_num;
    private EditText add_phone_num;
    private EditText add_beizhu_name;


    private String machine_num;
    private String phone_num;
    private String beizhu_name;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        init();

    }

    private void init() {

        toolbar.setNavigationIcon(R.mipmap.icon_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    });

        add_machine_num = (EditText) findViewById(R.id.add_Machine_Num);
        add_phone_num = (EditText) findViewById(R.id.add_Phone_Num);
        add_beizhu_name = (EditText) findViewById(R.id.add_beizhu_Name);
        machine_num = add_machine_num.getText().toString();
        phone_num = add_phone_num.getText().toString();
        beizhu_name =add_beizhu_name.getText().toString();


        //实现完成按钮的点击事件，并返回数据给上一级的fragment_2
        add_finish = (Button) findViewById(R.id.add_finish);
        add_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("machine_Num_data",machine_num);
                intent.putExtra("phone_Num_data",phone_num);
                intent.putExtra("beizhu_Name_data",beizhu_name);

                setResult(RESULT_OK, intent);

                finish();
            }
        });
    }
}