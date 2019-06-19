package com.jr.sikika.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jr.sikika.R;
import com.jr.sikika.classes.ViewPageItem;

import java.util.List;

public class ViewPageAdapter extends PagerAdapter {

    Context context;
    List<ViewPageItem> viewPageItems;

    public ViewPageAdapter(Context context, List<ViewPageItem> viewPageItems) {
        this.context = context;
        this.viewPageItems = viewPageItems;
    }

    @Override
    public int getCount() {
        return viewPageItems.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.viewpage_item, null);

        TextView title = view.findViewById(R.id.itemTitle);
        TextView desc = view.findViewById(R.id.itemDesc);
        ImageView image = view.findViewById(R.id.itemDrawable);

        ViewPageItem item = viewPageItems.get(position);

        title.setText(item.getTitle());
        desc.setText(item.getDescription());
        image.setImageResource(item.getImageId());

        container.addView(view);
        return view;
    }
}
