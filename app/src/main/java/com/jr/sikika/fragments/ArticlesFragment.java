package com.jr.sikika.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.jr.sikika.classes.Article;
import com.jr.sikika.adapters.GridLayoutAdapter;
import com.jr.sikika.R;

import java.util.ArrayList;
import java.util.List;


public class ArticlesFragment extends Fragment {
    GridLayoutAdapter gridLayoutAdapter;
    GridView gridView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.articles_fragment_layout, container, false);

        gridView = view.findViewById(R.id.articlesGrid);
        List<Article> articles = new ArrayList<>();


        gridLayoutAdapter = new GridLayoutAdapter(getContext(), articles);
        gridView.setAdapter(gridLayoutAdapter);

        return view;
    }



}
