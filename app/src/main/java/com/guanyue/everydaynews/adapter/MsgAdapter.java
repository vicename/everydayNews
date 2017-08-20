package com.guanyue.everydaynews.adapter;

import android.content.Context;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.generallibrary.adapter.base_recycler.BaseRecyclerViewAdapter;
import com.generallibrary.adapter.base_recycler.BaseViewHolder;
import com.generallibrary.adapter.base_recycler.OnItemClickDifListener;
import com.guanyue.everydaynews.R;
import com.guanyue.everydaynews.data.MsgBean;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by LiDaChang on 17/8/17.
 * __--__---__-------------__----__
 */

public class MsgAdapter extends BaseRecyclerViewAdapter<MsgBean> {
    private Context mContext;
    private SimpleDateFormat simpleDateFormat;

    public MsgAdapter(Context mContext, List<MsgBean> data) {
        super(mContext, data);
        this.mContext = mContext;
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
    }

    @Override
    public BaseViewHolder getItemHolder(Context context, OnItemClickDifListener listener, ViewGroup parent) {
        return new MessageViewHolder(context, listener, LayoutInflater.from(context).inflate(R.layout.item_msg, parent, false));
    }

    private class MessageViewHolder extends BaseViewHolder<MsgBean> {
        private final TextView tvMsg;
        private final TextView tvTime;

        public MessageViewHolder(Context context, final OnItemClickDifListener listener, View itemView) {
            super(context, listener, itemView);
            tvMsg = ((TextView) itemView.findViewById(R.id.tv_msg));
            tvTime = ((TextView) itemView.findViewById(R.id.tv_sava_time));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClick(v, getAdapterPosition());
                    }
                }
            });
        }

        @Override
        public void refreshView() {
            tvMsg.setText(mData.msgContent);
            String time = simpleDateFormat.format(new Date(mData.time));
            tvTime.setText(time);
        }
    }
}
