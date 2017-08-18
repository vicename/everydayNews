package com.guanyue.everydaynews.data;

import org.json.JSONObject;

/**
 * Created by LiDaChang on 17/8/18.
 * __--__---__-------------__----__
 */
/*
        "yestodayClosePrice": "3222.1673",//昨日收盘点数
        "max52": "0",//52周最大点数
        "diff_money": "-3.2288",//涨跌点数
        "tradeNum": "89917531",//成交量(手)
        "code": "sh000001",
        "maxPrice": "3225.0546",//今日最高点数
        "nowPrice": "3218.9385",//当前点数
        "min52": "0",//52周最低点数
        "time": "2017-04-18 11:11:42",//时间
        "name": "上证指数",//股票名称
        "tradeAmount": "102068196850",//成交金额（金额，元）
        "swing": "0.3923",//振幅 %
        "todayOpenPrice": "3215.3963", //今日开盘点数
        "diff_rate": "-0.1002",//涨跌幅度%
        "minPrice": "3212.4147" //今日最低点数
                */
public class StockIndexBean {
    public String name;
    public String diff_money;
    public String nowPrice;
    public String diff_rate;

    public StockIndexBean(JSONObject jsonObject) {
        name = jsonObject.optString("name");
        diff_money = jsonObject.optString("diff_money");
        nowPrice = jsonObject.optString("nowPrice");
        diff_rate = jsonObject.optString("diff_rate");
    }
}
