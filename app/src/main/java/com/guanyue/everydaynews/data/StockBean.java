package com.guanyue.everydaynews.data;

import org.json.JSONObject;

/**
 * Created by LiDaChang on 17/8/17.
 * __--__---__-------------__----__
 * <p>
 * "market": "sh",
 * "name": "博迈科",
 * "currcapital": "5870",  //流通股本，万股
 * "profit_four": "1.14",  //四季度净利润（亿元）
 * "listing_date": "2015-01-22",  //上市日期
 * "code": "603727",
 * "totalcapital": "23414.5",//总股本，万股
 * "mgjzc": "6.329666",      //每股净资产（元）
 * "pinyin": "bmk"           //拼音
 */

public class StockBean {
    public String market;
    public String name;
    public String currcapital;
    public String profit_four;
    public String listing_date;
    public String code;
    public String totalcapital;
    public String mgjzc;
    public String pinyin;

    public StockBean() {
    }

    public StockBean(JSONObject jsonObject) {
        this.market = jsonObject.optString("market");
        this.name = jsonObject.optString("name");
        this.currcapital = jsonObject.optString("currcapital");
        this.profit_four = jsonObject.optString("profit_four");
        this.listing_date = jsonObject.optString("listing_date");
        this.code = jsonObject.optString("code");
        this.totalcapital = jsonObject.optString("totalcapital");
        this.mgjzc = jsonObject.optString("mgjzc");
        this.pinyin = jsonObject.optString("pinyin");
    }

    @Override
    public String toString() {
        return "StockBean{" +
                "market='" + market + '\'' +
                ", name='" + name + '\'' +
                ", currcapital='" + currcapital + '\'' +
                ", profit_four='" + profit_four + '\'' +
                ", listing_date='" + listing_date + '\'' +
                ", code='" + code + '\'' +
                ", totalcapital='" + totalcapital + '\'' +
                ", mgjzc='" + mgjzc + '\'' +
                ", pinyin='" + pinyin + '\'' +
                '}';
    }
}
