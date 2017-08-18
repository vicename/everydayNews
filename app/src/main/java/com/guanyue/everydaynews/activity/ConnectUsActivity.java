package com.guanyue.everydaynews.activity;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.guanyue.everydaynews.R;
import com.guanyue.everydaynews.widget.PwMainTitleBar;

public class ConnectUsActivity extends AppBaseActivity {

    private PwMainTitleBar mTitleBar;

    @Override
    protected void initVar() {

    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_connect_us);
        mTitleBar = ((PwMainTitleBar) findViewById(R.id.titleBar));
        mTitleBar.setOnNavigationBtnClickListener(new PwMainTitleBar.OnNavigationBtnClickListener() {
            @Override
            public void onNavigationClick(View v) {
                finish();
            }
        });
        mTitleBar.setTitle("联系我们");
        findViewById(R.id.tv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
