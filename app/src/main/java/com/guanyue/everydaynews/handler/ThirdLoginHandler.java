package com.guanyue.everydaynews.handler;

import android.app.Activity;

import com.generallibrary.utils.Logger;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.Map;

import ai.botbrain.ttcloud.api.TtCloudListener;

/**
 * Created by Li DaChang on 17/8/20.
 * ..-..---.-.--..---.-...-..-....-.
 */

public class ThirdLoginHandler {
    public static void login(Activity activity, SHARE_MEDIA platform) {
        UMShareAPI mm = UMShareAPI.get(activity);
        mm.getPlatformInfo(activity, platform, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {
                Logger.i(221);
            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                Logger.i(1, 222);
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                Logger.i(1, "error:" + i + "," + throwable.toString());
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {
                Logger.i(224);
            }
        });
    }

    public static void share(Activity activity, TtCloudListener.Article article) {
        if (article == null) {
            return;
        }
        UMWeb web = new UMWeb(article.getContentUrl());
        web.setTitle(article.getContentTitle());//标题
//        web.setThumb(new UMImage(activity,));  //缩略图
        web.setDescription("");//描述
        new ShareAction(activity)
                .withText("我在财经观察上看到一篇文章,分享给你")
                .withMedia(web)
                .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE)
                .setCallback(new UMShareListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {
                        Logger.i(321);
                    }

                    @Override
                    public void onResult(SHARE_MEDIA share_media) {
                        Logger.i(322);
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                        Logger.i(333);
                        Logger.i(1, "error:" + throwable.toString());
                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media) {
                        Logger.i(334);
                    }
                })
                .open();
    }
}
