package com.guanyue.everydaynews.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;

import com.generallibrary.utils.Logger;
import com.guanyue.everydaynews.R;
import com.guanyue.everydaynews.base.AppBaseV4Fragment;

/**
 * Created by LiDaChang on 17/5/31.
 * __--__---__-------------__----__
 */

public class WebRelateFragment extends AppBaseV4Fragment {

    public static WebRelateFragment newInstance() {
        WebRelateFragment fragment = new WebRelateFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main_page;
    }

    @Override
    protected void onVisibilityChange(boolean isShown) {
        Logger.i(1, "change:" + isShown);
    }

    @Override
    protected void initArguments(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected void initVar(Context context) {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void loadData() {

    }

    @Override
    public void handleMessage(Message msg) {

    }

}
