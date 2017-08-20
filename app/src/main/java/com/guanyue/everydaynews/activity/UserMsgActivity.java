package com.guanyue.everydaynews.activity;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.guanyue.everydaynews.R;
import com.guanyue.everydaynews.adapter.MsgAdapter;
import com.guanyue.everydaynews.data.MsgBean;
import com.guanyue.everydaynews.data.UserHistoryManager;
import com.guanyue.everydaynews.widget.PwMainTitleBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserMsgActivity extends AppBaseActivity {

    private PwMainTitleBar mTitleBar;
    private RecyclerView mRvStock;
    private List<MsgBean> mList;
    private MsgAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initVar() {
        mList = new ArrayList<MsgBean>();
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_user_msg);
        mTitleBar = ((PwMainTitleBar) findViewById(R.id.titleBar));
        mTitleBar.setOnNavigationBtnClickListener(new PwMainTitleBar.OnNavigationBtnClickListener() {
            @Override
            public void onNavigationClick(View v) {
                finish();
            }
        });
        mTitleBar.setTitle("我的消息");
        mRvStock = ((RecyclerView) findViewById(R.id.rv_msg));
        mRvStock.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new MsgAdapter(mContext, mList);
        mRvStock.setAdapter(mAdapter);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {
        List<MsgBean> list = UserHistoryManager.getInstance().getBrowsHistoryList();
        if (list != null) {
            mList.clear();
            mList.addAll(list);
            mAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void handleMessage(Message msg) {

    }
}
