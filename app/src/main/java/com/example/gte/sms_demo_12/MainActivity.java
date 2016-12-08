package com.example.gte.sms_demo_12;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gte.sms_demo_12.Fragment.FirstFragment;
import com.example.gte.sms_demo_12.Fragment.SecondFragment;
import com.example.gte.sms_demo_12.Fragment.ThirdFragment;
import com.example.gte.sms_demo_12.bean.Tab;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


public class MainActivity extends FragmentActivity {



    private FragmentTabHost mTabHost;
    private LayoutInflater mInflater;

    private List<Tab> mTab = new ArrayList<>(3);

    private long firstPressTime = 0;
    private Toolbar toolbar;

    public FragmentTransaction mFragmentTransaction;
    public FragmentManager fragmentManager;
    public String curFragmentTag = "secondFragment";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        initTab();
        initcontactdata();

    }

    /**
     * 初始化MainActivity下方的三个TabHost
     */

    private void initTab() {
        Tab recording = new Tab(R.string.recording, R.drawable.selecter_recording, FirstFragment.class);
        Tab list = new Tab(R.string.list, R.drawable.selecter_list, SecondFragment.class);
        Tab about = new Tab(R.string.about, R.drawable.selecter_about, ThirdFragment.class);

        mTab.add(recording);
        mTab.add(list);
        mTab.add(about);

        mInflater = LayoutInflater.from(this);
        mTabHost = (FragmentTabHost) this.findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        for (Tab tab : mTab) {
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(getString(tab.getTitle()));
            tabSpec.setIndicator(builtIndicator(tab));
            mTabHost.addTab(tabSpec, tab.getFragment(), null);
        }

        mTabHost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);//除去两个TabHost中间的分隔线
        mTabHost.setCurrentTab(1); //设置默认TabHost 第一个
    }

    private View builtIndicator(Tab tab) {
        View view = mInflater.inflate(R.layout.tab_indicator, null);
        ImageView img = (ImageView) view.findViewById(R.id.icon_tab);
        TextView text = (TextView) view.findViewById(R.id.text_indicator);

        img.setBackgroundResource(tab.getIcon());
        text.setText(tab.getTitle());

        return view;
    }

    /**
     * 初始化联系人数据
     * 更新联系人listview
     */

    public void initcontactdata(){
        try {



            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            //获取DocumentBuilder对象，用builder接收
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            //引入org.w3c.dom包中的Document,利用parse将数据源转换成Document对象
            Document document = builder.parse(getAssets().open("contact_data.xml"));
            //获取当前文档的根元素


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }



    /*
     *双击返回键退出应用
     */

    public void onBackPressed() {
        long now = System.currentTimeMillis();
        if ((now - firstPressTime) > 2000) {
            Toast.makeText(MainActivity.this, "再按一次退出", Toast.LENGTH_SHORT).show();
            firstPressTime = now;
        } else {
            finish();
            System.exit(0);
        }
    }

}
