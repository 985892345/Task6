package com.ndhzs.task6.RvAdapter.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ndhzs.task6.R;

public class HeadViewHolder extends RecyclerView.ViewHolder {

    public TextView textView;

    public HeadViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.rv_head);
    }
}
