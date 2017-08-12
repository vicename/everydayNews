package com.guanyue.everydaynews.activity;

import android.os.Message;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.TextView;

import com.generallibrary.listener_simpler.TextWatcherSimpler;
import com.generallibrary.utils.Logger;
import com.guanyue.everydaynews.R;
import com.guanyue.everydaynews.widget.PwMainTitleBar;

public class ReportActivity extends AppBaseActivity {
    private PwMainTitleBar mTitleBar;
    private AppCompatEditText mEdtReport;
    private TextView mTvTip;

    @Override
    protected void initVar() {

    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_report);
        mTitleBar = ((PwMainTitleBar) findViewById(R.id.titleBar));
        mTitleBar.setOnNavigationBtnClickListener(new PwMainTitleBar.OnNavigationBtnClickListener() {
            @Override
            public void onNavigationClick(View v) {
                finish();
            }
        });
        mTitleBar.setTitle("留言给我们");
        mTvTip = ((TextView) findViewById(R.id.tv_report_tip));
        String tip = "0/500个字";
        mTvTip.setText(tip);
        mEdtReport = ((AppCompatEditText) findViewById(R.id.edt_report));
        mEdtReport.addTextChangedListener(new TextWatcherSimpler() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                final String tip = s.length() + "/500个字";
                mTvTip.setText(tip);
            }
        });

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
}
