package com.guanyue.everydaynews.activity;

import android.app.Activity;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.guanyue.everydaynews.R;
import com.guanyue.everydaynews.handler.ThirdLoginHandler;
import com.guanyue.everydaynews.widget.PwMainTitleBar;
import com.umeng.socialize.bean.SHARE_MEDIA;

public class LoginActivity extends AppBaseActivity {

    private View mViewLoginQQ;
    private View mViewLoginWx;
    private View mViewLoginSina;

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
        mViewLoginQQ = findViewById(R.id.l_layout_login_qq);
        mViewLoginSina = findViewById(R.id.l_layout_login_sina);
        mViewLoginWx = findViewById(R.id.l_layout_login_wx);
        mViewLoginQQ.setOnClickListener(new ClickLogin());
        mViewLoginSina.setOnClickListener(new ClickLogin());
        mViewLoginWx.setOnClickListener(new ClickLogin());
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

    private class ClickLogin implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.l_layout_login_qq:
                    ThirdLoginHandler.login((Activity) mContext, SHARE_MEDIA.QQ);
                    break;
                case R.id.l_layout_login_wx:
                    ThirdLoginHandler.login((Activity) mContext, SHARE_MEDIA.WEIXIN);
                    break;
                case R.id.l_layout_login_sina:
                    ThirdLoginHandler.login((Activity) mContext, SHARE_MEDIA.SINA);
                    break;
            }
        }
    }

}
