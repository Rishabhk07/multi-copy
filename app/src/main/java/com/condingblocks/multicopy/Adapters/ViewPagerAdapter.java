package com.condingblocks.multicopy.Adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.condingblocks.multicopy.R;
import com.condingblocks.multicopy.views.Activities.BaseActivity;

/**
 * Created by rishabhkhanna on 30/05/17.
 */

public class ViewPagerAdapter extends PagerAdapter {
    Context context;
    int[] layouts;

    public ViewPagerAdapter(Context context,int[] layouts) {
        this.context = context;
        this.layouts = layouts;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = li.inflate(layouts[position],container,false);
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return layouts.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}
