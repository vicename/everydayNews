package com.guanyue.everydaynews.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.generallibrary.utils.Logger;
import com.guanyue.everydaynews.R;
import com.guanyue.everydaynews.adapter.StockAdapter;
import com.guanyue.everydaynews.base.AppBaseV4Fragment;
import com.guanyue.everydaynews.data.StockIndexBean;
import com.guanyue.everydaynews.data.StockInfoBean;
import com.guanyue.everydaynews.presenter.MarketPresenter;

import java.util.ArrayList;
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

    public void setDD(View view, StockIndexBean stockIndexBean) {
        float rate = Float.parseFloat(stockIndexBean.diff_rate);
        int color;
        if (rate < 0) {
            color = ContextCompat.getColor(mContext, R.color.color_rate_down);
        } else if (rate > 0) {
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

    private class ViewCallback implements MarketPresenter.MarketViewCallback {

        @Override
        public void onStockListGetSuccess(List<StockInfoBean> list, StockIndexBean szBean, StockIndexBean shBean) {
            mStockList.clear();
            mStockList.addAll(list);
            mStockAdapter.notifyItemInserted(0);
            setDD(mShBox, shBean);
            setDD(mSzBox, szBean);
        }

        @Override
        public void onStockListGetFailed(String msg) {

        }
    }

}
