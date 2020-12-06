package com.ndhzs.task6.RvAdapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ndhzs.task6.R;
import com.ndhzs.task6.RvAdapter.ViewHolder.HeadViewHolder;
import com.ndhzs.task6.RvAdapter.ViewHolder.ImgTextViewHolder;

public class UniversalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int THE_HEAD = -1;
    private final String[] data;
    private final String[] colors;
    private final String[] heads;
    private final int interval;

    public UniversalAdapter(String[] data, String[] colors, String[] heads, int interval) {
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
        }
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_img_text, parent, false);
        return new ImgTextViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == THE_HEAD) {
            HeadViewHolder holder1 = (HeadViewHolder)holder;
            holder1.textView.setText(heads[position / interval]);
        }else {
            ImgTextViewHolder holder2 = (ImgTextViewHolder) holder;
            holder2.textView.setText(data[getItemViewType(position)]);
            holder2.imageView.setBackgroundColor(Color.parseColor(colors[getItemViewType(position)]));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position % interval == 0){
            return THE_HEAD;
        }else {
            return position - 1 - position / interval;
        }
    }

    @Override
    public int getItemCount() {
        return data.length + heads.length;
    }
}
