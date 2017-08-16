package com.guanyue.everydaynews.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.generallibrary.adapter.base_recycler.OnItemClickDifListener;
import com.guanyue.everydaynews.R;
import com.guanyue.everydaynews.data.StockBean;
import com.guanyue.everydaynews.data.StockInfoBean;

import java.util.List;

/**
 * Created by LiDaChang on 17/8/17.
 * __--__---__-------------__----__
 */

public class StockAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context mContext;
    List<StockInfoBean> list;
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;

    private View mHeaderView;

    private OnItemClickDifListener onItemClickRecyclerListener;

    public StockAdapter(Context context, List<StockInfoBean> list) {
        this.list = list;
        this.mContext = context;
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
                View viewLiveRoomMessage = LayoutInflater.from(mContext).inflate(R.layout.item_stock_list, parent, false);
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
            int color = 0;
            String rate = list.get(pos).diff_rate;
            float rateF = Float.parseFloat(rate);
            if (rateF < 0) {
                color = ContextCompat.getColor(mContext, R.color.color_rate_down);
            } else if (rateF > 0) {
                if (!rate.startsWith("+")) {
                    rate = "+" + rate;
                }
                color = ContextCompat.getColor(mContext, R.color.color_rate_up);
            } else {
                color = ContextCompat.getColor(mContext, R.color.text_secondary);
            }
            ((MessageViewHolder) holder).tvName.setText(list.get(pos).name);
            ((MessageViewHolder) holder).tvAmmount.setText(list.get(pos).nowPrice);
            ((MessageViewHolder) holder).tvAmmount.setTextColor(color);
            ((MessageViewHolder) holder).tvAccent.setText(rate);
            ((MessageViewHolder) holder).tvAccent.setTextColor(color);
            ((MessageViewHolder) holder).tvNum.setText(list.get(pos).num);
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
        private final TextView tvName;
        private final TextView tvNum;
        private final TextView tvAccent;
        private final TextView tvAmmount;

        public MessageViewHolder(final View itemView) {
            super(itemView);
            tvName = ((TextView) itemView.findViewById(R.id.tv_name));
            tvNum = ((TextView) itemView.findViewById(R.id.tv_num));
            tvAccent = ((TextView) itemView.findViewById(R.id.tv_accent));
            tvAmmount = ((TextView) itemView.findViewById(R.id.tv_ammount));
        }
    }

    class Holder extends RecyclerView.ViewHolder {
        public Holder(View itemView) {
            super(itemView);
        }
    }

}
