package com.ndhzs.task6.RvAdapter.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ndhzs.task6.R;

public class ImgTextViewHolder extends RecyclerView.ViewHolder{

    public TextView textView;
    public ImageView imageView;

    public ImgTextViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.tv_rv_item);
        imageView = itemView.findViewById(R.id.img_rv_item);
    }
}
