package com.example.gte.sms_demo_12.Control_Interface_Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gte.sms_demo_12.R;

public class Control_MainActivity extends AppCompatActivity implements View.OnClickListener{

    private String name;
    private String pNum;
    private String mBei;

    private TextView tv_num;
    private Button btn_time;
    private Button btn_state;
    private Button btn_fixed_value;
    private Button btn_fixed_value_define;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_main);
        btn_time = (Button) this.findViewById(R.id.btn_time);
        btn_state = (Button) this.findViewById(R.id.btn_state);
        btn_fixed_value = (Button) this.findViewById(R.id.btn_fixed_value);
        btn_fixed_value_define = (Button) this.findViewById(R.id.btn_fixed_value_define);

        btn_fixed_value_define.setOnClickListener(this);
        btn_fixed_value.setOnClickListener(this);
        btn_state.setOnClickListener(this);
        btn_time.setOnClickListener(this);
        tv_num = (TextView)findViewById(R.id.tv_num);

        init();

    }

    private void init() {
        Intent intent = getIntent();
          name = intent.getStringExtra("Name");
          pNum = intent.getStringExtra("pNum");
          mBei = intent.getStringExtra("mBei");
        tv_num.setText(name+"\n"+pNum+"\n"+mBei+"\n");


    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_time:
                Intent time = new Intent(this,control_time_Activity.class);
                time.putExtra("Name",name);
                time.putExtra("pNum",pNum);
                time.putExtra("mbei",mBei);
                startActivity(time);
                break;
            case R.id.btn_state:
                Intent state = new Intent(this,control_state_Activity.class);
                state.putExtra("Name",name);
                state.putExtra("pNum",pNum);
                state.putExtra("mbei",mBei);
                startActivity(state);
                break;
            case R.id.btn_fixed_value:
                Intent fixed_value = new Intent(this,control_fixed_value_Activity.class);
                fixed_value.putExtra("Name",name);
                fixed_value.putExtra("pNum",pNum);
                fixed_value.putExtra("mbei",mBei);
                startActivity(fixed_value);
                break;

            case R.id.btn_fixed_value_define:
//                Intent btn_fixed_value_define = new Intent(this,control_state_Activity.class);
//                btn_fixed_value_define.putExtra("Name",name);
//                btn_fixed_value_define.putExtra("pNum",pNum);
//                btn_fixed_value_define.putExtra("mbei",mBei);
//                startActivity(btn_fixed_value_define);
                Toast.makeText(this,"定值定义",Toast.LENGTH_SHORT).show();
                AlertDialog.Builder dialog = new AlertDialog.Builder(Control_MainActivity.this);
                dialog.setIcon(R.drawable.zylc);
                dialog.setCancelable(true);
                dialog.setPositiveButton("返回",new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog,int which){
                        finish();   //退出当前应用
                    }
                });
                dialog.setNegativeButton("返回",new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog ,int which){}
                });
                dialog.show();

                break;
        }
    }
}
