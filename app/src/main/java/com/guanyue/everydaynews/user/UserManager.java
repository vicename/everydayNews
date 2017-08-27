package com.guanyue.everydaynews.user;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.generallibrary.utils.Logger;
import com.generallibrary.utils.SPUtils;
import com.guanyue.everydaynews.base.PingApplication;

import java.util.ArrayList;
import java.util.List;

import ai.botbrain.ttcloud.api.TtCloudListener;
import ai.botbrain.ttcloud.api.TtCloudManager;

/**
 * Created by LiDaChang on 16/11/24.
 * __--__---__-------------__----__
 */

public class UserManager {

    private SPUtils mSPUtils;
    private UserBean mUserBean;
    private static UserManager mInstance;
    private List<IUserChangedObserver> mObservers;

    private static final String SER_FILE_NAME = "login_user";
    private static final String SP_ACCOUNT_INFO = "account_info";

    private static final String KEY_IS_LOGIN = "key_is_login";

    private static final String KEY_EM_ACCOUNT = "key_em_account";
    private Updater mUpdater;
    private String mEmAccount;

    public static synchronized UserManager getInstance() {
        if (mInstance == null) {
            mInstance = new UserManager();
        }
        return mInstance;
    }

    public static synchronized UserManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new UserManager();
        }
        return mInstance;
    }

    private UserManager() {
        Logger.i(1, "isNull:" + (PingApplication.getInstance() == null));
        mSPUtils = new SPUtils(PingApplication.getInstance(), SP_ACCOUNT_INFO);
        loadUser();
    }

    public void saveUser(@NonNull UserBean bean) {
        if (mUserBean != null) {
            Logger.w("---feng", "用户信息已经存在,虽然我帮你存上了,但你最好查查哪里除了问题.因为原则上来说我这里并不允许反复保存");
        }
        boolean isOk = DataUtils.serializationWithSP(bean, SER_FILE_NAME);
        if (isOk) {
            Logger.i(1, "存储成功!");
            sendMsgUserInfoUpdate(bean);
            return;
        }
        Logger.i(1, "存储失败");
    }

    /**
     * 更新用户信息
     *
     * @param userBean 用户实体类
     * @return 是否成功
     */
    private boolean updateInfo(@NonNull UserBean userBean) {
        mUserBean = userBean;
        if (DataUtils.serializationWithSP(userBean, SER_FILE_NAME)) {
            sendMsgUserInfoUpdate(userBean);
            return true;
        }
        return false;
    }

    public Updater update() {
        if (mUserBean == null) {
            Logger.e("---feng", "用户信息为空,不允许update");
        }
        if (mUpdater == null) {
            mUpdater = new Updater();
        }
        return mUpdater;
    }

    /**
     * 获取当前登录用户信息
     *
     * @return 当前登录用户信息
     */
    public UserBean getUser() {
        loadUser();
        if (mUserBean == null) {
            return new UserBean();
        }
        return mUserBean;
    }

    private void loadUser() {
        if (!isLogin()) {
            return;
        }
        if (mUserBean == null) {
            mUserBean = (UserBean) DataUtils.deserializationWithSP(PingApplication.getInstance(), SER_FILE_NAME);
        }
    }

    public void clearUser() {
        mUserBean = null;
        DataUtils.clear(SER_FILE_NAME);
    }

    public void setIsLogin(boolean isLogin) {
        if (!isLogin) {
            clearUser();
            TtCloudManager.logout();
        } else {
            TtCloudListener.User user = new TtCloudListener.User();
            UserBean userBean = UserManager.getInstance().getUser();
            user.setUserId(userBean.getUserId());
            user.setUserName(userBean.getNickname());
            user.setUserAvatar(userBean.getPhoto());
            user.setUserNickName(userBean.getNickname());
            TtCloudManager.login(user);
        }
        mSPUtils.put(KEY_IS_LOGIN, isLogin);
        Logger.i(1, "login!");
        for (IUserChangedObserver mObserver : mObservers) {
            if (isLogin) {
                mObserver.onUserLogin();
            } else {
                mObserver.onUserLogout();
            }
        }
    }

    /**
     * 判断是否登录
     *
     * @return 是否登录
     */
    public boolean isLogin() {
        return mSPUtils.getBoolean(KEY_IS_LOGIN, false);
    }

    public void putEmAccount(@NonNull String account) {
        mEmAccount = account;
        mSPUtils.put(KEY_EM_ACCOUNT, account);
    }

    /**
     * 注册用户信息变更监听
     *
     * @param observer 要注册的监听器
     */
    public void registerObserver(IUserChangedObserver observer) {
        if (mObservers == null) {
            mObservers = new ArrayList<>();
        }

        mObservers.add(observer);
    }

    /**
     * 注销用户信息变更监听
     *
     * @param observer 要注销的监听器
     */
    public void unregisterObserver(IUserChangedObserver observer) {
        if (mObservers != null && mObservers.contains(observer)) {
            mObservers.remove(observer);
        }
    }

    private String checkSexual(int flag) {
        switch (flag) {
            case 0:
                return "女";
            case 1:
                return "男";
            default:
                return "";
        }
    }

    /**
     * 传入userId,判断是否是用户本人
     *
     * @param userId userId
     * @return 是否
     */
    public boolean isUserSelf(String userId) {
        return TextUtils.equals(userId, mUserBean.getId());
    }


    public String getEMAccount() {
        if (isLogin()) {
            return getUser().getId();
        }
        if (mEmAccount != null) {
            return mEmAccount;
        }
        return mSPUtils.getString(KEY_EM_ACCOUNT, "");

    }

    public static class Updater {
        UserBean userBean;

        private Updater() {
            this.userBean = UserManager.getInstance().getUser();
            if (userBean == null) {
                userBean = new UserBean();
            }
        }

        public Updater nickname(String nickname) {
            userBean.setNickname(nickname);
            return this;
        }

        public Updater id(String id) {
            userBean.setId(id);
            return this;
        }

        public Updater phoneNum(String phone) {
            userBean.setPhone(phone);
            return this;
        }

        public Updater photo(String photo) {
            userBean.setPhoto(photo);
            return this;
        }

        public Updater sign(String sign) {
            userBean.setSign(sign);
            return this;
        }

        public Updater isHavePwd(boolean isHave) {
            userBean.setIsHasPassword(isHave);
            return this;
        }

        public void apply() {
            UserManager.getInstance().updateInfo(userBean);
        }
    }

    private void sendMsgUserInfoUpdate(UserBean userBean) {
        if (mObservers != null) {
            for (IUserChangedObserver mObserver : mObservers) {
                mObserver.onUserInfoUpdate(userBean);
            }
        }
    }


    /**
     * 用户信息变更监听
     */
    public interface IUserChangedObserver {
        void onUserLogin();

        void onUserInfoUpdate(UserBean user);

        void onUserLogout();
    }
}
