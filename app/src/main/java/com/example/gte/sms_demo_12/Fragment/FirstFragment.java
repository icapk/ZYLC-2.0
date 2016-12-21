package com.example.gte.sms_demo_12.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gte.sms_demo_12.R;
import com.example.gte.sms_demo_12.Activity.SearchActivity;
import com.example.gte.sms_demo_12.Activity.addActivity;
import com.example.gte.sms_demo_12.mulu_list.Person;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import static android.app.Activity.RESULT_OK;


/**
 * Created by GTE on 2016/11/9.
 *
 */



public class FirstFragment extends Fragment {

    private Toolbar toolbar;
    private Intent intent_search;
    private Intent intent_add;
    private TextView receive_data;
    private List<Person> list;
    private String Name;
    private static final String TAG = "Nametest";

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_1, null);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setTitle("记录");

        receive_data = (TextView) view.findViewById(R.id.receive_data);






        toolbar.setNavigationIcon(R.mipmap.icon_add);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent_add = new Intent(getActivity(),addActivity.class);
                startActivity(intent_add);}
        });

        //设置右上角的填充菜单
        toolbar.inflateMenu(R.menu.main_menu);
        //设置menu点击事件
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                //判断被点击的item
               if (item.getItemId() == R.id.menu_toolbar_add) {

                        intent_search = new Intent(getActivity(),SearchActivity.class);
                        startActivity(intent_search);

                }
                return true;
            }
        });

        return view;
    }

public void init(){
    try {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder =  builderFactory.newDocumentBuilder();
        Document document = builder.parse(getResources().getAssets().open("data.xml"));
        Element content = document.getDocumentElement();

        Element mList = document.createElement("list");

        Element mName = document.createElement("name");
        mName.setTextContent("machine_num");
        Element pNumber = document.createElement("pNumber");
        pNumber.setTextContent("phone_num");
        Element mBeiZhu = document.createElement("mBeiZhu");
        mBeiZhu.setTextContent("beizhu_name");

        mList.appendChild(mName);
        mList.appendChild(pNumber);
        mList.appendChild(mBeiZhu);

        content.appendChild(mList);


        TransformerFactory transformerFactory =  TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty("encoding","utf-8");
        StringWriter sw = new StringWriter();
        transformer.transform(new DOMSource(document),new StreamResult(sw));

        receive_data.setText(sw.toString());

    } catch (ParserConfigurationException e) {
        e.printStackTrace();
    } catch (TransformerConfigurationException e) {
        e.printStackTrace();
    } catch (TransformerException e) {
        e.printStackTrace();
    } catch (SAXException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }



}

    @Override
    public void onResume() {
        super.onResume();
        init();
    }

    @Override
    public void onStart() {
        super.onStart();
        init();
    }

    //用于接收Intent返回的结果
public void onActivityResult(int requestCode, int resultCode, Intent data) {

            switch (requestCode) {

            case 1:

                if (resultCode == RESULT_OK) {

                    String machine_Num = data.getStringExtra("machine_Num_data");
                    receive_data.setText(machine_Num);

                }

                break;

            default:

        }
}


}