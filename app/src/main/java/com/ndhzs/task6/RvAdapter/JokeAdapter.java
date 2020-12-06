package com.ndhzs.task6.RvAdapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ndhzs.task6.R;
import com.ndhzs.task6.RvAdapter.ViewHolder.GridViewHolder;
import com.ndhzs.task6.RvAdapter.ViewHolder.HeadViewHolder;
import com.ndhzs.task6.RvAdapter.ViewHolder.ImgTextViewHolder;

public class JokeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final int THE_HEAD = -1;
    private final int THE_IMG_TEXT = -2;
    private final int THE_GRID = -3;
    private final String[] data;
    private final String[] colors;
    private final String[] heads;
    private final int interval;

    public JokeAdapter(String[] data, String[] colors, String[] heads, int interval) {
        this.data = data;
        this.colors = colors;
        this.heads = heads;
        this.interval = interval;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == THE_HEAD){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_head, parent, false);
            return new HeadViewHolder(view);
        }else if (viewType == THE_IMG_TEXT){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_img_text, parent, false);
            return new ImgTextViewHolder(view);
        }
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid, parent, false);
        return new GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == THE_HEAD) {
            HeadViewHolder holder1 = (HeadViewHolder)holder;
            holder1.textView.setText(heads[position / interval]);
        }else if (getItemViewType(position) == THE_IMG_TEXT){
            ImgTextViewHolder holder2 = (ImgTextViewHolder) holder;
            holder2.textView.setText(data[getTextPosition(position)]);
            holder2.imageView.setBackgroundColor(Color.parseColor(colors[getColorPosition(position)]));
        }else {
            GridViewHolder holder3 = (GridViewHolder) holder;
            holder3.imageView.setBackgroundColor(Color.parseColor(colors[getColorPosition(position)]));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position % interval == 0){
            return THE_HEAD;
        }else if (position % interval == 1 || position % interval == 6){
            return THE_IMG_TEXT;
        }
        return THE_GRID;
    }

    @Override
    public int getItemCount() {
        return colors.length + heads.length;
    }

    private int getTextPosition(int position){
        int i = (position - 1) % 7;
        if (i == 0){
            return (position - 1) * 2 / 7;
        }else {
            return (position + 1) * 2 / 7 - 1;
        }
    }

    private int getColorPosition(int position){
        return position - 1 - position / interval;
    }
}
