package com.snake.smartbeijin;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.snake.smartbeijin.com.snake.smartbeijin.utils.PrefUtils;

public class SplashActivity extends Activity {

    private RelativeLayout rl_splash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_splash);
        rl_splash = (RelativeLayout) findViewById(R.id.rl_splash);
        startAnima();

    }

    /**
     * 开始动画
     */
    private void startAnima() {

        // 缩放动画
        ScaleAnimation sa = new ScaleAnimation(0, 1, 0,1,Animation.RELATIVE_TO_SELF ,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
        sa.setDuration(1000);
        sa.setFillAfter(true);
        // 旋转动画
        RotateAnimation ra = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        ra.setDuration(1000);
        ra.setFillAfter(true);
        //渐变动画
        AlphaAnimation aa = new AlphaAnimation(0, 1);
        aa.setDuration(2000);
        aa.setFillAfter(true);

        //动画集合
        AnimationSet as = new AnimationSet(false); //这里的false代表??
        as.addAnimation(sa);
        as.addAnimation(ra);
        as.addAnimation(aa);

        //开始动画
        rl_splash.startAnimation(as);

        // 开启新的guideactivity
        as.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }
            //动画结束的时候,开启guideactivity
            @Override
            public void onAnimationEnd(Animation animation) {
                //判断有没哟展示过引导页
                boolean is_guide_show = PrefUtils.getBoolean(getApplication(), "is_guide_show", false);
                if (!is_guide_show) {
                    //跳到引导界面
                Intent intent = new Intent(getApplicationContext(),GuideAcitivity.class);
                startActivity(intent);
                } else {
                    //跳到主界面
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }
                finish();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

}
