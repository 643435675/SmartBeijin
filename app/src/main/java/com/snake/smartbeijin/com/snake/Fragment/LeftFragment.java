package com.snake.smartbeijin.com.snake.Fragment;

import android.view.View;

import com.snake.smartbeijin.R;

/**
 * 侧边栏Fragment
 * Created by Hll on 2016/7/12.
 */
public class LeftFragment extends BaseFragment {
    @Override
    public View initViews() {
        View view = View.inflate(mActivity, R.layout.fragment_left_menu, null);
        return view;
    }


}
