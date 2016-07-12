package com.snake.smartbeijin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.orhanobut.logger.Logger;
import com.snake.smartbeijin.com.snake.smartbeijin.utils.PrefUtils;

import java.util.ArrayList;

/**
 * Created by Hll on 2016/7/11.
 */
public class GuideAcitivity extends Activity {

    private ViewPager viewpager;
    //图片id
    private int[] mImageIds = new int[]{R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3};
    /**
     * 存储图片id的集合
     */
    private ArrayList<ImageView> mImages;
    private LinearLayout ll_dots;
    private ImageView iv_redot;
    private int pointDistance; // 小红点距离左边的距离
    private Button btn_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        initView();
        initData();
    }

    /**
     * 初始化view
     */
    private void initView() {
        viewpager = (ViewPager) findViewById(R.id.vp_guide);
        ll_dots = (LinearLayout) findViewById(R.id.ll_dots);
        iv_redot = (ImageView) findViewById(R.id.iv_red_movedot);
        btn_start = (Button) findViewById(R.id.btn_start);
    }

    /**
     * 初始化数据data
     */
    private void initData() {

        /** 初始化三张图片*/
        mImages = new ArrayList<ImageView>();
        for (int i = 0; i < mImageIds.length; i++) {
            ImageView mImageView = new ImageView(this);
            mImageView.setBackgroundResource(mImageIds[i]);
            mImages.add(mImageView);

            /**1 初始化小圆点*/
            ImageView iv_dot = new ImageView(this);
            iv_dot.setImageResource(R.drawable.shape_dots_normal);

            /** 2 初始化布局参数 */
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            // 3 进行判断,如果是第2,设置左边距
            if (i > 0) {
                params.leftMargin = 10;
            }
            // 设置布局参数
            iv_dot.setLayoutParams(params);
            // 添加圆点添加上去
            ll_dots.addView(iv_dot);
        }
        //给viewpager设置适配器*/
        viewpager.setAdapter(new mViewPager());

        /** viewpager设置滑动监听器,
         * 页面滑动,进行小红点位置的移动*/
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //页面滑动的时候得到移动小红点的位置,更新小红点的位置
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                System.out.println("position　：" + position + " 当前页面移动的百分比positionOffset :" +
                        " " + positionOffset + "positionOffsetPixels  " + positionOffsetPixels);
                // 通过修改小红点的左边距来更新小红点的位置
                //要将当前的位置信息产生的距离加进来   float 转 int  四舍五入
                int leftMargin = (int) (pointDistance * positionOffset + pointDistance * position + 0.5f);
                // 2 获取小红点的布局参数
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) iv_redot.getLayoutParams();
                params.leftMargin = leftMargin;
                iv_redot.setLayoutParams(params);

                /**监听页面滑动,设置button的显示状态*/
                if (position != 2) {
                    btn_start.setVisibility(View.GONE);

                } else {
                    btn_start.setVisibility(View.VISIBLE);
                    btn_start.setOnClickListener(new View.OnClickListener() {
                        //点击开始体验按钮
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            PrefUtils.putBoolean(getApplication(),"is_guide_show",true);
                            finish();
                        }
                    });
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        /**监听layout执行结束的事件,获取小红点的left位置*/
        iv_redot.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {



            //视图树渲染完毕
            @Override
            public void onGlobalLayout() {
              // 得到第一个点左边位置的左边距
                pointDistance = ll_dots.getChildAt(1).getLeft() - ll_dots.getChildAt(0).getLeft();

                // 用完观察者之后移除观察者,用一次调用一次
                iv_redot.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    /**
     * pagerAdapter

     */
    private class mViewPager extends PagerAdapter {

        @Override
        public int getCount() {
            return mImages.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = mImages.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }
}
