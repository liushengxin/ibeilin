package cn.ibeilin.ibeilin;

import cn.ibeilin.ibeilin.R.anim;
import cn.ibeilin.ibeilin.R.id;
import cn.ibeilin.ibeilin.util.SystemUiHider;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


public class SplashActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.
				 FEATURE_NO_TITLE);//»•µÙ±ÍÃ‚¿∏
		setContentView(R.layout.activity_splash);


		ImageView IV1=(ImageView) findViewById(id.qidongbeijing_IV);
		ImageView IV2=(ImageView) findViewById(id.qidongzimu_IV);
		

        Animation animation = AnimationUtils.loadAnimation(this,  
                R.anim.qidong_anim);  
		IV2.startAnimation(animation);

    //    Animation animation = AnimationUtils.loadAnimation(this,  R.anim.rotate_demo);  
    //    imageView.startAnimation(animation);  
	}
}
	
