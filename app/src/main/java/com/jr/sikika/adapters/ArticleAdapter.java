package com.jr.sikika.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jr.sikika.ArticleReader;
import com.jr.sikika.R;
import com.jr.sikika.classes.ArticleClass;

import java.util.List;

public class ArticleAdapter extends BaseAdapter {

    Context context;
    List<ArticleClass> articles;


    public void setContext(Context context) {
        this.context = context;
    }

    public void setArticles(List<ArticleClass> articles) {
        this.articles = articles;
    }

    @Override
    public int getCount() {
        return articles.size();
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

        convertView = LayoutInflater.from(context).inflate(R.layout.item_articles, null);
        TextView title = convertView.findViewById(R.id.article_title);
        ImageView imageView = convertView.findViewById(R.id.article_image);
        TextView snippet = convertView.findViewById(R.id.article_snippet);
        Button read = convertView.findViewById(R.id.btn_read);

        final ArticleClass article = articles.get(position);

        title.setText(article.getTitle());
        snippet.setText(article.getSnippet());
        Glide.with(context).load(article.getImageSource()).into(imageView);

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x = new Intent(context, ArticleReader.class);
                x.putExtra("url", article.getUrl());
                x.putExtra("imgUrl", article.getImageSource());
                context.startActivity(x);
            }
        });

        return convertView;
    }
}
