package com.guanyue.everydaynews;

import android.app.Activity;
import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.guanyue.everydaynews.activity.AppBaseActivity;
import com.guanyue.everydaynews.activity.MainActivity;

public class SplashActivity extends AppBaseActivity {

    @Override
    protected void initVar() {

    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_splash);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        },800);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

    }

    @Override
    public void handleMessage(Message msg) {

    }
}
