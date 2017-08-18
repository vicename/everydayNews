package com.guanyue.everydaynews.activity;

import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.generallibrary.utils.Logger;
import com.guanyue.everydaynews.R;
import com.guanyue.everydaynews.adapter.SearchResultAdapter;
import com.guanyue.everydaynews.data.StockIndexBean;
import com.guanyue.everydaynews.data.StockInfoBean;
import com.guanyue.everydaynews.presenter.MarketPresenter;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppBaseActivity {

    private EditText mEdtSearch;
    private View mViewSearch;
    private RecyclerView mRvResult;
    List<StockInfoBean> mStockList;
    private SearchResultAdapter mAdapter;
    private MarketPresenter mMarketPresenter;

    @Override
    protected void initVar() {
        mStockList = new ArrayList<>();
        mMarketPresenter = MarketPresenter.create(mContext, new SearchViewCallback());
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_search);
        initSnackBar(R.id.root);
        mEdtSearch = ((EditText) findViewById(R.id.edt_search));
        mViewSearch = findViewById(R.id.iv_search);
//        Snackbar.make()
        mViewSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyWord = mEdtSearch.getText().toString();
                if (TextUtils.isEmpty(keyWord)) {
                    toastGo("搜索内容不能为空");
                    return;
                }
                mMarketPresenter.searchStock(keyWord);
                showProgress("搜索中...");
            }
        });
        mRvResult = ((RecyclerView) findViewById(R.id.rv_search_result));
        mRvResult.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new SearchResultAdapter(mContext, mStockList);
        mRvResult.setAdapter(mAdapter);
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

    private class SearchViewCallback implements MarketPresenter.MarketViewCallback {
        @Override
        public void onStockListGetSuccess(List<StockInfoBean> list, StockIndexBean szBean, StockIndexBean shBean) {
            Logger.i(1, "listSu:" + list.toString());
            mStockList.clear();
            mStockList.addAll(list);
            if (mAdapter.getItemCount() == 0) {
                mAdapter.notifyItemInserted(0);
            } else {
                mAdapter.notifyDataSetChanged();
            }
            dismissProgress();
        }

        @Override
        public void onStockListGetFailed(String msg) {
            dismissProgress();
//            toastGo("没有找到结果...");
            snackBarGo("没有找到结果...");
        }
    }
}
