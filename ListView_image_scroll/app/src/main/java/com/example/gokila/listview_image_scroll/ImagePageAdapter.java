package com.example.gokila.listview_image_scroll;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by MallowTech on 3/16/2016.
 */
public class ImagePageAdapter extends PagerAdapter {
    int NumberOfPages = 3;
    LayoutInflater mInflater;
    int[] res = {
            R.drawable.google2,
            R.drawable.google4,
            R.drawable.google5};

    ImagePageAdapter(Context c) {
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return NumberOfPages;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View v = mInflater.inflate(R.layout.image, container, false);
        ImageView imageView = (ImageView) v.findViewById(R.id.imageView);
        imageView.getImageMatrix();
        imageView.setImageResource(res[position]);
        container.addView(v);

        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}

