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
import java.util.Arrays;
import java.util.List;

public class BottomRvAdapter extends RecyclerView.Adapter<BottomRvAdapter.BottomRvHolder> implements ItemChangedInterface{

    private final List<Fragment> otherFragments;
    private final List<Fragment> unChangedFragments = new ArrayList<>();
    private final ItemChangedInterface changedInterface;
    private final String TAG = "123";

    public BottomRvAdapter(List<Fragment> otherFragments, ItemChangedInterface changedInterface) {
        this.otherFragments = otherFragments;
        unChangedFragments.addAll(otherFragments);
        this.changedInterface = changedInterface;

    }

    @NonNull
    @Override
    public BottomRvHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dialog_rv_item, parent, false);
        return new BottomRvHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BottomRvHolder holder, int position) {
        holder.mButton.setText(otherFragments.get(position).toString());
        holder.mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < otherFragments.size(); i++){
                    if (otherFragments.get(i).toString().contentEquals(holder.mButton.getText())){
                        Log.d(TAG, otherFragments.get(i).toString());
                        Log.d(TAG, (String) holder.mButton.getText());
                        changedInterface.add(otherFragments.get(i));
                        notifyItemRemoved(i);
                        otherFragments.remove(i);
                        break;
                    }
                }

                //以下是采用的position来增加，但出现了许多问题，问题主要是没弄懂position的机制，在删除某一项后，position有时候不变
                //有时候就移位了，实在是没时间弄了，于是只好以点击按键的相同的文字来增加
                //要改此问题，还和下面的100行有关
//                Log.d(TAG, "换行\nbottom: position = " + position);
//                Log.d(TAG, "点击的是"+holder.button.getText()+"    反应的是"+unChangedFragments.get(position));
//                Log.d(TAG, "最开始的bottom改变数组"+Arrays.toString(otherFragments.toArray()));
//                Log.d(TAG, "最开始的bottom不变数组"+Arrays.toString(unChangedFragments.toArray()));
//                changedInterface.add(unChangedFragments.get(position));//这个每个item对应的position仍然不变
//                //得到fragment中与之前position位置相同的元素位置(因为position不变)
//                int i = otherFragments.indexOf(unChangedFragments.get(position));
//                Log.d(TAG, "i = "+i);
//                notifyItemRemoved(i);//这个remove不是真正的remove，每个item对应的position不变，但内部保存的数组位置仍改变
//                otherFragments.remove(i);
//                unChangedFragments.set(position, null);
//                if (otherFragments.size() == 0){
//                    unChangedFragments.clear();
//                }
////                unChangedFragments.remove(position);
//                Log.d(TAG, "变化后的bottom数组"+Arrays.toString(unChangedFragments.toArray()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return otherFragments.size();
    }

    @Override
    public void onMove(int currentPosition, int targetPosition) {
    }

    @Override
    public void add(Fragment fragment) {
        otherFragments.add(fragment);
        Log.d(TAG, "add = "+fragment.toString());
//        Log.d(TAG, "bottom add前有"+this.getItemCount());
//        Log.d(TAG, "bottom变化的数组长度 = "+otherFragments.size());
        notifyItemInserted(otherFragments.size());
//        unChangedFragments.add(fragment);
//        Log.d(TAG, "bottom add后有"+this.getItemCount());
////        if (!unChangedFragments.contains(fragment)){
////            unChangedFragments.add(fragment);
////        }
////        unChangedFragments.clear();
////        unChangedFragments.addAll(otherFragments);
//        Log.d(TAG, "增加后的bottom数组"+Arrays.toString(unChangedFragments.toArray()));
    }

    public static class BottomRvHolder extends RecyclerView.ViewHolder {

        Button mButton;

        public BottomRvHolder(@NonNull View itemView) {
            super(itemView);

            mButton = itemView.findViewById(R.id.materialButton);
        }
    }
}
