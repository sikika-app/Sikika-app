package com.jr.sikika;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Boolean ft = true;
        Handler proceed = new Handler();
        proceed.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(ft){
                    startActivity(new Intent(MainActivity.this, Walkthrough.class));
                    finish();
                }else{
                    startActivity(new Intent(MainActivity.this, Walkthrough.class));
                    finish();
                }
            }
        }, 2000);
    }
}
