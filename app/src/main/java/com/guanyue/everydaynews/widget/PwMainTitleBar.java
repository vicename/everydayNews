package com.guanyue.everydaynews.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.guanyue.everydaynews.R;

/**
 * Created by LiDaChang on 17/8/10.
 * __--__---__-------------__----__
 */

public class PwMainTitleBar extends FrameLayout {
    private Context mContext;
    private TextView mTVRight;
    private OnMenuItemClickPwListener mListener;
    private OnNavigationBtnClickListener mNavigationClickListener;
    private View mProg;
    private View mViewBottomLine;
    private ImageView mIvMenuItem;
    public static final String TAG_ITEM = "item";
    private View mIvBack;
    private View mViewRefresh;
    private OnMenuItem2ClickPwListener mMenuItem2Listener;

    public PwMainTitleBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.layout_primary_title_bar, this);
        mTVRight = (TextView) findViewById(R.id.tv_right);
        mProg = findViewById(R.id.progress_bar);
        mIvMenuItem = (ImageView) findViewById(R.id.iv_menu_item);
        mViewBottomLine = findViewById(R.id.view_title_bottom_line);
        mViewRefresh = findViewById(R.id.iv_refresh);
        mIvBack = findViewById(R.id.f_layout_back);
        mIvBack.setOnClickListener(new OnClickListener() {
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

        mIvMenuItem.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onCLick(v);
                }
            }
        });
        mViewRefresh.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMenuItem2Listener != null) {
                    mMenuItem2Listener.onClick(v);
                }
            }
        });
    }

    public void setTitle(String title) {
        if (title != null) {
            ((TextView) findViewById(R.id.tv_title_bar_title)).setText(title);
        }
    }

    public void blinkTitle() {
        View title = findViewById(R.id.tv_title_bar_title);
        title.setVisibility(GONE);
        title.setVisibility(VISIBLE);
    }

    public void setItemText(String itemText) {
        if (itemText != null) {
            mIvMenuItem.setVisibility(GONE);
            mTVRight.setVisibility(VISIBLE);
            mTVRight.setText(itemText);
            mTVRight.setTag(TAG_ITEM);
            mIvMenuItem.setTag(null);
            setItemEnable(true);
        } else {
            mIvMenuItem.setTag(null);
            setItemEnable(true);
            mTVRight.setTag(TAG_ITEM);
            mIvMenuItem.setVisibility(GONE);
            mTVRight.setVisibility(GONE);
        }
    }

    public void setItemRes(int res) {
        mTVRight.setVisibility(GONE);
        mIvMenuItem.setVisibility(VISIBLE);
        mIvMenuItem.setImageResource(res);
        mIvMenuItem.setTag(TAG_ITEM);
        mTVRight.setTag(null);
    }

    public void setItem2() {
        mViewRefresh.setVisibility(VISIBLE);
    }

    public void removeItem2() {
        mViewRefresh.setVisibility(GONE);
    }

    public void clearItem() {
        mIvMenuItem.setVisibility(GONE);
        mTVRight.setVisibility(GONE);
        mTVRight.setTag(null);
        mIvMenuItem.setTag(null);
    }

    public void setItemEnable(boolean isEnabled) {
        final View item = findViewWithTag(TAG_ITEM);
        if (item != null)
            item.setEnabled(isEnabled);
    }

    public boolean isItemyEnabled() {
        return mTVRight.isEnabled() || mIvMenuItem.isEnabled();
    }

    public void setProgress(boolean isProgressing) {
        View item = findViewWithTag(TAG_ITEM);
        if (isProgressing) {
            mProg.setVisibility(VISIBLE);
            if (item != null)
                item.setVisibility(GONE);
        } else {
            mProg.setVisibility(GONE);
            if (item != null)
                item.setVisibility(VISIBLE);
        }
    }

    public void disableBottomLine() {
        mViewBottomLine.setVisibility(GONE);
    }

    public void setOnItemClickListener(OnMenuItemClickPwListener listener) {
        mListener = listener;
    }

    public void setOnItem2ClickListener(OnMenuItem2ClickPwListener listener) {
        mMenuItem2Listener = listener;
    }

    public void setOnNavigationBtnClickListener(OnNavigationBtnClickListener listener) {
        mNavigationClickListener = listener;
        mIvBack.setVisibility(VISIBLE);
    }

    public interface OnMenuItemClickPwListener {
        void onCLick(View view);
    }

    public interface OnMenuItem2ClickPwListener {
        void onClick(View view);
    }

    public interface OnNavigationBtnClickListener {
        void onNavigationClick(View v);
    }

}
