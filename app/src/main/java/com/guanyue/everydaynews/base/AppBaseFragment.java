package com.guanyue.everydaynews.base;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by LiDaChang on 17/4/25.
 * __--__---__-------------__----__
 */

public abstract class AppBaseFragment extends Fragment {
    protected View mView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initArguments(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (null == mView) {
            if (container != null) {
                container.removeAllViews();
            }
            mView = inflater.inflate(getLayoutId(), container, false);
        }
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initVar(getActivity());
        initView(savedInstanceState);
        loadData();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if ((isVisibleToUser && isResumed())) {
            onResume();
        } else if (!isVisibleToUser) {
            onPause();
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
