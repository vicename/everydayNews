package com.guanyue.everydaynews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

public class MsgAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context mContext;
    List<MsgBean> list;
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;

    private View mHeaderView;

    private OnItemClickDifListener onItemClickRecyclerListener;
    private SimpleDateFormat simpleDateFormat;

    public MsgAdapter(Context context, List<MsgBean> list) {
        this.list = list;
        this.mContext = context;
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case TYPE_HEADER:
                if (mHeaderView != null) {
                    return new Holder(mHeaderView);
                }
                break;
            case TYPE_NORMAL:
                View viewLiveRoomMessage = LayoutInflater.from(mContext).inflate(R.layout.item_msg, parent, false);
                holder = new MessageViewHolder(viewLiveRoomMessage);
                break;
        }
        return holder;
    }

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    public View getHeaderView() {
        return mHeaderView;
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null) return TYPE_NORMAL;
        if (position == 0) return TYPE_HEADER;
        return TYPE_NORMAL;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_HEADER) return;
        final int pos = getRealPosition(holder, position);
        if (holder instanceof MessageViewHolder) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickRecyclerListener != null) {
                        onItemClickRecyclerListener.onItemClick(holder.itemView, pos);
                    }
                }
            });
            MessageViewHolder msgHolder = (MessageViewHolder) holder;
            msgHolder.tvMsg.setText(list.get(pos).msgContent);
            String time = simpleDateFormat.format(new Date(list.get(pos).time));
            msgHolder.tvTime.setText(time);
        }
    }

    public int getRealPosition(RecyclerView.ViewHolder holder, int originalPosition) {
        int position = holder.getLayoutPosition();
        position = mHeaderView == null ? position : position - 1;
        return position;
    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        }
        return mHeaderView == null ? list.size() : list.size() + 1;
    }

    public void setOnItemClickListener(OnItemClickDifListener listener) {
        onItemClickRecyclerListener = listener;
    }

    private class MessageViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvMsg;
        private final TextView tvTime;

        public MessageViewHolder(final View itemView) {
            super(itemView);
            tvMsg = ((TextView) itemView.findViewById(R.id.tv_msg));
            tvTime = ((TextView) itemView.findViewById(R.id.tv_sava_time));
        }
    }

    class Holder extends RecyclerView.ViewHolder {
        public Holder(View itemView) {
            super(itemView);
        }
    }

}
