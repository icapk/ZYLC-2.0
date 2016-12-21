package com.example.gte.sms_demo_12.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.gte.sms_demo_12.R;

/**
 * Created by GTE on 2016/11/16.
 */

public class SearchActivity extends Activity {

    Toolbar toolbar;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);

        toolbar = (Toolbar) findViewById(R.id.search_toolbar);
        init();


    }

    private void init() {
        toolbar.setNavigationIcon(R.mipmap.icon_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
