package com.guanyue.everydaynews.activity;

import android.os.Message;
import android.support.v7.app.AlertDialog;
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
        findViewById(R.id.tv_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit(mEdtReport.getText().toString());
            }

        });
    }

    private void submit(String text) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage("提交中...");
        builder.setCancelable(false);
        final AlertDialog dialog = builder.create();
        dialog.show();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
                toastGo("提交成功!");

                String ss = "财经观察—专注财经头条资讯平台每天都来读【财经观察】颠覆传统阅读，个性化推荐模式，运用大数据算法，精准推荐你喜欢的内容，从此不受冗奈信息困扰。【个性化推荐】为你推荐实时财经新闻。【更多行情】更多股票行情，24小时实时更新【简单易用】基本我们的算法，使用很简单，功能很强大，轻松看你想看的财经资讯。建议或意见请联系：邮箱：mrxwkx@sina.com新浪微博：每日财经newsQQ号：2670508246财经观察用户协议\n";
            }
        }, 1200);
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
