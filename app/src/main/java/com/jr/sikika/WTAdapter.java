package com.jr.sikika;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class WTAdapter extends PagerAdapter {
    Context context;
    List<ScreenItem> screenItems;

    public WTAdapter(Context context, List<ScreenItem> screenItems) {
        this.context = context;
        this.screenItems = screenItems;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View inflater = LayoutInflater.from(context).inflate(R.layout.walkthrough_screen, null);

        ImageView imageView = inflater.findViewById(R.id.wtImage);
        TextView title = inflater.findViewById(R.id.wtTitle);
        TextView desc = inflater.findViewById(R.id.wtDesc);

        title.setText(screenItems.get(position).getTitle());
        desc.setText(screenItems.get(position).getDesc());
        imageView.setImageResource(screenItems.get(position).getImg());

        container.addView(inflater);
        return inflater;
    }

    @Override
    public int getCount() {
        return screenItems.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
