package com.guanyue.everydaynews.data;

import com.generallibrary.utils.Logger;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiDaChang on 17/4/25.
 * __--__---__-------------__----__
 */

public class JsonArrayParser<T> {

    public JsonArrayParser() {
    }

    public List<String> parasToStringList(JSONArray jsonArray) {
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            String str = jsonArray.optString(i);
            stringList.add(str);
        }
        return stringList;
    }

    public List<T> parasToObjects(JSONArray jsonArray, JsonObjectParseIt<T> callback) {
        List<T> tArrayList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jb = jsonArray.optJSONObject(i);
            if (jb != null) {
                tArrayList.add(callback.parasJsonObject(jb));
            } else {
                Logger.e("---feng---", "数据解析错误!");
            }
        }
        return tArrayList;
    }

    public interface JsonObjectParseIt<T> {
        T parasJsonObject(JSONObject jb);
    }
}
