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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gte.sms_demo_12.Adapter.list_adapter;
import com.example.gte.sms_demo_12.Control_Interface_Activity.Control_MainActivity;
import com.example.gte.sms_demo_12.R;
import com.example.gte.sms_demo_12.Activity.SearchActivity;
import com.example.gte.sms_demo_12.Activity.addActivity;
import com.example.gte.sms_demo_12.domain.Name;
import com.example.gte.sms_demo_12.mulu_list.Person;
import com.example.gte.sms_demo_12.mulu_list.left_word_style;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


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
    private List<Name> nlist;
    private String m;
    private String n;
    public String b;




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



    private void initListView() {
        list_adapter adapter = new list_adapter(this, list);

        listView.setAdapter(adapter);
        listView.setOnScrollListener(this);
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
                Toast.makeText(getActivity(),name.getName(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * 初始化联系人列表信息
     */
    private void initData() {

        list = new ArrayList<>();

        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document document = builder.parse(getResources().getAssets().open("contacts_data.xml"));
            Element element = document.getDocumentElement();
            NodeList lists = element.getElementsByTagName("list");
            for (int i = 0; i < lists.getLength(); i++) {
                Element ele = (Element) lists.item(i);

                 m= ele.getElementsByTagName("name").item(0).getTextContent().toString();
                 n = ele.getElementsByTagName("pNumber").item(0).getTextContent().toString();
                 b = ele.getElementsByTagName("mBeiZhu").item(0).getTextContent().toString();
                new  Person(m,n,b).setName(m);


                list.add(new Person(m,n,b));

            }


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


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
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
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
