package com.sushant.pinlock.adapter;

import android.app.ActionBar;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sushant.pinlock.view.PinView;

/**
 * Created by braindigit on 3/16/16.
 */
public class MainViewPagerAdapter extends PagerAdapter {

    private Context context;

    public MainViewPagerAdapter(Context context) {
        this.context = context;
    }


    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view ==  object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = null;
        switch (position) {
            case 1:
                ImageView imageView = new ImageView(context);
                imageView.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_blue_light));
                ViewGroup.LayoutParams layoutParams = new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                imageView.setLayoutParams(layoutParams);
                imageView.setImageResource(android.R.drawable.ic_btn_speak_now);
                view = imageView;
                break;
            case 0:
                view = new PinView(context);
                break;
        }

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }
}
