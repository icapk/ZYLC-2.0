package com.example.gte.sms_demo_12.Control_Interface_Activity;

import android.app.Dialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gte.sms_demo_12.R;

import static android.telephony.SmsMessage.createFromPdu;

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
    private MessageReceiver messageReceiver;
    private SendStatusReceiver sendStatusReceiver;
    private IntentFilter sendFilter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_fixed_value);

        IntentFilter receiveFilter = new IntentFilter();
        receiveFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
        receiveFilter.setPriority(1000);
        messageReceiver = new MessageReceiver();
        registerReceiver(messageReceiver,receiveFilter);

        sendFilter = new IntentFilter();
        sendFilter.addAction("SEND_SMS_ACTION");
        sendStatusReceiver = new SendStatusReceiver();
        registerReceiver(sendStatusReceiver,sendFilter);

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
        num = set_num.getText().toString();
        value = set_value.getText().toString();
        switch (v.getId()) {

            case R.id.btn_check_fixed_value:
//                Toast.makeText(control_fixed_value_Activity.this,"查询定值",Toast.LENGTH_LONG).show();
                smsmanager.sendTextMessage(pNum, null, "查询定值", Pi, null);
                break;
            case R.id.btn_check_fixed_value_define:
                //初始化一个自定义的Dialog
                Dialog dialog = new MyDialog(control_fixed_value_Activity.this);
                dialog.show();
                break;
            case R.id.btn_set_fixed_value:
//                Toast.makeText(control_fixed_value_Activity.this,"设置定值#"+num+"#"+value,Toast.LENGTH_LONG).show();
                smsmanager.sendTextMessage(pNum, null, "设置定值#"+num+"#"+value, Pi, null);
                break;
        }
        
    }



    class MessageReceiver extends BroadcastReceiver
    {

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
            if(address == pNum)//判断收到的短信是否有当前联系人发出，是，则显示，否则不在此界面显示出来
            tv_fixed_value.setText(fullMessage);
        }
    }
    class SendStatusReceiver extends BroadcastReceiver
    {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (getResultCode() == RESULT_OK)
            {
                //短信发送成功
                Toast.makeText(control_fixed_value_Activity.this,"短信发送成功，请等待...",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(control_fixed_value_Activity.this,"短信发送失败，请重新发送",Toast.LENGTH_SHORT).show();
            }
        }
    }
    protected void onDestory()
    {
        super.onDestroy();
        unregisterReceiver(messageReceiver);
        unregisterReceiver(sendStatusReceiver);
    }
}