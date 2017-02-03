package com.example.gte.sms_demo_12.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.gte.sms_demo_12.R;

import java.util.ArrayList;


/**
 * Created by GTE on 2016/11/9.
 *
 */



public class FirstFragment extends Fragment {

    private Toolbar toolbar;

    private ImageView iv_red_point;
    private ViewPager myViewPager;
    private LinearLayout ll_container;
    private ArrayList<ImageView> mImageViewList;

    //引导页图片数组
    //此处修改产品页的图片
    //方法为->
    // 先将要添加的图片修改名称为“img_4”，然后将img_4粘贴到res目录下的mipmap-hdpi下
    // 然后将 ,R.mipmap.img_4 添加到R.mipmap.img_3 后面即可
    private int[] mImageIds = new int[]{R.mipmap.img_1,
            R.mipmap.img_2,R.mipmap.img_3};

    // 小红点移动距离
    private int mPointDis;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_1, null);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setTitle("产品展示");

        iv_red_point = (ImageView)view.findViewById(R.id.iv_red_point);
        myViewPager = (ViewPager)view.findViewById(R.id.vp_f1);
        ll_container = (LinearLayout)view.findViewById(R.id.ll_container);

        initdata();
        //设置数据
        myViewPager.setAdapter(new myAdapter());


        /**
         * 由于ViewPager没有setOnclickListener
         * 利用setOnTouchListener设置ViewPager点击事件,检测ACTION_UP事件实现点击事件功能
         */
        myViewPager.setOnTouchListener(new View.OnTouchListener() {
                                           int flage = 0;

                                           @Override
                                           public boolean onTouch(View view, MotionEvent event) {
                                               switch (event.getAction()) {
                                                   case MotionEvent.ACTION_DOWN:
                                                       flage = 0;
                                                       break;
                                                   case MotionEvent.ACTION_MOVE:
                                                       flage = 1;
                                                       break;
                                                   case MotionEvent.ACTION_UP:
                                                       if (flage == 0) {
                                                           int item = myViewPager.getCurrentItem();
                                                           if (item == 0) {
                                                               Uri uri = Uri.parse("http://gtesh.com/03600043/wap/picshow.php?id=207436");
                                                               Intent it = new Intent(Intent.ACTION_VIEW, uri);
                                                               startActivity(it);
                                                           } else if (item == 1) {
                                                               Uri uri = Uri.parse("http://gtesh.com/03600043/wap/picshow.php?id=207438");
                                                               Intent it = new Intent(Intent.ACTION_VIEW, uri);
                                                               startActivity(it);
                                                           } else if (item == 2) {
                                                               Uri uri = Uri.parse("http://gtesh.com/03600043/wap/picshow.php?id=207410");
                                                               Intent it = new Intent(Intent.ACTION_VIEW, uri);
                                                               startActivity(it);

                                                           }
                                                           break;
                                                       }
                                               }
                                               return false;
                                           }
                                       });


        myViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // 当页面滑动过程中的回调
                System.out.println("当前位置:" + position + ";移动偏移百分比:"
                        + positionOffset);
                // 更新小红点距离
                int leftMargin = (int) (mPointDis * positionOffset) + position
                        * mPointDis;// 计算小红点当前的左边距
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) iv_red_point
                        .getLayoutParams();
                params.leftMargin = leftMargin; // 修改左边距

                // 重新设置布局参数
                iv_red_point.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        /**
         *   计算两个圆点的距离
         *   移动距离=第二个圆点left值 - 第一个圆点left值
         *   measure->layout(确定位置)->draw(activity的onCreate方法执行结束之后才会走此流程)
         *   mPointDis = llContainer.getChildAt(1).getLeft()
         *   - llContainer.getChildAt(0).getLeft();
         *   System.out.println("圆点距离:" + mPointDis);
         *   监听layout方法结束的事件,位置确定好之后再获取圆点间距
         *   视图树
         */
        iv_red_point.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {

                    @Override
                    public void onGlobalLayout() {
                        // 移除监听,避免重复回调
                        iv_red_point.getViewTreeObserver()
                                .removeGlobalOnLayoutListener(this);
                        // ivRedPoint.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        // layout方法执行结束的回调
                        mPointDis = ll_container.getChildAt(1).getLeft()
                                - ll_container.getChildAt(0).getLeft();
                        System.out.println("圆点距离:" + mPointDis);
                    }
                });

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