package com.jr.sikika.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.jr.sikika.R;
import com.jr.sikika.classes.Article;

import java.util.List;

public class GridLayoutAdapter extends BaseAdapter {

    Context context;
    List<Article> articles;

    public GridLayoutAdapter(Context context, List<Article> articles) {
        this.context = context;
        this.articles = articles;
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.articles_grid_layout, null);
        return convertView;
    }
}
