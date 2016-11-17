package com.example.gte.sms_demo_12.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.gte.sms_demo_12.R;


/**
 * Created by GTE on 2016/11/10.
 */



public class SecondFragment extends Fragment {

    private Toolbar toolbar;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_2, null);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setTitle("目录");

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
                switch (item.getItemId())
                {
                    case R.id.menu_toolbar_add:
                        Toast.makeText(getContext(), R.string.menu_add, Toast.LENGTH_LONG).show();
                        break;
                    case R.id.menu_toolbar_search:
                        Toast.makeText(getContext(), R.string.menu_search, Toast.LENGTH_LONG).show();

                }

                return true;
            }
        });
    }

}
