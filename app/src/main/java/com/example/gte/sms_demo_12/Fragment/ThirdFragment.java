package com.example.gte.sms_demo_12.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gte.sms_demo_12.Activity.SplashActivity;
import com.example.gte.sms_demo_12.R;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by GTE on 2016/11/10.
 */
public class ThirdFragment extends Fragment{

    private Toolbar toolbar;
    private TextView tv_link;
    private ImageView logo;
    private ImageView img;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_3, null);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setTitle("关于");

        tv_link = (TextView)view.findViewById(R.id.tv_link);
        img = (ImageView)view.findViewById(R.id.imageView);

        tv_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://gtesh.com");
                Intent it = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(it);
            }
        });



        return view;
    }




}
