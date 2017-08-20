package com.guanyue.everydaynews.data;

import android.text.TextUtils;

import com.generallibrary.utils.DifWorker;
import com.generallibrary.utils.Logger;
import com.guanyue.everydaynews.base.PingApplication;
import com.guanyue.everydaynews.user.DataUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by LiDaChang on 17/7/25.
 * __--__---__-------------__----__
 */

@SuppressWarnings("unchecked")//为了消除警告的掩耳盗铃注解
public class UserHistoryManager {
    private static UserHistoryManager mInstance;

    private static final String FILE_NAME_BROWSER = "file_name_browser";
    private static final String FILE_NAME_SEARCH = "file_name_search";

    private static final int LENGTH_MAX = 100;
    private List<MsgBean> mBrowsHistoryList;


    public static synchronized UserHistoryManager getInstance() {
        if (mInstance == null) {
            mInstance = new UserHistoryManager();
        }
        return mInstance;
    }

    private UserHistoryManager() {
        loadHistory();
    }

    private void loadHistory() {
        mBrowsHistoryList = (List<MsgBean>) DataUtils.deserializationWithSP(PingApplication.getInstance(), FILE_NAME_BROWSER);
        checkList(mBrowsHistoryList, FILE_NAME_BROWSER);
    }

    private void checkList(List list, String name) {
        if (list != null) {
            boolean isOk;
            isOk = removeDuplicateWithOrder(list);
            if (list.size() > LENGTH_MAX) {
                list = cutForMaxLength(list);
                isOk = true;
            }
            if (isOk) {
                DataUtils.serializationWithSP(list, name);
            }
        }
    }

    public void saveBrowsHistory(final MsgBean bean) {
        DifWorker.getInstance().executeTask(new Runnable() {
            @Override
            public void run() {
                if (mBrowsHistoryList != null) {
                } else {
                    mBrowsHistoryList = new ArrayList<>();
                }
                mBrowsHistoryList.add(bean);
                if (mBrowsHistoryList.size() > LENGTH_MAX)
                    mBrowsHistoryList.remove(0);
                Logger.i(1, "mBrowsHistoryList:" + mBrowsHistoryList.toString());
                DataUtils.serializationWithSP(mBrowsHistoryList, FILE_NAME_BROWSER);
            }
        });
    }

    private List cutForMaxLength(List list) {
        List newList = new ArrayList();
        newList.clear();
        for (int i = 0; i < list.size(); i++) {
            if (i < 4) {
                newList.add(list.get(i));
            }
        }
        return newList;
    }

    private List removeDuplicate(List list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = list.size() - 1; j > i; j--) {
                if (list.get(j).equals(list.get(i))) {
                    list.remove(j);
                }
            }
        }
        return list;
    }

    private boolean removeDuplicateWithOrder(List list) {
        if (list == null || list.size() == 0) {
            return false;
        }
        Set set = new HashSet();
        List newList = new ArrayList();
        for (Object element : list) {
            if (set.add(element)) {
                newList.add(element);
            }
        }
        list.clear();
        list.addAll(newList);
        return list.size() != newList.size();
    }

    public List<MsgBean> getBrowsHistoryList() {
        return getListReverse(mBrowsHistoryList);
    }

    private List getListReverse(List list) {
        List history = new ArrayList<>();
        if (list != null) {
            history.addAll(list);
            Collections.reverse(history);
        }
        return history;
    }

    public void clearAll() {
        clearBrowsHistoryList();
    }

    // 清除最近浏览
    public void clearBrowsHistoryList() {
        mBrowsHistoryList = null;
        DataUtils.clear(FILE_NAME_BROWSER);
    }
}
