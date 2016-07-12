package com.snake.smartbeijin.com.snake.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Hll on 2016/7/12.
 * Fragment基类
 */
public  abstract  class BaseFragment extends Fragment {
    public Activity mActivity;
    private View mRootView;

    /** 创建Fragment*/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }
    /**初始化Fragment布局,此处Fragment不能为空,否则就创建失败了*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = initViews();
        return mRootView;
    }

    /**Fragment所在的Activity创建完成*/
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**初始化Fragment布局,返回view  必须由子类实现*/
    public  abstract View  initViews() ;

    /**初始化数据,子类可以不实现*/
    public void initData(){}
}
