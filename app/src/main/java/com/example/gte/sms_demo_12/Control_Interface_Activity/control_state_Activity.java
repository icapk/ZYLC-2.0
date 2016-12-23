package com.example.gte.sms_demo_12.Control_Interface_Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.gte.sms_demo_12.R;

public class control_state_Activity extends AppCompatActivity {

    private TextView tv_number;
    private TextView tv_toolbar;
    private String name;
    private String pNum;
    private String mBei;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_time);
        tv_number = (TextView) findViewById(R.id.tv_number);
        tv_toolbar = (TextView) findViewById(R.id.tv_toolbar);
        tv_toolbar.setText("状态");
        init();
    }
    private void init() {
        Intent intent = getIntent();
        name = intent.getStringExtra("Name");
        pNum = intent.getStringExtra("pNum");
        mBei = intent.getStringExtra("mBei");
        tv_number.setText(name + "\n" + pNum + "\n" + mBei + "\n");
    }

}
