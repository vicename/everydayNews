package com.guanyue.everydaynews.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.generallibrary.utils.Logger;
import com.guanyue.everydaynews.R;
import com.guanyue.everydaynews.adapter.StockAdapter;
import com.guanyue.everydaynews.base.AppBaseV4Fragment;
import com.guanyue.everydaynews.data.StockIndexBean;
import com.guanyue.everydaynews.data.StockInfoBean;
import com.guanyue.everydaynews.presenter.MarketPresenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by LiDaChang on 17/5/31.
 * __--__---__-------------__----__
 */

public class MarketFragment extends AppBaseV4Fragment {

    private MarketPresenter mPresenter;
    private RecyclerView mRvStock;
    private StockAdapter mStockAdapter;
    private List<StockInfoBean> mStockList;
    private View mShBox;
    private View mSzBox;
    private View mViewSortReverse;
    private boolean mIsRateReverse;
    private View mTvPriceSort;
    private boolean mIsPriceReverse;
    private ImageView mIvRate;

    public static MarketFragment newInstance() {
        MarketFragment fragment = new MarketFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_market;
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
        mPresenter = MarketPresenter.create(mContext, new ViewCallback());
        mStockList = new ArrayList<>();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mRvStock = ((RecyclerView) mView.findViewById(R.id.rv_stock));
        mRvStock.setLayoutManager(new LinearLayoutManager(getActivity()));
        mStockAdapter = new StockAdapter(mContext, mStockList);
        mRvStock.setAdapter(mStockAdapter);
        mShBox = mView.findViewById(R.id.l_layout_sh_box);
        mSzBox = mView.findViewById(R.id.l_layout_sz_box);
        mViewSortReverse = mView.findViewById(R.id.tv_rate_guid);
        mViewSortReverse.setOnClickListener(new ClickReversSort());
        mTvPriceSort = mView.findViewById(R.id.tv_price_sort);
        mTvPriceSort.setOnClickListener(new ClickPriceSort());
        mIvRate = ((ImageView) mView.findViewById(R.id.iv_list_rate));
    }

    @Override
    protected void loadData() {
        mPresenter.getStockList();

    }

    @Override
    public void handleMessage(Message msg) {

    }

    public void refresh() {
        loadData();
    }

    private class SortRateComparator implements Comparator<StockInfoBean> {

        @Override
        public int compare(StockInfoBean o1, StockInfoBean o2) {
            float f1 = Float.parseFloat(o1.diff_rate) * 100;
            float f2 = Float.parseFloat(o2.diff_rate) * 100;
            return (int) (f2 - f1);
        }
    }

    private class SortRateReverseComparator implements Comparator<StockInfoBean> {

        @Override
        public int compare(StockInfoBean o1, StockInfoBean o2) {
            float f1 = Float.parseFloat(o1.diff_rate) * 100;
            float f2 = Float.parseFloat(o2.diff_rate) * 100;
            return (int) (f1 - f2);
        }
    }

    private class SortPriceComparator implements Comparator<StockInfoBean> {

        @Override
        public int compare(StockInfoBean o1, StockInfoBean o2) {
            float f1 = Float.parseFloat(o1.nowPrice) * 100;
            float f2 = Float.parseFloat(o2.nowPrice) * 100;
            return (int) (f2 - f1);
        }
    }

    private class SortPriceReverseComparator implements Comparator<StockInfoBean> {

        @Override
        public int compare(StockInfoBean o1, StockInfoBean o2) {
            float f1 = Float.parseFloat(o1.nowPrice) * 100;
            float f2 = Float.parseFloat(o2.nowPrice) * 100;
            return (int) (f1 - f2);
        }
    }

    public void setDD(View view, StockIndexBean stockIndexBean) {
        float rate = Float.parseFloat(stockIndexBean.diff_rate);
        int color;
        ImageView ivRate = (ImageView) view.findViewById(R.id.iv_info_rate_arrow);
        if (rate < 0) {
            color = ContextCompat.getColor(mContext, R.color.color_rate_down);
            ivRate.setImageResource(R.drawable.s2);
            ivRate.setRotation(180);
        } else if (rate > 0) {
            ivRate.setImageResource(R.drawable.s1);
            ivRate.setRotation(0);
            color = ContextCompat.getColor(mContext, R.color.color_rate_up);
            stockIndexBean.diff_rate = "+" + stockIndexBean.diff_rate;
            stockIndexBean.diff_money = "+" + stockIndexBean.diff_money;
        } else {
            color = ContextCompat.getColor(mContext, R.color.text_secondary);
        }
        ((TextView) view.findViewById(R.id.tv_info_dot)).setText(stockIndexBean.nowPrice);
        ((TextView) view.findViewById(R.id.tv_info_dot)).setTextColor(color);
        ((TextView) view.findViewById(R.id.tv_info_price)).setText(stockIndexBean.diff_money);
        ((TextView) view.findViewById(R.id.tv_info_price)).setTextColor(color);
        ((TextView) view.findViewById(R.id.tv_info_rate)).setText(stockIndexBean.diff_rate);
        ((TextView) view.findViewById(R.id.tv_info_rate)).setTextColor(color);
    }

    private void sortRateGo() {
        Comparator<StockInfoBean> sss;
        if (!mIsRateReverse) {
            sss = new SortRateComparator();
        } else {
            sss = new SortRateReverseComparator();
        }
        Collections.sort(mStockList, sss);
        mIsRateReverse = !mIsRateReverse;
        if (mIsRateReverse) {
            mIvRate.setRotation(0);
        } else {
            mIvRate.setRotation(180);
        }
    }

    private void sortPriceGo() {
        Comparator<StockInfoBean> sss;
        if (!mIsPriceReverse) {
            sss = new SortPriceComparator();
        } else {
            sss = new SortPriceReverseComparator();
        }
        Collections.sort(mStockList, sss);
        mIsPriceReverse = !mIsPriceReverse;
    }

    private class ClickPriceSort implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            sortPriceGo();
            mStockAdapter.notifyDataSetChanged();
        }
    }

    private class ClickReversSort implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            sortRateGo();
            mStockAdapter.notifyDataSetChanged();
        }

    }

    private class ViewCallback implements MarketPresenter.MarketViewCallback {

        @Override
        public void onStockListGetSuccess(List<StockInfoBean> list, StockIndexBean szBean, StockIndexBean shBean) {
            mStockList.clear();
            mStockList.addAll(list);
            sortRateGo();
            mStockAdapter.notifyItemInserted(0);
            setDD(mShBox, shBean);
            setDD(mSzBox, szBean);
        }

        @Override
        public void onStockListGetFailed(String msg) {

        }

    }
}
