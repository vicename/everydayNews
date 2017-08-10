package com.guanyue.everydaynews.activity;

import android.content.Context;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.generallibrary.base.DifBaseActivity;
import com.generallibrary.utils.Logger;
import com.guanyue.everydaynews.R;
import com.guanyue.everydaynews.fragment.UserHomeFragment;
import com.guanyue.everydaynews.fragment.WebRelateFragment;

import java.util.ArrayList;
import java.util.List;

import ai.botbrain.ttcloud.sdk.fragment.IndexFragment;

public class MainActivity extends DifBaseActivity {
    private TabsAdapter mTabsAdapter;
    private LinearLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected void initVar() {
        List<TabsAdapter.TabInfo> tabs = new ArrayList<>();
        tabs.add(new TabsAdapter.TabInfo("评论回复", new IndexFragment()));
        tabs.add(new TabsAdapter.TabInfo("点赞通知", WebRelateFragment.newInstance()));
        tabs.add(new TabsAdapter.TabInfo("系统通知", WebRelateFragment.newInstance()));
        tabs.add(new TabsAdapter.TabInfo("用户中心", UserHomeFragment.newInstance()));
        mTabsAdapter = new TabsAdapter(getSupportFragmentManager(), this, tabs);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
        mTabLayout = ((LinearLayout) findViewById(R.id.tabLayout));
        mViewPager = ((ViewPager) findViewById(R.id.viewpager));
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(mTabsAdapter);
        for (int i = 0; i < mTabLayout.getChildCount(); i++) {
            final int finalI = i;
            mTabLayout.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewPager.setCurrentItem(finalI);
                }
            });
        }
    }

    @Override
    protected void initListener() {
    }

    @Override
    protected void loadData() {
    }

    @Override
    public void handleMessage(Message msg) {

    }

    private static class TabsAdapter extends FragmentPagerAdapter {
        private final List<TabInfo> mTabs;

        TabsAdapter(FragmentManager fm, Context mContext, List<TabInfo> mTabs) {
            super(fm);
            this.mTabs = mTabs;
        }

        @Override
        public int getCount() {
            return mTabs.size();
        }

        @Override
        public Fragment getItem(int position) {
            return mTabs.get(position).fragment;
        }

        static final class TabInfo {
            public String title;
            public Fragment fragment;

            TabInfo(String title, Fragment fragment) {
                this.title = title;
                this.fragment = fragment;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabs.get(position).title;
        }
    }
}
