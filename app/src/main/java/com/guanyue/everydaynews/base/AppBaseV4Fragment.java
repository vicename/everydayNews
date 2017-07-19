package com.guanyue.everydaynews.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.generallibrary.utils.WeakHandler;

/**
 * Created by LiDaChang on 17/3/9.
 * __--__---__-------------__----__
 */

public abstract class AppBaseV4Fragment extends Fragment implements WeakHandler.IHandler {
    protected View mView;
    protected WeakHandler mHandler;
    protected Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initArguments(savedInstanceState);
        mHandler = new WeakHandler(this);
        mContext = getActivity();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initVar(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (null == mView) {
            mView = inflater.inflate(getLayoutId(), container, false);
        }
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView(savedInstanceState);
        loadData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if ((isVisibleToUser && isResumed())) {
            if (getUserVisibleHint()) {
                onVisibilityChange(true);
            }
        } else if (!isVisibleToUser) {
            onVisibilityChange(false);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint()) {
            onVisibilityChange(true);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        onVisibilityChange(false);
    }

    protected abstract int getLayoutId();

    protected abstract void onVisibilityChange(boolean isShown);

    protected abstract void initArguments(@Nullable Bundle savedInstanceState);

    protected abstract void initVar(Context context);

    protected abstract void initView(Bundle savedInstanceState);

    protected abstract void loadData();

}
