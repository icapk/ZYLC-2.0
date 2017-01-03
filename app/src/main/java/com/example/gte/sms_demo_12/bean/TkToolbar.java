package com.example.gte.sms_demo_12.bean;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.TintTypedArray;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.gte.sms_demo_12.R;

/**
 * Created by GTE on 2016/11/16.
 */

public class TkToolbar extends Toolbar {

    private LayoutInflater mInflater;

    private View mView;

    private EditText tb_search;
    private TextView tb_title;
    private ImageButton tb_rightButton;
    private String tb_rightString;

    public TkToolbar(Context context) {
        this(context,null);
    }

    public TkToolbar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TkToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        
        initView();

        if (attrs != null){
            // <!--通过调用TintTypedArray.obtainStyledAttributes获取自定义toolbar的属性集合 -->
            final TintTypedArray a = TintTypedArray.obtainStyledAttributes
                    (getContext(),attrs, R.styleable.TkToolbar,defStyleAttr,0);
            // <!--设置rightButton属性-->
            final Drawable rightIcon = a.getDrawable(R.styleable.TkToolbar_rightButtonIcon);
            if (rightIcon != null)
            {
                setRightButtinIcon(rightIcon);
            }


            //回收
            a.recycle();
        }

    }

    private void initView() {

        if (mView ==null){
        //通过LayoutInflater获取Toolbar布局 读取View
            mInflater = LayoutInflater.from(getContext());
        mView = mInflater.inflate(R.layout.tb_layout,null);


        tb_title = (TextView) mView.findViewById(R.id.tb_title);
        tb_rightButton = (ImageButton) mView.findViewById(R.id.tb_rightButton);

        //加载layout布局
        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER_HORIZONTAL);
        //将toolbar布局添加到TKToolbar
        addView(mView,lp);
        }
    }

    public void setRightButtinIcon(Drawable icon)
    {
        if (tb_rightButton != null){
            tb_rightButton.setImageDrawable(icon);
            tb_rightButton.setVisibility(VISIBLE);
        }
    }


    //rightButtonIcon的监听事件
    public void setRightButtonOnClickListener(OnClickListener li){
        tb_rightButton.setOnClickListener(li);

    }

    //重写setTitle方法，设置title
    @Override
    public void setTitle( int resId) {
        setTitle(getContext().getText(resId));
    }

    @Override
    public void setTitle(CharSequence title) {
        initView();
        if (tb_title != null)
        {
            tb_title.setText(title);
            showTbTitle();
        }
    }
    //显示toolbar的search
    public void showTbSearch(){
        if (tb_search !=null){
            tb_search.setVisibility(VISIBLE);
        }
    }
    //隐藏toolbar的search
    public void hideTbSearch(){
        if (tb_search !=null){
            tb_search.setVisibility(GONE);
        }
    }
    //显示toolbar的title
    public void showTbTitle(){
        if (tb_title !=null){
            tb_title.setVisibility(VISIBLE);
        }
    }
    //隐藏toolbar的title
    public void hideTbTitle(){
        if (tb_title !=null){
            tb_title.setVisibility(GONE);
        }
    }

}
