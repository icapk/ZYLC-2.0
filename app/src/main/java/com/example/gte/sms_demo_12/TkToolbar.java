package com.example.gte.sms_demo_12;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by GTE on 2016/11/16.
 */

public class TkToolbar extends Toolbar {

    private LayoutInflater mInflater;

    private View mView;

    private EditText tb_search;
    private TextView tb_title;
    private ImageButton tb_rightButton;

    public TkToolbar(Context context) {
        this(context,null);
    }

    public TkToolbar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TkToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        
        initView();

    }

    private void initView() {


        if (mView ==null){
        //读取View
            mInflater = LayoutInflater.from(getContext());
        mView = mInflater.inflate(R.layout.toolbar,null);

        tb_search = (EditText) mView.findViewById(R.id.tb_search);
        tb_title = (TextView) mView.findViewById(R.id.tb_title);
        tb_rightButton = (ImageButton) mView.findViewById(R.id.tb_rightButton);

        //加载layout布局
        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER_HORIZONTAL);

        addView(mView,lp);

        }
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
            tb_title.setVisibility(VISIBLE);
        }
    }
}
