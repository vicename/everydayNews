package com.guanyue.everydaynews.base;

import android.content.Context;

import com.generallibrary.net.HttpUtils;
import com.generallibrary.net.IHttpUtils;
import com.generallibrary.okhttp.callback.Callback;
import com.generallibrary.utils.LibDateUtil;
import com.generallibrary.utils.Logger;
import com.generallibrary.utils.SHA256Utils;

import java.io.File;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * 网络请求的代理封装
 * Created by yueguang on 16-12-15.
 */

public class HttpHandler implements IHttpUtils {
    private static HttpHandler mHttpHandler;
    private IHttpUtils mHttpUtilProxy;
    private Context mContext;

    private HttpHandler(Context context) {
        mContext = context;
        HttpUtilProxy httpUtilProxy = new HttpUtilProxy(new HttpUtils());
        mHttpUtilProxy = (IHttpUtils) (Proxy.newProxyInstance(IHttpUtils.class.getClassLoader(),
                new Class[]{IHttpUtils.class}, httpUtilProxy));
    }

    public synchronized static HttpHandler initInstance(Context context) {
        if (mHttpHandler == null) {
            mHttpHandler = new HttpHandler(context);
        }
        return mHttpHandler;
    }

    public synchronized static HttpHandler getInstance() {
        return mHttpHandler;
    }


    @Override
    public void get(String url, Callback callBack) {
        get(url, new HashMap<String, String>(), callBack);
    }

    @Override
    public void get(String url, Map<String, String> params, Callback callBack) {
        if (params == null) {
            params = new HashMap<>();
        }
        mHttpUtilProxy.get(url, params, callBack);
    }

    @Override
    public void post(String url, Map<String, String> params, Callback callBack) {
        if (params == null) {
            params = new HashMap<>();
        }
        mHttpUtilProxy.post(url, params, callBack);
    }

    @Override
    public void postFile(String url, Map<String, String> params, String fileKey, File file, Callback callBack) {
        if (params == null) {
            params = new HashMap<>();
        }
        mHttpUtilProxy.postFile(url, params, fileKey, file, callBack);
    }

    @Override
    public void postFile(String url, Map<String, String> params, Callback callBack) {
        if (params == null) {
            params = new HashMap<>();
        }
        mHttpUtilProxy.postFile(url, params, callBack);
    }


    private class HttpUtilProxy implements InvocationHandler {
        private Object target;

        HttpUtilProxy(Object target) {
            this.target = target;
        }

        @Override
        @SuppressWarnings("unchecked")
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            for (Object arg : args) {
                if (arg instanceof Map) {
                    initParams((Map<String, String>) arg);
                    break;
                }
            }

            return method.invoke(target, args);
        }
    }

    private void initParams(Map<String, String> params) {
//        params.put("time", LibDateUtil.getCurrentTime());
//        UserManager userManager = UserManager.getInstance(mContext);
//        if (!params.containsKey("token")) {
//            if (userManager.isLogin()) {
//                params.put("token", userManager.getUser().getToken());
//            }
//        }
//        StringBuilder sb = new StringBuilder();
//        Collection<String> keySet = params.keySet();
//        Set<String> list = new TreeSet<>(keySet);
//        for (String key : list) {
//            if ("auth".equals(key)) {
//                continue;
//            }
//            if (params.get(key) == null) {
//                params.put(key, "");
//            }
//            sb.append(params.get(key));
//        }
//        String auth = SHA256Utils.shaEncode(sb.toString());
//        params.put("auth", auth);
//        Logger.i(1, "params", params.toString());
    }
}
