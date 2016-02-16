package com.fanyafeng.testnew.testnavigation;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.fanyafeng.testnew.R;

/**
 * Created by fanyafeng on 2015/10/28,0028.
 */
public class MyRecyclerViewHolder extends RecyclerView.ViewHolder {
    public TextView textView;

    public MyRecyclerViewHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.id_textview);
    }
}
