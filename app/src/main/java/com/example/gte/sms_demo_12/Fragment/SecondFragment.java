package com.example.gte.sms_demo_12.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.gte.sms_demo_12.R;
import com.example.gte.sms_demo_12.SearchActivity;
import com.example.gte.sms_demo_12.addActivity;


/**
 * Created by GTE on 2016/11/10.
 */



public class SecondFragment extends Fragment {

    private Toolbar toolbar;
    Intent intent_search;
    Intent intent_add;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_2, null);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setTitle("目录");


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


}
