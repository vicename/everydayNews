package com.guanyue.everydaynews.presenter;

import android.content.Context;

import com.guanyue.everydaynews.user.UserBean;

/**
 * Created by LiDaChang on 17/8/11.
 * __--__---__-------------__----__
 */

public class MainPresenter {
    private final Context mContext;
    private final MainPageViewCallback mCallback;

    public static MainPresenter create(Context context, MainPageViewCallback callback) {
        return new MainPresenter(context, callback);
    }

    private MainPresenter(Context context, MainPageViewCallback callback) {
        this.mContext = context;
        this.mCallback = callback;
    }

    public interface MainPageViewCallback {
        void onLoginSuccess(UserBean userInfo);

        void onLoginFailed(String msg);

        void onPwdSetSuccess();

        void onPwdSetFailed(String msg);
    }
}
