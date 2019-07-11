package com.jr.sikika.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.jr.sikika.adapters.ArticleAdapter;
import com.jr.sikika.R;
import com.jr.sikika.classes.ArticleClass;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;


public class Articles extends Fragment {
    ArticleAdapter articleAdapter;
    ListView listView;
    LinearLayout loadingSplash;
    List<ArticleClass> articles;
    String nextLink = null;
    View footerView;
    Button loadMore;
    ProgressBar loading;
    int loadedTimes = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_articles, container, false);

        listView = view.findViewById(R.id.articlesList);
        loadingSplash = view.findViewById(R.id.loadingSplash);
        articles = new ArrayList<>();
        String url = "https://sikika.net/articles/";
        loadContent(url);
        articleAdapter = new ArticleAdapter();

        footerView = LayoutInflater.from(getContext()).inflate(R.layout.loading, null);
        loadMore = footerView.findViewById(R.id.btnLoadMore);
        loading = footerView.findViewById(R.id.progressLoading);

        loadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadContent(nextLink);
                loadMore.setVisibility(View.GONE);
                loading.setVisibility(View.VISIBLE);
            }
        });


        return view;
    }

    public void loadContent(String url){

        AsyncHttpClient httpClient = new AsyncHttpClient();
        httpClient.get(getContext(), url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(getContext(), "failed to load data", Toast.LENGTH_SHORT).show();
                loadMore.setVisibility(View.VISIBLE);
                loading.setVisibility(View.GONE);
                return;
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Document document = Jsoup.parse(responseString);
                Elements elements = document.select("div[class=et_pb_ajax_pagination_container] article");
                for (Element elm: elements) {
                    Element artLink = elm.selectFirst("a");
                    String link = artLink.attr("href");
                    Element artImg = elm.selectFirst("a img");
                    String imageUrl = artImg.attr("src");
                    Element artTitle = elm.selectFirst("h2 a");
                    String title = artTitle.html();
                    Element artBy = elm.selectFirst("p span a");
                    String lts = artBy.attr("href");
                    String by = artBy.html();
                    Element artDesc = elm.selectFirst("div p");
                    String desc = artDesc.html();

                    Elements nl = document.select("div[class=pagination clearfix]>div[class=alignleft]>a");

                    if(nl.size() == 1){
                        nextLink = nl.attr("href").trim();
                    }else{
                        nextLink = null;
                    }

                    articles.add(new ArticleClass(title, desc, link, imageUrl, by, lts));
                }

                articleAdapter.setArticles(articles);
                articleAdapter.setContext(getContext());

                if(loadedTimes == 0){
                    listView.setAdapter(articleAdapter);
                }else{
                    articleAdapter.notifyDataSetChanged();
                }
                loadedTimes++;

                if(nextLink == null){
                    loading.setVisibility(View.GONE);
                    loadMore.setVisibility(View.GONE);
                }else{
                    loadMore.setVisibility(View.VISIBLE);
                    loading.setVisibility(View.GONE);
                }
                listView.removeFooterView(footerView);

                listView.addFooterView(footerView);

                loadingSplash.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);

            }
        });

    }



}
