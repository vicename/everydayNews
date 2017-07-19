package com.guanyue.everydaynews.base;

import android.app.Application;

import com.generallibrary.utils.Logger;

import ai.botbrain.ttcloud.api.TtCloudManager;
import ai.botbrain.ttcloud.api.TtcClient;

/**
 * Created by LiDaChang on 17/7/19.
 * __--__---__-------------__----__
 */

public class ApplicationPlus extends Application {
    private static ApplicationPlus mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.line("程序启动", "0000");
        mInstance = this;
        TtcClient client = new TtcClient.Builder()
                .setDebug()
                .build();
        TtCloudManager.init(this, client);
        HttpHandler.initInstance(this);
    }

    public static ApplicationPlus getInstance() {
        // 这里不用判断instance是否为空
        return mInstance;
    }
}
