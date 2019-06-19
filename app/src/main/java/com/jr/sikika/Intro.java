package com.jr.sikika;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.jr.sikika.adapters.ViewPageAdapter;
import com.jr.sikika.classes.ViewPageItem;

import java.util.ArrayList;
import java.util.List;

public class Intro extends AppCompatActivity {

    ViewPager viewPager;
    RelativeLayout relativeLayout;
    TabLayout tabLayout;
    PagerAdapter pagerAdapter;
    List<ViewPageItem> pageItems;
    Button btnnext;
    Button btngetstarted;
    Button btnback;
    Button btnskip;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        String desc = getResources().getString(R.string.lorem);

        viewPager = findViewById(R.id.introViewPager);
        relativeLayout = findViewById(R.id.introRlayout);
        tabLayout = findViewById(R.id.introTabLayout);
        btnnext = findViewById(R.id.btnNext);
        btngetstarted = findViewById(R.id.btnGetStarted);
        btnback = findViewById(R.id.btnBack);
        btnskip = findViewById(R.id.btnSkip);


        pageItems = new ArrayList<>();
        pageItems.add(new ViewPageItem("articles", desc, R.drawable.sikika_articles));
        pageItems.add(new ViewPageItem("creative arts", desc, R.drawable.sikika_creative_arts));
        pageItems.add(new ViewPageItem("drama", desc, R.drawable.sikika_drama));
        pageItems.add(new ViewPageItem("features", desc, R.drawable.sikika_features));
        pageItems.add(new ViewPageItem("film", desc, R.drawable.sikika_film));
        pageItems.add(new ViewPageItem("partners", desc, R.drawable.sikika_partners));
        pageItems.add(new ViewPageItem("stream live", desc, R.drawable.sikika_streamlive));

        final List<Integer> backgrounds = new ArrayList<>();
        backgrounds.add(R.drawable.sikika_articles);
        backgrounds.add(R.drawable.sikika_creative_arts);
        backgrounds.add(R.drawable.sikika_drama);
        backgrounds.add(R.drawable.sikika_features);
        backgrounds.add(R.drawable.sikika_film);
        backgrounds.add(R.drawable.sikika_partners);
        backgrounds.add(R.drawable.sikika_streamlive);


        pagerAdapter = new ViewPageAdapter(this, pageItems);

        viewPager.setAdapter(pagerAdapter);

        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                visibilityDeterminer(viewPager.getCurrentItem());
                relativeLayout.setBackgroundResource(backgrounds.get(viewPager.getCurrentItem()));
            }

            @Override
            public void onPageSelected(int i) {
                visibilityDeterminer(i);
                relativeLayout.setBackgroundResource(backgrounds.get(i));
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                //Toast.makeText(Intro.this, ""+i, Toast.LENGTH_SHORT).show();
                //visibilityDeterminer(i);
            }
        });

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position = viewPager.getCurrentItem();

                if(position < pageItems.size()-1){
                    position ++;
                    viewPager.setCurrentItem(position, true);
                    visibilityDeterminer(position);
                }
            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position = viewPager.getCurrentItem();
                if(position >= 1){
                    position --;
                    viewPager.setCurrentItem(position, true);
                    visibilityDeterminer(position);
                }
            }
        });

        btngetstarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //got to start activity
                startActivity(new Intent(Intro.this, HomePage.class));
                finish();
            }
        });

        btnskip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //got to start activity
                startActivity(new Intent(Intro.this, HomePage.class));
                finish();
            }
        });
    }

    private void visibilityDeterminer(int position) {
        if(position == pageItems.size()-1){
            btngetstarted.setVisibility(View.VISIBLE);
            btnnext.setVisibility(View.GONE);
            btnskip.setVisibility(View.GONE);
        }

        if(position == 0){
            btnback.setVisibility(View.GONE);
            btngetstarted.setVisibility(View.GONE);
            btnnext.setVisibility(View.VISIBLE);
            btnskip.setVisibility(View.VISIBLE);
        }
        if(position !=pageItems.size()-1 && position !=0){
            btnback.setVisibility(View.VISIBLE);
            btngetstarted.setVisibility(View.GONE);
            btnnext.setVisibility(View.VISIBLE);
            btnskip.setVisibility(View.VISIBLE);
        }
    }

}
