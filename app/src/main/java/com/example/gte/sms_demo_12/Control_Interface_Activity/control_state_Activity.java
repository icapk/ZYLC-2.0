package com.example.gte.sms_demo_12.Control_Interface_Activity;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gte.sms_demo_12.R;

import static android.telephony.SmsMessage.createFromPdu;

public class control_state_Activity extends Activity implements View.OnClickListener {

    private TextView tv_number;
    private TextView tv_toolbar;
    private String name;
    private String pNum;

    private TextView tv_state;
    private String Tv_State;

    private Button btn_check_state;
    private Button btn_fugui;
    private Button btn_yufen;
    private Button btn_fenzha;
    private Button btn_yuhe;
    private Button btn_hezha;

    private SmsManager smsmanager;
    private PendingIntent Pi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_state);
        tv_number = (TextView) findViewById(R.id.tv_state_number);
        tv_toolbar = (TextView) findViewById(R.id.tv_toolbar);
        tv_toolbar.setText("状态");

        tv_state = (TextView) findViewById(R.id.tv_state);

        btn_check_state = (Button) findViewById(R.id.btn_check_state);
        btn_yufen = (Button) findViewById(R.id.btn_yufen);
        btn_fenzha = (Button) findViewById(R.id.btn_fenzha);
        btn_yuhe = (Button) findViewById(R.id.btn_yuhe);
        btn_hezha = (Button) findViewById(R.id.btn_hezha);
        btn_fugui = (Button) findViewById(R.id.btn_fugui);

        btn_fugui.setOnClickListener(this);
        btn_hezha.setOnClickListener(this);
        btn_yuhe.setOnClickListener(this);
        btn_fenzha.setOnClickListener(this);
        btn_yufen.setOnClickListener(this);
        btn_check_state.setOnClickListener(this);


        init();

        smsmanager = SmsManager.getDefault();
        Intent sentIntent = new Intent("SEND_SMS_ACTION");
        Pi = PendingIntent.getBroadcast(control_state_Activity.this, 0, sentIntent, 0);
    }

    private void init() {
        Intent intent = getIntent();
        name = intent.getStringExtra("Name");
        pNum = intent.getStringExtra("pNum");
        tv_number.setText(name + "\n" + pNum + "\n");
    }

    @Override
    public void onClick(View v) {
        Tv_State = tv_state.getText().toString();

        switch (v.getId()) {
            case R.id.btn_check_state:
                smsmanager.sendTextMessage(pNum, null, "查询状态", Pi, null);
                break;
            case R.id.btn_fugui:
                smsmanager.sendTextMessage(pNum, null, "复归", Pi, null);
                break;
            case R.id.btn_yufen:
                smsmanager.sendTextMessage(pNum, null, "预分", Pi, null);
                break;
            case R.id.btn_yuhe:
                smsmanager.sendTextMessage(pNum, null, "预合", Pi, null);
                break;
            case R.id.btn_hezha:
                if (Tv_State == "分位")
                    smsmanager.sendTextMessage(pNum, null, "合闸", Pi, null);
                else {

                    Toast toast = Toast.makeText(control_state_Activity.this, "请确定主机是否处于分闸状态", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
                break;
            case R.id.btn_fenzha:
                if (Tv_State == "合位")
                    smsmanager.sendTextMessage(pNum, null, "分闸", Pi, null);
                else {
                    Toast toast = Toast.makeText(control_state_Activity.this, "请确定主机是否处于合闸状态", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    break;


                }

        }

        class MessageReceiver extends BroadcastReceiver {

            @Override
            public void onReceive(Context context, Intent intent) {
                Bundle bundle = intent.getExtras();
                Object[] pdus = (Object[]) bundle.get("pdus");//提取短信信息
                SmsMessage[] messages = new SmsMessage[pdus.length];
                for (int i = 0; i < messages.length; i++) {
                    messages[i] = createFromPdu((byte[]) pdus[i]);
                }
                String address = messages[0].getOriginatingAddress();
                String fullMessage = "";
                for (SmsMessage message : messages) {
                    fullMessage += message.getMessageBody();//获取短信内容
                }
                abortBroadcast();
                tv_state.setText(fullMessage);
            }


        }
    }
}

