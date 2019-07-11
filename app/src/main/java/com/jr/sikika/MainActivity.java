package com.jr.sikika;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jr.sikika.database.DatabaseAccess;
import com.jr.sikika.database.FirstRun;
import com.jr.sikika.database.FirstRunInterface;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    FirstRunInterface firstRunInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstRunInterface = DatabaseAccess.getInstance(this).getRun();
        final List<FirstRun> firstRunList = firstRunInterface.selectAll();
        Handler proceed = new Handler();
        proceed.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(firstRunList.size() == 0){
                    startActivity(new Intent(MainActivity.this, Intro.class));
                    finish();
                }else{
                    startActivity(new Intent(MainActivity.this, HomePage.class));
                    finish();
                }
            }
        }, 1000);
    }
}
