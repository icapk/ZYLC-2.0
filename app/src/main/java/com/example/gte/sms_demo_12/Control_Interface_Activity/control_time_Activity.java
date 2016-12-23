package com.example.gte.sms_demo_12.Control_Interface_Activity;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gte.sms_demo_12.R;


public class control_time_Activity extends AppCompatActivity implements View.OnClickListener{




    private TextView tv_number;
    private TextView tv_time;
    private TextView tv_toolbar;
    private String name;
    private String pNum;
    private String mBei;
    private Button btn_check_time;
    private Button btn_send_time;

    private SmsManager smsmanager;
    private PendingIntent Pi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_time);
        tv_number = (TextView) findViewById(R.id.tv_number);
        tv_time = (TextView) findViewById(R.id.tv_time);
        btn_check_time = (Button)findViewById(R.id.btn_check_time);
        btn_send_time = (Button)findViewById(R.id.btn_send_time);

        btn_check_time.setOnClickListener(this);

        tv_toolbar = (TextView) findViewById(R.id.tv_toolbar);
        tv_toolbar.setText("时间");



        smsmanager = SmsManager.getDefault();
        Intent sentIntent = new Intent("SEND_SMS_ACTION");
        Pi = PendingIntent.getBroadcast(control_time_Activity.this,0,sentIntent,0);

        init();

    }


    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_check_time:
                smsmanager.sendTextMessage(pNum, null,"查询时间" , Pi, null);
                break;
            case R.id.btn_send_time:

                break;
        }
    }


    private void init() {
        Intent intent = getIntent();
        name = intent.getStringExtra("Name");
        pNum = intent.getStringExtra("pNum");
        mBei = intent.getStringExtra("mBei");
        tv_number.setText(name + "\n" + pNum + "\n" + mBei + "\n");
    }


}
