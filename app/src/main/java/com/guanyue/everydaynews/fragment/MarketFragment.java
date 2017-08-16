package com.guanyue.everydaynews.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.generallibrary.utils.Logger;
import com.guanyue.everydaynews.R;
import com.guanyue.everydaynews.adapter.StockAdapter;
import com.guanyue.everydaynews.base.AppBaseV4Fragment;
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
    }

    @Override
    protected void loadData() {
        mPresenter.getStockList();

    }

    @Override
    public void handleMessage(Message msg) {

    }

    private class ViewCallback implements MarketPresenter.MarketViewCallback {

        @Override
        public void onStockListGetSuccess(List<StockInfoBean> list) {
            mStockList.clear();
            mStockList.addAll(list);
            mStockAdapter.notifyItemInserted(0);
        }

        @Override
        public void onStockListGetFailed(String msg) {

        }
    }

}
