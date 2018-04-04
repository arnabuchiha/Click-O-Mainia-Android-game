package com.virtualsense.tetris;

/**
 * Created by arnab on 4/3/2018.
 */
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;

public class GridViewCustomAdapter extends BaseAdapter {

    ArrayList<String> items;
    String color[]={
            "#FF716A2D" ,
            "#FFFF00" ,
            "#FF00FF" ,
            "#FF0000" ,
            "#C0C0C0" ,
            "#808080" ,
            "#808000" ,
            "#800080" ,
            "#800000" ,
            "#00FFFF" ,
            "#00FF00" ,
            "#008080" ,
            "#008000" ,
            "#0000FF" ,
            "#000080" ,
            "#51247e" ,
            "#3F51B5" ,
            "#303F9F" ,
            "#FF4081"};
    static Activity mActivity;

    private static LayoutInflater inflater = null;

    public GridViewCustomAdapter(Activity activity, ArrayList<String> tempTitle) {
        mActivity = activity;
        items = tempTitle;

        inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public final int getCount() {

        return items.size();

    }

    @Override
    public final Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public final long getItemId(int position) {

        return position;
    }

    @SuppressLint("ResourceType")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = null;
        /**
         * For button sizes according to screen size and setting button unique color
         */
        DisplayMetrics metrics = new DisplayMetrics();
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        v = inflater.inflate(R.layout.item, null);
        v.setMinimumHeight(height/10);
        if(Integer.valueOf(items.get(position))!=0) {
            Button tv = v.findViewById(R.id.button);
            tv.setText(items.get(position));
            tv.setBackgroundColor(Color.parseColor(color[Integer.valueOf(items.get(position))-1]));
        }
        else{
            Button tv=v.findViewById(R.id.button);
            tv.setText("");
            tv.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        return v;
    }

}
