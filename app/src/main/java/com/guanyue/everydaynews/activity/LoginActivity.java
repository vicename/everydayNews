package com.guanyue.everydaynews.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.generallibrary.utils.Logger;
import com.generallibrary.utils.ToastUtils;
import com.guanyue.everydaynews.R;
import com.guanyue.everydaynews.base.PingApplication;
import com.guanyue.everydaynews.handler.ThirdLoginHandler;
import com.guanyue.everydaynews.user.UserBean;
import com.guanyue.everydaynews.user.UserManager;
import com.guanyue.everydaynews.widget.PwMainTitleBar;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
        Logger.i(1, "data:" + data);
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
                    ThirdLoginHandler.auth((Activity) mContext, SHARE_MEDIA.SINA, new UMAuthListener() {
                        @Override
                        public void onStart(SHARE_MEDIA share_media) {
                            Logger.i(321);
                        }

                        @Override
                        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                            Logger.i(1, 322);
                            ToastUtils.showToast(PingApplication.getInstance(), "登录成功");
                            Logger.i(1, "map:" + map.toString());
                            UserBean userBean = UserManager.getInstance().getUser();
                            userBean.setNickname(map.get("name"));
                            userBean.setUserId(map.get("uid"));
                            String url = "";
                            if (TextUtils.isEmpty(map.get("iconurl"))) {
                                url = "http://cn.bing.com/s/cn/cn_logo_serp.png";
                            }
                            userBean.setPhoto(url);
                            Logger.i(1, "user:" + userBean.toString());
                            UserManager.getInstance().saveUser(userBean);
                            UserManager.getInstance().setIsLogin(true);
                            finish();
                        }

                        @Override
                        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                            Logger.i(326);
                            Logger.i(1, "error:" + i + "," + throwable.getMessage());
                        }

                        @Override
                        public void onCancel(SHARE_MEDIA share_media, int i) {
                            Logger.i(224);
                        }
                    });
                    break;
            }
        }
    }

}
