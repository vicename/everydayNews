package com.guanyue.everydaynews.activity;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.guanyue.everydaynews.R;
import com.guanyue.everydaynews.widget.PwMainTitleBar;

public class LoginActivity extends AppBaseActivity {

    @Override
    protected void initVar() {

    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_login);
        PwMainTitleBar mTitleBar = ((PwMainTitleBar) findViewById(R.id.titleBar));
        mTitleBar.setOnNavigationBtnClickListener(new PwMainTitleBar.OnNavigationBtnClickListener() {
            @Override
            public void onNavigationClick(View v) {
                finish();
            }
        });
        mTitleBar.setTitle("登录");
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
