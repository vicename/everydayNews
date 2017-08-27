package com.guanyue.everydaynews.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.generallibrary.utils.Logger;
import com.generallibrary.utils.ToastUtils;
import com.guanyue.everydaynews.R;
import com.guanyue.everydaynews.activity.AboutActivity;
import com.guanyue.everydaynews.activity.ConnectUsActivity;
import com.guanyue.everydaynews.activity.LoginActivity;
import com.guanyue.everydaynews.activity.ReportActivity;
import com.guanyue.everydaynews.activity.UserMsgActivity;
import com.guanyue.everydaynews.base.AppBaseV4Fragment;
import com.guanyue.everydaynews.base.PingApplication;
import com.guanyue.everydaynews.handler.CleanCacheManager;
import com.guanyue.everydaynews.handler.FileUtils;
import com.guanyue.everydaynews.handler.ThirdLoginHandler;
import com.guanyue.everydaynews.user.UserBean;
import com.guanyue.everydaynews.user.UserManager;
import com.guanyue.everydaynews.widget.PwMainTitleBar;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

/**
 * Created by LiDaChang on 17/8/10.
 * __--__---__-------------__----__
 */

public class UserHomeFragment extends AppBaseV4Fragment implements UserManager.IUserChangedObserver {

    private View mViewLogin;

    public static Fragment newInstance() {
        UserHomeFragment fragment = new UserHomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user_home;
    }

    @Override
    protected void onVisibilityChange(boolean isShown) {

    }

    @Override
    protected void initArguments(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected void initVar(Context context) {
        UserManager.getInstance().registerObserver(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        UserManager.getInstance().unregisterObserver(this);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mViewLogin = LayoutInflater.from(mContext).inflate(R.layout.layout_user_home_login, (ViewGroup) mView, false);
        ((ViewGroup) mView).removeAllViews();
        ((ViewGroup) mView).addView(mViewLogin);
        initLoginView();
    }

    private void initLoginView() {
        ImageView ivHead = ((ImageView) mViewLogin.findViewById(R.id.iv_user_head));
        TextView tvName = ((TextView) mViewLogin.findViewById(R.id.tv_user_name));
        if (UserManager.getInstance().isLogin()) {
            String name = UserManager.getInstance().getUser().getNickname();
            tvName.setText(name);
            String url = UserManager.getInstance().getUser().getPhoto();
            if ("http://cn.bing.com/s/cn/cn_logo_serp.png".equals(url) | TextUtils.isEmpty(url)) {
                ivHead.setImageResource(R.drawable.settings_icon_account);
            } else {
                Glide.with(PingApplication.getInstance())
                        .load(UserManager.getInstance().getUser().getPhoto())
                        .into(ivHead);
            }
            ivHead.setOnClickListener(null);
        } else {
            tvName.setText("点击登录");
            ivHead.setImageResource(R.drawable.settings_icon_account);
            ivHead.setOnClickListener(new ItemClickListener());
        }
        setItemInfo(mViewLogin.findViewById(R.id.item_user_home_msg), "我的消息", R.drawable.ic_item_msg);
        setItemInfo(mViewLogin.findViewById(R.id.item_user_home_report), "留言反馈", R.drawable.ic_item_report);
        setItemInfo(mViewLogin.findViewById(R.id.item_user_home_clean), "清理缓存", R.drawable.ic_item_clean);
        setItemInfo(mViewLogin.findViewById(R.id.item_user_home_about), "本站声明", R.drawable.ic_item_about);
        setItemInfo(mViewLogin.findViewById(R.id.item_user_home_connect_us), "联系我们", R.drawable.ic_item_connect_us);
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void handleMessage(Message msg) {

    }


    private void setItemInfo(View view, String title, int res) {
        ((TextView) view.findViewById(R.id.tv_title_user)).setText(title);
        ((ImageView) view.findViewById(R.id.iv_ic_item_user)).setImageResource(res);
        view.setOnClickListener(new ItemClickListener());
    }

    private void getCacheSize() {

    }

    private void clearCache() {
        FileUtils.deleteFolderFile(getActivity().getExternalCacheDir().getPath(), true);
        FileUtils.deleteFolderFile(getActivity().getCacheDir().getPath(), true);
//        FileUtils.cleanInternalCache(SettingActivity.this);
//        mCache.setText("0.0B");
        ToastUtils.showToast(mContext, "清理完毕");
    }

    @Override
    public void onUserLogin() {
        Logger.i(1, "login!");
        initLoginView();
    }

    @Override
    public void onUserInfoUpdate(UserBean user) {

    }

    @Override
    public void onUserLogout() {
        Logger.i(1, "userLogOut!");
        ToastUtils.showToast(PingApplication.getInstance(), "退出登录成功");
        initLoginView();
    }

    private class ItemClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.iv_user_head:
                    if (!UserManager.getInstance().isLogin()) {
                        startActivity(new Intent(mContext, LoginActivity.class));
//                        ToastUtils.showToast(mContext, "登录功能马上上线,请期待");
                    }
                    break;
                case R.id.item_user_home_msg:
                    startActivity(new Intent(mContext, UserMsgActivity.class));
                    break;
                case R.id.item_user_home_report:
                    startActivity(new Intent(mContext, ReportActivity.class));
                    break;
                case R.id.item_user_home_clean:
                    clearCache();
                    break;
                case R.id.item_user_home_about:
                    startActivity(new Intent(mContext, AboutActivity.class));
                    break;
                case R.id.item_user_home_connect_us:
                    startActivity(new Intent(mContext, ConnectUsActivity.class));
                    break;
            }
        }
    }
}
