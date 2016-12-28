package com.example.gte.sms_demo_12.Control_Interface_Activity;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gte.sms_demo_12.R;

public class control_fixed_value_Activity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_number;
    private TextView tv_toolbar;

    private String name;
    private String pNum;
    private String mBei;
    private TextView tv_fixed_value;
    private Button btn_check_fixed_value_define;
    private Button btn_check_fixed_value;
    private SmsManager smsmanager;
    private PendingIntent Pi;
    private EditText set_num;
    private EditText set_value;
    private Button btn_set_fixed_value;
    private String value;
    private String num;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_fixed_value);
        tv_number = (TextView) findViewById(R.id.tv_fixed_value_number);
        tv_toolbar = (TextView) findViewById(R.id.tv_toolbar);
        tv_toolbar.setText("定值");

        tv_fixed_value = (TextView) findViewById(R.id.tv_fixed_value);
        set_num = (EditText) findViewById(R.id.ed_num);
        set_value = (EditText) findViewById(R.id.ed_value);
        
        btn_check_fixed_value = (Button)findViewById(R.id.btn_check_fixed_value);
        btn_check_fixed_value_define = (Button)findViewById(R.id.btn_check_fixed_value_define);
        btn_set_fixed_value = (Button)findViewById(R.id.btn_set_fixed_value);

        btn_set_fixed_value.setOnClickListener(this);
        btn_check_fixed_value.setOnClickListener(this);
        btn_check_fixed_value_define.setOnClickListener(this);

        init();
        num = set_num.getText().toString();
        value = set_value.getText().toString();

        smsmanager = SmsManager.getDefault();
        Intent sentIntent = new Intent("SEND_SMS_ACTION");
        Pi = PendingIntent.getBroadcast(control_fixed_value_Activity.this,0,sentIntent,0);
    }
    private void init() {
        Intent intent = getIntent();
        name = intent.getStringExtra("Name");
        pNum = intent.getStringExtra("pNum");
        mBei = intent.getStringExtra("mBei");
        tv_number.setText(name + "\n" + pNum + "\n" );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_check_fixed_value:
                Toast.makeText(control_fixed_value_Activity.this,"查询定值",Toast.LENGTH_LONG).show();
//                smsmanager.sendText.Message(pNum, null, "查询定值", Pi, null);
                break;
            case R.id.btn_check_fixed_value_define:
                Toast.makeText(control_fixed_value_Activity.this,"查询定值定义",Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_set_fixed_value:
                Toast.makeText(control_fixed_value_Activity.this,"设置定值#"+num+"#"+value,Toast.LENGTH_LONG).show();
//                smsmanager.sendTextMessage(pNum, null, "设置定值#"+num+"#"+value, Pi, null);
                break;
        }
        
    }
}