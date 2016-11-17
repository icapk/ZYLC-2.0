package com.example.gte.sms_demo_12;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

/**
 * Created by GTE on 2016/11/17.
 */

public class addActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.mipmap.icon_logo);
    }
}