package com.jr.sikika;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Walkthrough extends AppCompatActivity {
    @BindView(R.id.screenPager)
    ViewPager screenpager;
    WTAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walkthrough);
        ButterKnife.bind(this);
        ScreenItem one = new ScreenItem("one", getResources().getString(R.string.ls), R.drawable.sikika_logo);
        ScreenItem two = new ScreenItem("two", getResources().getString(R.string.ls), R.drawable.sikika_logo);
        ScreenItem three = new ScreenItem("three", getResources().getString(R.string.ls), R.drawable.ic_arrow_forward_black_24dp);

        List<ScreenItem> slist = new ArrayList<>();
        slist.add(one);
        slist.add(two);
        slist.add(three);
        adapter = new WTAdapter(this, slist);

        screenpager.setAdapter(adapter);
    }
}
