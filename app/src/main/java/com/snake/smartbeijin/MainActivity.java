package com.snake.smartbeijin;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.snake.smartbeijin.com.snake.Fragment.ContentFragment;
import com.snake.smartbeijin.com.snake.Fragment.LeftFragment;

/**
 * Created by Hll on 2016/7/11.
 */
public class MainActivity extends SlidingFragmentActivity {
    private SlidingMenu menu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //设置侧边栏布局
        setBehindContentView(R.layout.left_menu);

        menu = getSlidingMenu();
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN); //全屏幕触摸
        menu.setBehindOffset(200);// 屏幕预留300px

        // 初始化Fragment
        initFragment();
    }
    /**初始化Fragment*/
    private void initFragment() {
        // getFragmentManager(); 4.0+
        // 获取Fragment管理器
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction(); // 开启事物

        // 使用Fragment替换现有布局,
       ft.replace(R.id.fl_mian_content, new ContentFragment());
        ft.replace(R.id.fl_left_menu,new LeftFragment());
        ft.commit(); //提交事物
    }
}
