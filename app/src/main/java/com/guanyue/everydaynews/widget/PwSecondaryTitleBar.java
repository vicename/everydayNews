package com.guanyue.everydaynews.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.guanyue.everydaynews.R;


/**
 * Created by LiDaChang on 17/4/25.
 * __--__---__-------------__----__
 */

public class PwSecondaryTitleBar extends FrameLayout {
    private Context mContext;
    private TextView mTVRight;
    private OnMenuItemClickPwListener mListener;
    private OnNavigationBtnClickListener mNavigationClickListener;
    private View mProg;
    private View mViewBottomLine;

    public PwSecondaryTitleBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.layout_secondary_title_bar, this);
        mTVRight = (TextView) findViewById(R.id.tv_right);
        mProg = findViewById(R.id.progress_bar);
        mViewBottomLine = findViewById(R.id.view_title_bottom_line);
        findViewById(R.id.f_layout_back).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mNavigationClickListener != null) {
                    mNavigationClickListener.onNavigationClick(v);
                }
            }
        });

        mTVRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onCLick(v);
                }
            }
        });
    }

    public void setTitle(String title) {
        if (title != null) {
            ((TextView) findViewById(R.id.tv_title_bar_title)).setText(title);
        }
    }

    public void setItemText(String itemText) {
        if (itemText != null) {
            mTVRight.setVisibility(VISIBLE);
            mTVRight.setText(itemText);
        }
    }

    public void setItemEnable(boolean isEnabled) {
        mTVRight.setEnabled(isEnabled);
    }

    public boolean isItemEnabled() {
        return mTVRight.isEnabled();
    }

    public void setProgress(boolean isProgressing) {
        if (isProgressing) {
            mProg.setVisibility(VISIBLE);
            mTVRight.setVisibility(GONE);
        } else {
            mProg.setVisibility(GONE);
            mTVRight.setVisibility(VISIBLE);
        }
    }

    public void disableBottomLine() {
        mViewBottomLine.setVisibility(GONE);
    }

    public void setOnItemClickListener(OnMenuItemClickPwListener listener) {
        mListener = listener;
    }

    public void setOnNavigationBtnClickListener(OnNavigationBtnClickListener listener) {
        mNavigationClickListener = listener;
    }

    public interface OnMenuItemClickPwListener {
        void onCLick(View view);
    }

    public interface OnNavigationBtnClickListener {
        void onNavigationClick(View v);
    }


}
