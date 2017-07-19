package com.guanyue.everydaynews.activity;

import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.generallibrary.base.LibBaseActivity;
import com.generallibrary.utils.Logger;
import com.guanyue.everydaynews.R;

import ai.botbrain.ttcloud.sdk.fragment.IndexFragment;

public class MainActivity extends LibBaseActivity {
    private IndexFragment mFg;

    @Override
    protected void initVar() {

    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
        mFg = new IndexFragment();
        Logger.i(22);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {
        openNews();
    }

    private void openNews() {
        Logger.i(21);
        if (!mFg.isAdded()) {
            Logger.i(23);
            FragmentManager mFm = getSupportFragmentManager();
            FragmentTransaction mFt = mFm.beginTransaction();
            Logger.i(24);
            mFt.add(R.id.root221, mFg);
            Logger.i(25);
            mFt.commit();
            Logger.i(26);
        }
    }

    @Override
    public void handleMessage(Message msg) {

    }
}
