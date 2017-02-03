package com.example.gte.sms_demo_12.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.gte.sms_demo_12.Fragment.SecondFragment;
import com.example.gte.sms_demo_12.R;


/**
 * 闪屏页面
 */
public class SplashActivity extends Activity {

	private RelativeLayout rlRoot;
	private Button btn_jump;
	private ImageView img_splash;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_splash);

		rlRoot = (RelativeLayout) findViewById(R.id.rl_splash);
		//判断当前手机版本是否大于Android5.0，是，则隐藏虚拟按键和状态栏
		if(Build.VERSION.SDK_INT >=21 ){
			img_splash = (ImageView)findViewById(R.id.im_splash);
			//设置隐藏虚拟按键
			img_splash.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
			//设置隐藏状态栏
			img_splash.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

			img_splash.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
			img_splash.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
			img_splash.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
			//设置状态栏状态为透明
			getWindow().setStatusBarColor(Color.TRANSPARENT);
			getWindow().setNavigationBarColor(Color.TRANSPARENT);
		}



		btn_jump = (Button)findViewById(R.id.btn_jump);
		btn_jump.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),
                        MainActivity.class);
                startActivity(intent);

                finish();
			}
		});

		// 旋转动画
		RotateAnimation animRotate = new RotateAnimation(0, 360,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		animRotate.setDuration(5000);// 动画时间
		animRotate.setFillAfter(true);// 保持动画结束状态

		// 缩放动画
		ScaleAnimation animScale = new ScaleAnimation(0, 1, 0, 1,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		animScale.setDuration(5000);
		animScale.setFillAfter(true);// 保持动画结束状态

		// 渐变动画
		AlphaAnimation animAlpha = new AlphaAnimation(0, 1);
		animAlpha.setDuration(5000);// 动画时间
		animAlpha.setFillAfter(true);// 保持动画结束状态

		// 动画集合
		AnimationSet set = new AnimationSet(true);
//		set.addAnimation(animRotate);
//		set.addAnimation(animScale);
		set.addAnimation(animAlpha);

		// 启动动画
		rlRoot.startAnimation(set);

		set.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {

			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}

			@Override
			public void onAnimationEnd(Animation animation) {

				Intent intent = new Intent(getApplicationContext(),
							MainActivity.class);

				startActivity(intent);

				finish();// 结束当前页面
			}
		});
	}

	/**
	 * 设置虚拟按键和状态栏的状态
	 * @param hasFocus
	 */
	public void onWindowFocusChanged(boolean hasFocus ) {
		super.onWindowFocusChanged(hasFocus);
		if(hasFocus && Build.VERSION.SDK_INT >= 21) {//判断sdk版本是否大于21，是，则隐藏虚拟按键和状态栏
			View view = getWindow().getDecorView();
			//隐藏虚拟按键和状态栏
			view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
			//显示虚拟按键和状态栏
//            view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
			//设置状态栏透明
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);

		}
	}

}
