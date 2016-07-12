package com.snake.smartbeijin.com.snake.Fragment;

import android.view.View;

import com.snake.smartbeijin.R;

/**
 * 主界面Fragment
 * Created by Hll on 2016/7/12.
 */
public class ContentFragment extends BaseFragment {
    @Override
    public View initViews() {
        View view = View.inflate(mActivity, R.layout.fragment_right_content, null);
        return view;
    }
}
