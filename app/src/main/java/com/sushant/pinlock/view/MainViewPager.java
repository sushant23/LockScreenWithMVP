package com.sushant.pinlock.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import com.sushant.pinlock.adapter.MainViewPagerAdapter;

/**
 * Created by braindigit on 3/16/16.
 */
public class MainViewPager extends ViewPager {
    public MainViewPager(Context context) {
        super(context);
        init();
    }

    public MainViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setAdapter(new MainViewPagerAdapter(getContext()));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
