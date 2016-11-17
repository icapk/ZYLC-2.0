package com.example.gte.sms_demo_12.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.gte.sms_demo_12.R;
import com.example.gte.sms_demo_12.SearchActivity;
import com.example.gte.sms_demo_12.addActivity;


/**
 * Created by GTE on 2016/11/9.
 *
 */



public class FirstFragment extends Fragment {
    private Toolbar toolbar;
    Intent intent_search;
    Intent intent_add;
    private int REQUEST_CODE;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_1, null);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setTitle("记录");

        //初始化menu菜单
        initmenu();


        return view;
    }


    public  void initmenu() {

        toolbar.inflateMenu(R.menu.main_menu);//设置右上角的填充菜单
        //设置menu点击事件
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                //判断被点击的item
                switch (item.getItemId()) {
                    case R.id.menu_toolbar_add:
                        intent_add = new Intent(getActivity(),addActivity.class);
                        startActivity(intent_add);
                        break;
                    case R.id.menu_toolbar_search:
                        /**
                         * 从fragment跳转到Activity 跟Activity跳转一样
                         * 只不过需要先利用fragment 的getActivity方法获取fragment所在的Activity
                         */
                        intent_search = new Intent();
                        intent_search.setClass(getActivity(), SearchActivity.class);

                        startActivity(intent_search);    //这里用getActivity().startActivity(intent);
                        //如果需要返回结果则用下面的方法
                        //startActivityForResult(intent,REQUEST_CODE);
                }
                return true;
            }
        });


    }
    //用于接收Intent返回的结果
private void onActivityResult(){
    getActivity().startActivityForResult(intent_add, REQUEST_CODE);
}


}