package com.guanyue.everydaynews.activity;

import android.content.Context;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.generallibrary.base.DifBaseActivity;
import com.generallibrary.utils.Logger;
import com.guanyue.everydaynews.R;
import com.guanyue.everydaynews.fragment.UserHomeFragment;
import com.guanyue.everydaynews.fragment.MarketFragment;
import com.guanyue.everydaynews.presenter.MainPresenter;
import com.guanyue.everydaynews.user.UserBean;
import com.guanyue.everydaynews.user.UserManager;
import com.guanyue.everydaynews.widget.PwMainTitleBar;

import java.util.ArrayList;
import java.util.List;

import ai.botbrain.ttcloud.sdk.fragment.IndexFragment;

public class MainActivity extends DifBaseActivity implements UserManager.IUserChangedObserver {
    private TabsAdapter mTabsAdapter;
    private LinearLayout mTabLayout;
    private ViewPager mViewPager;
    private PwMainTitleBar mTitleBar;
    private MainPresenter mPresenter;

    @Override
    protected void initVar() {
        List<TabsAdapter.TabInfo> tabs = new ArrayList<>();
        tabs.add(new TabsAdapter.TabInfo("每日新闻资讯", 0, new IndexFragment()));
        tabs.add(new TabsAdapter.TabInfo("股市行情", 2, MarketFragment.newInstance()));
        tabs.add(new TabsAdapter.TabInfo("我的", 1, UserHomeFragment.newInstance()));
        mTabsAdapter = new TabsAdapter(getSupportFragmentManager(), this, tabs);
        UserManager.getInstance().registerObserver(this);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
        mTitleBar = ((PwMainTitleBar) findViewById(R.id.titleBar));
        mTitleBar.disableBottomLine();
        mTabLayout = ((LinearLayout) findViewById(R.id.tabLayout));
        checkTitleBar(0);
        checkTab(0);
        mViewPager = ((ViewPager) findViewById(R.id.viewpager));
        mViewPager.setOffscreenPageLimit(2);
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
        mViewPager.addOnPageChangeListener(new PageListener());
        mPresenter = MainPresenter.create(mContext, new ViewCallback());
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

    @Override
    public void onUserLogin() {

    }

    @Override
    public void onUserInfoUpdate(UserBean user) {

    }

    @Override
    public void onUserLogout() {

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

        public TabInfo getTabInfo(int position) {
            return mTabs.get(position);
        }

        static final class TabInfo {
            String title;
            Fragment fragment;
            int itemType;

            TabInfo(String title, int itemType, Fragment fragment) {
                this.title = title;
                this.fragment = fragment;
                this.itemType = itemType;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabs.get(position).title;
        }

    }

    private void checkTab(int index) {
        if (mTabLayout != null) {
            for (int i = 0; i < 3; i++) {
                mTabLayout.getChildAt(i).setSelected(false);
            }
            mTabLayout.getChildAt(index).setSelected(true);
        }
    }

    private void checkTitleBar(int index) {
        final TabsAdapter.TabInfo info = mTabsAdapter.getTabInfo(index);
        mTitleBar.setTitle(info.title);
        switch (info.itemType) {
            case 0:
                mTitleBar.removeItem2();
                mTitleBar.setItemRes(R.drawable.ic_search);
                Logger.i(202);
                mTitleBar.setOnItemClickListener(new ClickTitleItemSearch());
                break;
            case 1:
                mTitleBar.removeItem2();
                mTitleBar.setItemText("退出");
                Logger.i(20);
                mTitleBar.setOnItemClickListener(new ClickTitleItemLogout());
                break;
            case 2:
                mTitleBar.setItemRes(R.drawable.ic_search);
                mTitleBar.setItem2();
                mTitleBar.setOnItemClickListener(new ClickTitleItemSearch2());
                mTitleBar.setOnItem2ClickListener(new ClickRefresh());
                break;
        }
    }

    private class PageListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            checkTitleBar(position);
            checkTab(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    private class ClickTitleItemSearch implements PwMainTitleBar.OnMenuItemClickPwListener {

        @Override
        public void onCLick(View view) {

        }
    }

    private class ClickTitleItemSearch2 implements PwMainTitleBar.OnMenuItemClickPwListener {

        @Override
        public void onCLick(View view) {

        }
    }

    private class ClickRefresh implements PwMainTitleBar.OnMenuItem2ClickPwListener {

        @Override
        public void onClick(View view) {

        }
    }

    private class ClickTitleItemLogout implements PwMainTitleBar.OnMenuItemClickPwListener {

        @Override
        public void onCLick(View view) {
            Logger.i(1, "toLogOut!");
            UserManager.getInstance().setIsLogin(false);
        }
    }

    private class ViewCallback implements MainPresenter.MainPageViewCallback {

        @Override
        public void onLoginSuccess(UserBean userInfo) {

        }

        @Override
        public void onLoginFailed(String msg) {

        }

        @Override
        public void onPwdSetSuccess() {

        }

        @Override
        public void onPwdSetFailed(String msg) {

        }
    }
}
