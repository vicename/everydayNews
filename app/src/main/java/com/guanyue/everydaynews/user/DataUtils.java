package com.guanyue.everydaynews.user;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;

import com.generallibrary.utils.Logger;
import com.generallibrary.utils.SPUtils;
import com.guanyue.everydaynews.base.PingApplication;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by LiDaChang on 17/5/22.
 * __--__---__-------------__----__
 */

public class DataUtils {

    /**
     * 序列化到本地
     *
     * @param bean     要序列化的对象
     * @param fileName 文件名
     * @return 是否成功
     */
    public static boolean serializationWithSP(Object bean, String fileName) {
        byte[] bytes;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(bean);
            oos.flush();
            bytes = bos.toByteArray();
            SharedPreferences sharedPreferences = PingApplication.getInstance().getSharedPreferences(fileName, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            String encodeToString = Base64.encodeToString(bytes, Base64.DEFAULT);
            editor.putString(fileName, encodeToString);
            editor.apply();
            oos.close();
            bos.close();
        } catch (IOException ex) {
            Logger.e(1, "序列化失败:" + ex.toString());
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public static void clear(String fileName) {
        SharedPreferences sharedPreferences = PingApplication.getInstance().getSharedPreferences(fileName, Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().apply();
    }

    public static void remove(String fileName) {
        SharedPreferences sharedPreferences = PingApplication.getInstance().getSharedPreferences(fileName, Context.MODE_PRIVATE);
        sharedPreferences.edit().remove(fileName).apply();
    }

    public static Object deserializationWithSP(Context context, String fileName) {
        ObjectInputStream ois;
        Object object = null;
        String bb = new SPUtils(context, fileName).getString(fileName, "");
        if (!TextUtils.isEmpty(bb)) {
            byte[] bytes = Base64.decode(bb.getBytes(), Base64.DEFAULT);
            try {
                ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
                object = ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                Logger.w("反对象序列化失败！e = " + e.toString());
                e.printStackTrace();
            }
        }
        return object;
    }
}
