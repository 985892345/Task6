package com.ndhzs.task6.RvAdapter.ViewHolder;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ndhzs.task6.R;

public class GridViewHolder extends RecyclerView.ViewHolder {

    public ImageView imageView;

    public GridViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.item_grid_img);
    }
}
