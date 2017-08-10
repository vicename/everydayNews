package com.guanyue.everydaynews.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.generallibrary.utils.Logger;
import com.guanyue.everydaynews.R;
import com.guanyue.everydaynews.base.AppBaseV4Fragment;
import com.guanyue.everydaynews.user.UserManager;

/**
 * Created by LiDaChang on 17/8/10.
 * __--__---__-------------__----__
 */

public class UserHomeFragment extends AppBaseV4Fragment {

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
        String name = UserManager.getInstance().getUser().getNickname();
        tvName.setText(name);
        setItemInfo(mViewLogin.findViewById(R.id.item_user_home_msg), "我的消息", R.drawable.ad_close);
        setItemInfo(mViewLogin.findViewById(R.id.item_user_home_report), "留言反馈", R.drawable.ad_close);
        setItemInfo(mViewLogin.findViewById(R.id.item_user_home_clean), "清理缓存", R.drawable.ad_close);
        setItemInfo(mViewLogin.findViewById(R.id.item_user_home_about), "本站声明", R.drawable.ad_close);
        setItemInfo(mViewLogin.findViewById(R.id.item_user_home_connect_us), "联系我们", R.drawable.ad_close);
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

    private class ItemClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.item_user_home_msg:
                    break;
                case R.id.item_user_home_report:
                    break;
                case R.id.item_user_home_clean:
                    break;
                case R.id.item_user_home_about:
                    break;
                case R.id.item_user_home_connect_us:
                    break;
            }
        }
    }
}
