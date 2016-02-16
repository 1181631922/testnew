package com.fanyafeng.testnew.testnavigation;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fanyafeng.testnew.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fanyafeng on 2015/10/28,0028.
 */
public class MyStaggeredViewAdapter extends RecyclerView.Adapter<MyRecyclerViewHolder> {
    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    public OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public Context context;
    public List<String> mDates;
    public List<Integer> mHeights;
    public LayoutInflater mLayoutInflater;

    public MyStaggeredViewAdapter(Context context) {
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
        mDates = new ArrayList<>();
        mHeights = new ArrayList<>();
        for (int i = 'A'; i <= 'z'; i++) {
            mDates.add((char) i + "");
        }
        for (int i = 0; i < mDates.size(); i++) {
            mHeights.add((int) (Math.random() * 300) + 200);
        }
    }

    @Override
    public MyRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_main, parent, false);
        MyRecyclerViewHolder myRecyclerViewHolder = new MyRecyclerViewHolder(view);
        return myRecyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyRecyclerViewHolder holder, final int position) {
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(holder.itemView, position);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemClickListener.onItemLongClick(holder.itemView, position);
                    return true;
                }
            });
        }
        ViewGroup.LayoutParams mLayoutParams = holder.textView.getLayoutParams();
        mLayoutParams.height = mHeights.get(position);
        holder.textView.setLayoutParams(mLayoutParams);
        holder.textView.setText(mDates.get(position));
    }

    @Override
    public int getItemCount() {
        return mDates.size();
    }
}
