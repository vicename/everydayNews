package com.guanyue.everydaynews.data;

import org.json.JSONObject;

/**
 * Created by LiDaChang on 17/8/17.
 * __--__---__-------------__----__
 * <p>
 * "appointRate":"	-13.96", //委比，单位%
 * "appointRate":"-658",//委差，单位手
 * "todayMax": "16.160",//今日最高价
 * "highLimit": "17.57",//涨停价
 * "buy5_n": "18100",//买五
 * "buy2_n": "56600",//买二
 * "tradeNum": "3336273",//成交量(股，不是手)
 * "buy2_m": "16.080",//买二报价
 * "buy5_m": "16.050",//买五报价
 * "currcapital": "115641.6852",
 * "sell3_m": "16.120",//卖三报价
 * "openPrice": "15.950",//今日开盘价
 * "buy3_m": "16.070",//买三报价
 * "buy4_m": "16.060",//买四报价
 * "circulation_value": "186.18",//流通市值，亿元
 * "buy4_n": "25000",//买四
 * "date": "2017-04-18",//日期
 * "sell5_n": "19300",//卖五
 * "buy3_n": "35800",//买三
 * "all_value": "186.18",//总市值，亿元
 * "sell5_m": "16.140",//卖五报价
 * "closePrice": "15.970",//昨日收盘价
 * "time": "11:11:43",//刷新时间
 * "turnover": "0.289%",//换手率
 * "sell3_n": "12900",//卖三
 * "name": "白云机场",//上证指数
 * "sell4_n": "9600",//卖四
 * "downLimit": "14.37",//跌停价
 * "sell4_m": "16.130",//卖四报价
 * "tradeAmount": "53647432.000",//成交金额（元）
 * "swing": "1.31",//振幅
 * "totalcapital": "115641.6852", //总股本，万股
 * "diff_rate": "0.81",//涨跌幅度
 * "yestodayClosePrice": "15.970",//昨日收盘价
 * "sell1_n": "67963",//卖一
 * "todayMin": "15.950",//今日最低价
 * "sell1_m": "16.100",//卖一报价
 * "max52": "",//52周最高价
 * "diff_money": "0.13",//涨跌金额
 * "code": "600004",//sh000001
 * "nowPrice": "16.100",//当前价
 * "sell2_m": "16.110",//卖二
 * "min52": "",//52周最低价
 * "sell2_n": "20879",//卖二
 * "buy1_m": "16.090",//买一报价（金额，元）
 * "pe": "13.64",//市盈率(TTM,动态)
 * "buy1_n": "53900",//买一数量（股）
 * "market": "sh",
 * "pb": "1.74"//市净率
 */

public class StockInfoBean {
    public String diff_rate;
    public String nowPrice;
    public String name;
    public String num;

    public StockInfoBean(JSONObject jsonObject) {
        this.diff_rate = jsonObject.optString("diff_rate");
        this.nowPrice = jsonObject.optString("nowPrice");
        this.name = jsonObject.optString("name");
        this.num = jsonObject.optString("code");
    }

    @Override
    public String toString() {
        return "StockInfoBean{" +
                "diff_rate='" + diff_rate + '\'' +
                ", nowPrice='" + nowPrice + '\'' +
                ", name='" + name + '\'' +
                ", num='" + num + '\'' +
                '}';
    }
}


