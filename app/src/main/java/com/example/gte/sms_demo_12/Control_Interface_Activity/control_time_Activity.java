package com.example.gte.sms_demo_12.Control_Interface_Activity;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gte.sms_demo_12.R;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.app.Activity.RESULT_OK;
import static android.telephony.SmsMessage.createFromPdu;


public class control_time_Activity extends Activity implements View.OnClickListener{




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
        tv_number = (TextView) findViewById(R.id.tv_time_number);
        tv_time = (TextView) findViewById(R.id.tv_time);
        btn_check_time = (Button)findViewById(R.id.btn_check_time);
        btn_send_time = (Button)findViewById(R.id.btn_send_time);

        btn_check_time.setOnClickListener(this);
        btn_send_time.setOnClickListener(this);

        tv_toolbar = (TextView) findViewById(R.id.tv_toolbar);
        tv_toolbar.setText("时间");

        smsmanager = SmsManager.getDefault();
        Intent sentIntent = new Intent("SEND_SMS_ACTION");
        Pi = PendingIntent.getBroadcast(control_time_Activity.this,0,sentIntent,0);

        init();

    }

    /**
     *
     * @param v
     */
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_check_time:
                smsmanager.sendTextMessage(pNum, null,"查询时间" , Pi, null);
                break;
            case R.id.btn_send_time:

                SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd/HH:mm:ss ");

                Date curDate = new Date(System.currentTimeMillis());//获取当前时间

                String str = formatter.format(curDate);

//                tv_time.setText(str);
                //设置时间
                smsmanager.sendTextMessage(pNum, null,"设置时间#"+str, Pi, null);
                Toast.makeText(control_time_Activity.this,"发送当前时间："+str,Toast.LENGTH_SHORT).show();
                break;
        }
    }


    private void init() {
        Intent intent = getIntent();
        name = intent.getStringExtra("Name");
        pNum = intent.getStringExtra("pNum");
//        mBei = intent.getStringExtra("mBei");
        tv_number.setText(name + "\n" + pNum + "\n" );
    }




    class SendStatusReceiver extends BroadcastReceiver
    {


        public void onReceive(Context context, Intent intent) {
            if (getResultCode() == RESULT_OK)
            {
                //短信发送成功
                Toast.makeText(control_time_Activity.this,"短信发送成功，请等待...",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(control_time_Activity.this,"短信发送失败，请重新发送",Toast.LENGTH_SHORT).show();
            }
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
            tv_time.setText(fullMessage);
        }
    }
}