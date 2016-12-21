package com.example.gte.sms_demo_12.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gte.sms_demo_12.R;

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

public class addActivity extends Activity {

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


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add);

        preferences = getPreferences(Activity.MODE_PRIVATE);
        editor = preferences.edit();

        add_toolbar = (Toolbar) findViewById(R.id.add_toolbar);

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

                saveData();
//                Toast.makeText(getApplicationContext(),in,Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    private void saveData(){
        try {
         DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder  builder = builderFactory.newDocumentBuilder();
         Document document = builder.parse(getResources().getAssets().open("contacts_data.xml"));
            Element content = document.getDocumentElement();

         Element mList = document.createElement("list");

         Element mName = document.createElement("name");
            mName.setTextContent(machine_num);
         Element pNumber = document.createElement("pNumber");
            pNumber.setTextContent(phone_num);
         Element mBeiZhu = document.createElement("mBeiZhu");
            mBeiZhu.setTextContent(beizhu_name);

         mList.appendChild(mName);
         mList.appendChild(pNumber);
         mList.appendChild(mBeiZhu);
        content.appendChild(mList);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}