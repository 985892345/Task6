package com.ndhzs.task6.dialog;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.ndhzs.task6.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TopRvAdapter extends RecyclerView.Adapter<TopRvAdapter.TopRvHolder> implements ItemChangedInterface {

    private final List<Fragment> fragments;
    private final List<Fragment> unChangedFragments = new ArrayList<>();
    private ItemChangedInterface changedInterface;
    private final String TAG = "123";

    public TopRvAdapter(List<Fragment> fragments) {
        this.fragments = fragments;
        unChangedFragments.addAll(fragments);
    }

    public void setChangedInterface(ItemChangedInterface changedInterface) {
        this.changedInterface = changedInterface;
    }

    @NonNull
    @Override
    public TopRvHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dialog_rv_item, parent, false);
        return new TopRvHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopRvHolder holder, int position) {
        holder.mButton.setText(fragments.get(position).toString());
        holder.mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position != 0){
                    for (int i = 0; i < fragments.size(); i++){
                        if (fragments.get(i).toString().contentEquals(holder.mButton.getText())){
                            Log.d(TAG, fragments.get(i).toString());
                            Log.d(TAG, (String) holder.mButton.getText());
                            changedInterface.add(fragments.get(i));
                            notifyItemRemoved(i);
                            fragments.remove(i);
                            break;
                        }
                    }

                    //以下是采用的position来增加，但出现了许多问题，问题主要是没弄懂position的机制，在删除某一项后，position有时候不变
                    //有时候就移位了，实在是没时间弄了，于是只好以点击按键的相同的文字来增加
                    //要改此问题，还和下面的100行有关
//                    Log.d(TAG, "换行\ntop: position = " + position);
//                    Log.d(TAG, "点击的是"+holder.mButton.getText()+"    反应的是"+unChangedFragments.get(position));
//                    Log.d(TAG, "最开始的top改变数组"+Arrays.toString(fragments.toArray()));
//                    Log.d(TAG, "最开始的top不变数组"+Arrays.toString(unChangedFragments.toArray()));
//                    changedInterface.add(unChangedFragments.get(position));//这个每个item对应的position仍然不变
//                    //得到fragment中与之前position位置相同的元素位置(因为position不变)
//                    int i = fragments.indexOf(unChangedFragments.get(position));
//                    Log.d(TAG, "i = "+i);
//                    notifyItemRemoved(i);//这个remove不是真正的remove，每个item对应的position不变，但内部保存的数组位置会改变
//                    fragments.remove(i);
//                    unChangedFragments.set(position, null);
//                    Log.d(TAG, "变化后的top数组"+Arrays.toString(unChangedFragments.toArray()));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }

    @Override
    public void onMove(int currentPosition, int targetPosition) {
        if (currentPosition != 0 && targetPosition != 0){
            for (int i = 0; i < targetPosition - currentPosition; i++) {
                notifyItemMoved(currentPosition + i, currentPosition + i + 1);
                Collections.swap(fragments, currentPosition + i, currentPosition + i + 1);
            }
            for (int i = 0; i < currentPosition - targetPosition; i++)  {
                notifyItemMoved(currentPosition - i, currentPosition - i - 1);
                Collections.swap(fragments, currentPosition - i, currentPosition - i - 1);
            }
        }
    }

    @Override
    public void add(Fragment fragment) {
        fragments.add(fragment);
        Log.d(TAG, "add = "+fragment.toString());
//        Log.d(TAG, "top add前有"+this.getItemCount());
//        Log.d(TAG, "top变化的数组长度 = "+fragments.size());
        notifyItemInserted(fragments.size());
//        unChangedFragments.add(fragment);
//        Log.d(TAG, "top add后有"+this.getItemCount());
////        if (!unChangedFragments.contains(fragment)){
////            unChangedFragments.add(fragment);
////        }
////        unChangedFragments.clear();
////        unChangedFragments.addAll(fragments);
//        Log.d(TAG, "增加后的top数组"+Arrays.toString(unChangedFragments.toArray()));
    }

    public static class TopRvHolder extends RecyclerView.ViewHolder {

        Button mButton;

        public TopRvHolder(@NonNull View itemView) {
            super(itemView);
            mButton = itemView.findViewById(R.id.materialButton);
        }
    }
}
