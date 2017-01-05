package com.example.gte.sms_demo_12.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gte.sms_demo_12.R;
import com.example.gte.sms_demo_12.mulu_list.Person;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
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

    private ViewPager myViewPager;
    private LinearLayout ll_container;
    private ArrayList<ImageView> mImageViewList;

    //引导页图片数组
    private int[] mImageIds = new int[]{R.mipmap.img_1,
            R.mipmap.img_2,R.mipmap.img_3,R.mipmap.logo};


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_1, null);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setTitle("产品展示");

        myViewPager = (ViewPager)view.findViewById(R.id.vp_f1);
        ll_container = (LinearLayout)view.findViewById(R.id.ll_container);

        initdata();
        //设置数据
        myViewPager.setAdapter(new myAdapter());

        return view;
    }

    /**
     * 初始化数据
     */
    private void initdata(){

        mImageViewList = new ArrayList<ImageView>();
        for (int i = 0;i<mImageIds.length;i++){
            ImageView view = new ImageView(getContext());
            //通过设置背景可以让图片填充布局
            view.setBackgroundResource(mImageIds[i]);
            mImageViewList.add(view);

            //初始化小圆点
            ImageView point = new ImageView(getContext());
            //设置灰色小圆点
            point.setImageResource(R.drawable.shape_point_gray);
            //将小圆点添加进入布局容器
            ll_container.addView(point);
        }
    }
    class myAdapter extends PagerAdapter{

        /**
         * @return 返回item的个数
         */
        public int getCount() {
            return mImageViewList.size();
        }

        /**
         * 初始化item布局
         * @param container
         * @param position
         * @return
         */
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView view  = mImageViewList.get(position);
            container.addView(view);
            return view;
        }

        /**
         *  判断object是不是view对象，view == object即可
         */
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        /**
         * 销毁item
         * @param container
         * @param position
         * @param object
         */
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

}