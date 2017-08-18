package com.guanyue.everydaynews.base;

import android.app.Application;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

import com.generallibrary.base.ApplicationBase;
import com.generallibrary.base.DifDefine;
import com.generallibrary.utils.Logger;
import com.generallibrary.utils.SPUtils;
import com.guanyue.everydaynews.user.UserBean;
import com.guanyue.everydaynews.user.UserManager;
import com.umeng.socialize.PlatformConfig;

import ai.botbrain.ttcloud.api.TtCloudManager;
import ai.botbrain.ttcloud.api.TtcClient;

/**
 * Created by LiDaChang on 17/7/19.
 * __--__---__-------------__----__
 */

public class PingApplication extends ApplicationBase implements UserManager.IUserChangedObserver {
    private UserManager mUserManager;

    private boolean mIsSensorEnabled;
    private Drawable mDrawableTempForShareElement;

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.line("程序启动", "0000");
        TtcClient client = new TtcClient.Builder()
                .setDebug()
                .build();
        TtCloudManager.init(this, client);
        HttpHandler.initInstance(this);
        mUserManager = UserManager.getInstance();
        mUserManager.registerObserver(this);

        PlatformConfig.setWeixin("","");
        PlatformConfig.setQQZone("","");
        PlatformConfig.setSinaWeibo("","","");
    }

    public void setLibDebug(boolean isDebug) {
        DifDefine.mIsDebug = isDebug;
    }


    public boolean isSensorEnabled() {
        return mIsSensorEnabled;
    }

    public void setIsSensorEnable(boolean isEnabled) {
        mIsSensorEnabled = isEnabled;
        SPUtils spUtils = new SPUtils(this);
        spUtils.put("isSensor", isEnabled);
    }

    public void setShareElementDrawable(@NonNull Drawable drawable) {
        mDrawableTempForShareElement = drawable;
    }

    public void clearShareElementDrawable() {
        mDrawableTempForShareElement = null;
    }

    public Drawable getShareElementDrawable() {
        if (mDrawableTempForShareElement != null) {
            return mDrawableTempForShareElement;
        }
        return null;
    }

    @Override
    public void onUserLogin() {
    }

    @Override
    public void onUserInfoUpdate(UserBean user) {

    }

    @Override
    public void onUserLogout() {

    }
}
