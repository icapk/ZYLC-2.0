# ZYLC-2.0
新版智能短信控制系统

2017.1.30

	1.横竖屏设置：（Manifest文件下<activity>节点下设置）
                   android:screenOrientation="portrait">     设置始终竖屏
                   android:screenOrientation="landscape">  设置始终横屏
	2.设置分屏功能：（Manifest文件下<application>节点下设置）
					android:resizeableActivity="true"   开启分屏功能
					android:resizeableActivity="false"  关闭分屏功能
	3.设置虚拟按键： （Activity下OnCreate方法中选取任意View对象即可，
		               有触摸事件发生时会重现屏幕的Layout会自动收缩
					   适应新的屏幕大小。）
					  View view = getWindow().getDecorView();
                   //自动隐藏虚拟按键和状态栏
                   view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |                       
                   View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);      
	4. 当targetSdkVersion版本小于等于13时在应用下方的虚拟按键上显示menu按钮
	
	 - 项目详情见个人站：http://icapk.com/zylc-sms/
 
