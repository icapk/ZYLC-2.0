package com.example.gte.sms_demo_12.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.example.gte.sms_demo_12.R;



public class SplashActivity extends Activity {

    RelativeLayout rl_splash;

    protected void onCreate(Bundle savedInstanceState) {


        setContentView(R.layout.activity_splash);

        rl_splash = (RelativeLayout) findViewById(R.id.rl_splash);
        startAnim();

    }
    //开启动画
    private void startAnim()
    {
        //动画集合
        AnimationSet set = new AnimationSet(false);

        //旋转动画
        RotateAnimation rotato = new RotateAnimation(0,360, Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0.5f);
        rotato.setDuration(5000);//动画时间
        rotato.setFillAfter(true);//保持动画
        //缩放动画
        ScaleAnimation scale = new ScaleAnimation(0,1,0,1,Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0.5f);
        scale.setDuration(5000);//动画时间
        scale.setFillAfter(true);//保持动画
        //渐变动画
        AlphaAnimation alpha = new AlphaAnimation(0,1);
        alpha.setDuration(5000);//动画时间
        alpha.setFillAfter(true);//保持动画

        set.addAnimation(rotato);
        set.addAnimation(scale);
        set.addAnimation(alpha);

        //设置动画监听
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }
            //动画执行结束
            public void onAnimationEnd(Animation animation) {
                jumpNextPager();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        rl_splash.startAnimation(set);
    }

    /*
    * 跳转下一个页面
    * */
    private void jumpNextPager()
    {
        startActivity(new Intent(SplashActivity.this,MainActivity.class));
        finish();
    }
}
