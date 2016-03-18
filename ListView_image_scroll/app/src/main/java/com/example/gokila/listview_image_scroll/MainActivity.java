package com.example.gokila.listview_image_scroll;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by MallowTech on 3/16/2016.
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ListView listview = (ListView) findViewById(R.id.listView);
        MultiLayoutAdapter adapter = new MultiLayoutAdapter();
        adapter.addItem(new Content("First", Content.TYPE_TEXT));
        adapter.addItem(new Content("Second", Content.TYPE_TEXT));
        adapter.addItem(new Content("Imageset 1", Content.TYPE_IMAGESET));
        adapter.addItem(new Content("Third", Content.TYPE_TEXT));
        adapter.addItem(new Content("four", Content.TYPE_TEXT));
        adapter.addItem(new Content("Five", Content.TYPE_TEXT));
        adapter.addItem(new Content("Six", Content.TYPE_TEXT));
        adapter.addItem(new Content("Seven", Content.TYPE_TEXT));
        adapter.addItem(new Content("Eight", Content.TYPE_TEXT));

        listview.setAdapter(adapter);
    }

    /* Simple class for multi-type content */
    private class Content {
        private static final int TYPE_TEXT = 0;
        private static final int TYPE_IMAGESET = 1;
        private static final int TYPE_COUNT = TYPE_IMAGESET + 1;
        private String mString;
        private int mType;

        public Content(String s, int type) {
            mString = s;
            mType = type;
        }

        public String getString() {
            return mString;
        }

        public int getType() {
            return mType;
        }
    }

    /* Adapter supporting multiple layouts */
    private class MultiLayoutAdapter extends BaseAdapter {
        private ArrayList<Content> mData = new ArrayList<Content>();
        private LayoutInflater mInflater;

        public MultiLayoutAdapter() {
            mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public void addItem(final Content item) {
            mData.add(item);
            notifyDataSetChanged();
        }

        @Override
        public int getItemViewType(int position) {
            return mData.get(position).getType();
        }

        @Override
        public int getViewTypeCount() {
            return Content.TYPE_COUNT;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public String getItem(int position) {
            return mData.get(position).getString();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View rowView = mInflater.inflate(R.layout.row_text, parent, false);

            switch (getItemViewType(position)) {
                case Content.TYPE_TEXT:
                    rowView = mInflater.inflate(R.layout.row_text, parent, false);
                    TextView textView = (TextView) rowView.findViewById(R.id.textView);
                    textView.setText(mData.get(position).getString());
                    break;
                case Content.TYPE_IMAGESET:
                    rowView = mInflater.inflate(R.layout.row_image, parent, false);
                    ViewPager viewPager = (ViewPager) rowView.findViewById(R.id.viewPager);
                    viewPager.setAdapter(new ImagePageAdapter(getApplicationContext()));
                    break;
            }
            return rowView;
        }
    }
}