package com.guanyue.everydaynews.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.generallibrary.utils.Logger;
import com.guanyue.everydaynews.R;

/**
 * Created by LiDaChang on 17/8/11.
 * __--__---__-------------__----__
 */

public class MainTabItemLayout extends FrameLayout {
    private final Context mContext;
    private final ImageView mIvIc;
    private final TextView mTvTab;
    private Drawable mDrawableSelected;
    private Drawable mDrawableNormal;
    private int mColorNormal;
    private int mColorSelected;
    private String mStr;

    public MainTabItemLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MainTabItemLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs);
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.layout_main_tab_item, this);
        mIvIc = ((ImageView) findViewById(R.id.iv_ic1));
        mTvTab = ((TextView) findViewById(R.id.tv_tab_info));
        if (attrs != null) {
            TypedArray b = getContext().obtainStyledAttributes(attrs, R.styleable.MainTab, defStyle, 0);
            mDrawableNormal = b.getDrawable(R.styleable.MainTab_drawableNormal);
            mDrawableSelected = b.getDrawable(R.styleable.MainTab_drawableSelected);
            mColorNormal = b.getColor(R.styleable.MainTab_colorNormal, 0);
            mColorSelected = b.getColor(R.styleable.MainTab_colorSelected, 0);
            mStr = b.getString(R.styleable.MainTab_text);
            b.recycle();
        }
        mIvIc.setImageDrawable(mDrawableNormal);
        mTvTab.setTextColor(mColorNormal);
        mTvTab.setText(mStr);
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        setItemSelected(selected);
    }

    public void setItemSelected(boolean isSelected) {
        if (isSelected) {
            mTvTab.setTextColor(mColorSelected);
            mIvIc.setImageDrawable(mDrawableSelected);
        } else {
            mTvTab.setTextColor(mColorNormal);
            mIvIc.setImageDrawable(mDrawableNormal);
        }
    }
}
