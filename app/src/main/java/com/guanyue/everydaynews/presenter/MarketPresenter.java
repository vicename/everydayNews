package com.guanyue.everydaynews.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.generallibrary.okhttp.callback.StringCallback;
import com.generallibrary.utils.DifStrMatcher;
import com.generallibrary.utils.DifStrUtils;
import com.generallibrary.utils.Logger;
import com.guanyue.everydaynews.base.HttpHandler;
import com.guanyue.everydaynews.data.JsonArrayParser;
import com.guanyue.everydaynews.data.StockBean;
import com.guanyue.everydaynews.data.StockIndexBean;
import com.guanyue.everydaynews.data.StockInfoBean;
import com.qq.e.comm.util.StringUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by LiDaChang on 17/8/17.
 * __--__---__-------------__----__
 */

public class MarketPresenter {

    private final Context mContext;
    private final MarketViewCallback mCallback;

    public static MarketPresenter create(Context context, MarketViewCallback callback) {
        return new MarketPresenter(context, callback);
    }

    private MarketPresenter(Context context, MarketViewCallback callback) {
        this.mContext = context;
        this.mCallback = callback;
    }

    public void getStockList() {
        Map<String, String> map = new HashMap<>();
        map.put("market", "sh");
        HttpHandler.getInstance().getI("https://ali-stock.showapi.com/stocklist", map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                mCallback.onStockListGetFailed("");
                Logger.i(1, "error:" + e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    JSONObject jb = new JSONObject(response);
                    int code = jb.optInt("showapi_res_code", -1);
                    if (code == 0) {
                        JSONObject jbBody = jb.getJSONObject("showapi_res_body");
                        JSONArray ja = jbBody.getJSONArray("contentlist");
                        List<StockBean> list = new JsonArrayParser<StockBean>().parasToObjects(ja, new JsonArrayParser.JsonObjectParseIt<StockBean>() {
                            @Override
                            public StockBean parasJsonObject(JSONObject jb) {
                                return new StockBean(jb);
                            }
                        });
//                        mCallback.onStockListGetSuccess(list);
                        getInfo(list);
                    } else {
                        mCallback.onStockListGetFailed("");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    mCallback.onStockListGetFailed("");
                }
            }
        });
    }

    public void getInfo(List<StockBean> list) {
        String ccc = "";
        for (StockBean stockBean : list) {
            ccc = ccc + stockBean.code + ",";
        }
        if (ccc.endsWith(",")) {
            ccc = ccc.substring(0, ccc.length() - 1);
        }
        Logger.i(1, "ccc:" + ccc);
        Map<String, String> map = new HashMap<>();
        map.put("stocks", ccc);
        map.put("needIndex", "1");
        HttpHandler.getInstance().getI("https://ali-stock.showapi.com/batch-real-stockinfo", map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Logger.i(1, "error:" + e.toString());
                mCallback.onStockListGetFailed("");
            }

            @Override
            public void onResponse(String response, int id) {
                JSONObject jb = null;
                try {
                    jb = new JSONObject(response);
                    int code = jb.optInt("showapi_res_code", -1);
                    if (code == 0) {
                        JSONObject jbBody = jb.getJSONObject("showapi_res_body");
                        JSONArray jaList = jbBody.getJSONArray("list");
                        List<StockInfoBean> list = new JsonArrayParser<StockInfoBean>().parasToObjects(jaList, new JsonArrayParser.JsonObjectParseIt<StockInfoBean>() {
                            @Override
                            public StockInfoBean parasJsonObject(JSONObject jb) {
                                return new StockInfoBean(jb);
                            }
                        });
                        JSONArray jsIndex = jbBody.getJSONArray("indexList");
                        Logger.i(1, "indexList:" + jsIndex);
                        List<StockIndexBean> indexBeen = new JsonArrayParser<StockIndexBean>().parasToObjects(jsIndex, new JsonArrayParser.JsonObjectParseIt<StockIndexBean>() {
                            @Override
                            public StockIndexBean parasJsonObject(JSONObject jb) {
                                return new StockIndexBean(jb);
                            }
                        });
                        StockIndexBean szBean = null;
                        StockIndexBean shBean = null;
                        for (StockIndexBean stockIndexBean : indexBeen) {
                            if (stockIndexBean.name.equals("深证成指")) {
                                szBean = stockIndexBean;
                            }
                            if (stockIndexBean.name.equals("上证指数")) {
                                shBean = stockIndexBean;
                            }
                        }
                        mCallback.onStockListGetSuccess(list, szBean, shBean);
                    } else {
                        mCallback.onStockListGetFailed("");
                    }
                } catch (JSONException e) {
                    mCallback.onStockListGetFailed("");
                    e.printStackTrace();
                }
            }
        });
    }

    public void searchStock(String keyWord) {
        Map<String, String> map = new HashMap<>();
        if (DifStrMatcher.isNumber(keyWord)) {
            map.put("code", keyWord);
        } else if (DifStrMatcher.isContainChinese(keyWord)) {
            map.put("name", keyWord);
        } else {
            map.put("pinyin", keyWord);
        }

        HttpHandler.getInstance().getI("https://ali-stock.showapi.com/name-to-stockinfo", map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                mCallback.onStockListGetFailed("");
                Logger.i(1, "error:" + e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    JSONObject jb = new JSONObject(response);
                    int code = jb.optInt("showapi_res_code", -1);
                    if (code == 0) {
                        JSONObject jbBody = jb.getJSONObject("showapi_res_body");
                        JSONArray ja = jbBody.getJSONArray("list");
                        List<StockBean> list = new JsonArrayParser<StockBean>().parasToObjects(ja, new JsonArrayParser.JsonObjectParseIt<StockBean>() {
                            @Override
                            public StockBean parasJsonObject(JSONObject jb) {
                                return new StockBean(jb);
                            }
                        });
//                        mCallback.onStockListGetSuccess(list);
                        getInfo(list);
                        Logger.i(1, "list:" + ja.toString());
                    } else {
                        mCallback.onStockListGetFailed("");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    mCallback.onStockListGetFailed("");
                }
            }
        });
    }


    public interface MarketViewCallback {
        void onStockListGetSuccess(List<StockInfoBean> list, StockIndexBean szBean, StockIndexBean shBean);

        void onStockListGetFailed(String msg);
    }
}
