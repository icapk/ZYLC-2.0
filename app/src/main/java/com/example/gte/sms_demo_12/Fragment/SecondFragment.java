package com.example.gte.sms_demo_12.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gte.sms_demo_12.Adapter.list_adapter;
import com.example.gte.sms_demo_12.R;
import com.example.gte.sms_demo_12.SearchActivity;
import com.example.gte.sms_demo_12.addActivity;
import com.example.gte.sms_demo_12.mulu_list.Person;
import com.example.gte.sms_demo_12.mulu_list.left_word_style;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * Created by GTE on 2016/11/10.
 */



public class SecondFragment extends Fragment implements
        left_word_style.onWordsChangeListener, AbsListView.OnScrollListener {

    private Toolbar toolbar;
    Intent intent_search;
    Intent intent_add;
    private TextView tv;
    private left_word_style left_word;
    private Handler handler;
    private ListView listView;
    private List<Person> list;
    left_word_style word;

     public String mMachine_Num;




    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_2, null);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setTitle("目录");

        init();

        tv = (TextView) view.findViewById(R.id.tv);
        left_word =  (left_word_style)view.findViewById(R.id.left_word);

        listView = (ListView) view.findViewById(R.id.list);

        //初始化数据
        initData();
        //初始化列表
        initListView();

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

                mMachine_Num =data.getStringExtra("machine_Num_data").toString();
                String mPhone_Num =data.getStringExtra("phone_Num_data").toString();
                String mBeiZhu =data.getStringExtra("beizhu_Name_data").toString();




        }

    }

    private void initListView() {
        list_adapter adapter = new list_adapter(this, list);
        listView.setAdapter(adapter);
        listView.setOnScrollListener(this);


    }

    /**
     * 初始化联系人列表信息
     */
    private void initData() {
        list = new ArrayList<>();
        list.add(new Person(mMachine_Num));
        list.add(new Person("张晓飞"));
        list.add(new Person("杨光福"));
        list.add(new Person("阿钟"));
        list.add(new Person("胡继群"));
        list.add(new Person("徐歌阳"));
        list.add(new Person("钟泽兴"));
        list.add(new Person("宋某人"));
        list.add(new Person("刘某人"));
        list.add(new Person("Tony"));
        list.add(new Person("老刘"));
        list.add(new Person("隔壁老王"));
        list.add(new Person("安传鑫"));
        list.add(new Person("温松"));
        list.add(new Person("成龙"));
        list.add(new Person("那英"));
        list.add(new Person("刘甫"));
        list.add(new Person("沙宝亮"));
        list.add(new Person("张猛"));
        list.add(new Person("张大爷"));
        list.add(new Person("张哥"));
        list.add(new Person("张娃子"));
        list.add(new Person("樟脑丸"));
        list.add(new Person("吴亮"));
        list.add(new Person("Tom"));
        list.add(new Person("阿三"));
        list.add(new Person("肖奈"));
        list.add(new Person("贝微微"));
        list.add(new Person("赵二喜"));
        list.add(new Person("曹光"));
        list.add(new Person("姜宇航"));

        //对集合排序
        Collections.sort(list, new Comparator<Person>() {
            @Override
            public int compare(Person lhs, Person rhs) {
                //根据拼音进行排序
                return lhs.getPinyin().compareTo(rhs.getPinyin());
            }
        });
    }

    //手指按下字母改变监听回调
    @Override
    public void wordsChange(String words) {
        updateWord(words);
        updateListView(words);
    }


    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }


    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        //当滑动列表的时候，更新右侧字母列表的选中状态
        //word.setTouchIndex(list.get(firstVisibleItem).getHeaderWord());
    }

    /**
     * @param words 首字母
     */
    private void updateListView(String words) {
        for (int i = 0; i < list.size(); i++) {
            String headerWord = list.get(i).getHeaderWord();
            //将手指按下的字母与列表中相同字母开头的项找出来
            if (words.equals(headerWord)) {
                //将列表选中哪一个
                listView.setSelection(i);
                //找到开头的一个即可
                return;
            }
        }
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
        //1s后让tv隐藏
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tv.setVisibility(View.GONE);
            }
        }, 500);
    }

}
