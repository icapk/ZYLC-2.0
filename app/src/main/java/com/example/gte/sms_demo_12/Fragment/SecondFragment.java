package com.example.gte.sms_demo_12.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.gte.sms_demo_12.R;
import com.example.gte.sms_demo_12.SearchActivity;
import com.example.gte.sms_demo_12.addActivity;
import com.example.gte.sms_demo_12.mulu_list.left_word_style;


/**
 * Created by GTE on 2016/11/10.
 */



public class SecondFragment extends Fragment implements
        left_word_style.onWordsChangeListener{

    private Toolbar toolbar;
    Intent intent_search;
    Intent intent_add;
    private TextView tv_number ;
    private TextView tv;
    private left_word_style left_word;
    private Handler handler;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_2, null);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setTitle("目录");

        init();

        tv = (TextView) view.findViewById(R.id.tv);
        left_word =  (left_word_style)view.findViewById(R.id.left_word);

        //设置列表点击滑动监听
        handler = new Handler();
        left_word.setOnWordsChangeListener(this);

        return view;
    }

    private void init() {

        toolbar.setNavigationIcon(R.mipmap.icon_add);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent_add = new Intent(getActivity(),addActivity.class);
                startActivityForResult(intent_add,0);
            }
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
                    startActivityForResult(intent_search,1);

                }
                return true;
            }
        });

    }
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);  //这个super可不能落下，否则可能回调不了
        if(resultCode == 0){  //判断返回码是否是0
            //String mNumber =data.getStringExtra("machine_Num_data").toString();
           //tv_number.setText(mNumber);
        }

    }


    //手指按下字母改变监听回调

    public void wordsChange(String words) {
        updateWord(words);
    }

    /**
     * 更新中央的字母提示
     *
     * @param words 首字母
     */
    private void updateWord(String words) {
        tv.setText(words);
        tv.setVisibility(View.VISIBLE);
        //清空之前的所有消息
        handler.removeCallbacksAndMessages(null);
        //500ms后让tv隐藏
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tv.setVisibility(View.GONE);
            }
        }, 500);
    }

}
