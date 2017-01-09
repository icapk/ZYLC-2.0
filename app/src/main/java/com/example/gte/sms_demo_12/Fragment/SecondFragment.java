package com.example.gte.sms_demo_12.Fragment;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gte.sms_demo_12.Activity.addActivity;
import com.example.gte.sms_demo_12.Activity.changeActivity;
import com.example.gte.sms_demo_12.Adapter.list_adapter;
import com.example.gte.sms_demo_12.Control_Interface_Activity.Control_MainActivity;
import com.example.gte.sms_demo_12.DataBase.MyDataBaseHelper;
import com.example.gte.sms_demo_12.R;
import com.example.gte.sms_demo_12.domain.Name;
import com.example.gte.sms_demo_12.mulu_list.Person;
import com.example.gte.sms_demo_12.mulu_list.left_word_style;
import com.example.gte.sms_demo_12.popupList.PopupList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * Created by GTE on 2016/11/10.
 */



public class SecondFragment extends Fragment implements
        left_word_style.onWordsChangeListener, AbsListView.OnScrollListener, View.OnClickListener {

    private Toolbar toolbar;
    private Button btn_add;
    Intent intent_add;
    private TextView tv;
    private left_word_style left_word;
    private Handler handler;
    private ListView listView;
    private List<Person> list;
    private List<Name> nlist;
    private String m;
    private String n;
    public String b;

    private MyDataBaseHelper dbHelper;
    private SQLiteDatabase dx;

    private List<String> popupMenuItemList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        popupMenuItemList.add(getString(R.string.change));
        popupMenuItemList.add(getString(R.string.delete));
        popupMenuItemList.add(getString(R.string.check));
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_2, null);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setTitle("目录");

//        init();

        btn_add = (Button)view.findViewById(R.id.btn_add);
        btn_add.setOnClickListener(this);
        tv = (TextView) view.findViewById(R.id.tv);
        left_word =  (left_word_style)view.findViewById(R.id.left_word);

        listView = (ListView) view.findViewById(R.id.list);
        dbHelper = new MyDataBaseHelper(getContext(),"Contact.db",null,1);
        dx = dbHelper.getWritableDatabase();

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

//        toolbar.setNavigationIcon(R.mipmap.icon_add);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                intent_add = new Intent(getActivity(),addActivity.class);
//                startActivity(intent_add);
//            }
//        });

    }



    private void initListView() {

        final list_adapter adapter = new list_adapter(this, list);

        listView.setAdapter(adapter);
        listView.setOnScrollListener(this);

        //listview长按事件

        PopupList popupList = new PopupList();
        popupList.init(getActivity(), listView, popupMenuItemList, new PopupList.OnPopupListClickListener() {
            @Override
            public void onPopupListClick(View contextView, int contextPosition, int position) {
                Person name = list.get(contextPosition);
                switch (position) {
                    //修改
                    case 0:
                        dx.delete("Contact","name=?",new String[]{name.getName()});

                        Intent intent = new Intent(getActivity(), changeActivity.class);

                        String Name = name.getName();
                        String pNum = name.getpNumber();
                        String mBei = name.getmBeiZhu();
                        intent.putExtra("Name",Name);
                        intent.putExtra("pNum",pNum);
                        intent.putExtra("mBei",mBei);
                        startActivity(intent);

                        break;
                    //删除
                    case 1:

                        Toast.makeText(getActivity(), "删除  “"+name.getName()+"”  成功！", Toast.LENGTH_LONG).show();
                        dx.delete("Contact","name=?",new String[]{name.getName()});

//                        adapter.notifyDataSetChanged();
                        onStart();
                        break;
                    //查看
                    case 2:
                        Toast.makeText(getActivity(), "当前手机号为："+ name.getpNumber() , Toast.LENGTH_LONG).show();

                        break;
                }
//                Toast.makeText(getActivity(), contextPosition + "long"+ position, Toast.LENGTH_LONG).show();
            }
        });




        //listview点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Person name = list.get(position);
//Name name = list.get(position);

                Intent intent = new Intent(getActivity(), Control_MainActivity.class);
                String Name = name.getName();
                String pNum = name.getpNumber();
                String mBei = name.getmBeiZhu();
                intent.putExtra("Name",Name);
                intent.putExtra("pNum",pNum);
                intent.putExtra("mBei",mBei);
                startActivity(intent);

            }
        });

    }



    /**
     * 初始化联系人列表信息
     */
    private void initData() {

        list = new ArrayList<>();

        //查询表中数据
        Cursor cursor = dx.query("Contact",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                //遍历Cursor对象，取出数据
                m = cursor.getString(cursor.getColumnIndex("name"));
                n = cursor.getString(cursor.getColumnIndex("num"));
                b = cursor.getString(cursor.getColumnIndex("beizhu"));

//                new  Person(m,n,b).setName(m);


                list.add(new Person(m,n,b));
//            Toast.makeText(getActivity(),name,Toast.LENGTH_LONG).show();
            }while (cursor.moveToNext());
        }
        cursor.close();


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

    @Override
    public void onStart() {
        super.onStart();
        initData();
        initListView();

    }

    @Override
    public void onResume() {
        super.onResume();
//        initData();

    }

    @Override
    public void onStop() {
        super.onStop();
        onDestroyView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
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

    /**
     * 添加按钮点击事件
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_add:
                intent_add = new Intent(getActivity(),addActivity.class);
                startActivity(intent_add);
                break;
        }
    }
}
