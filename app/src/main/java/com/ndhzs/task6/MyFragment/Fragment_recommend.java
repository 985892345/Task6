package com.ndhzs.task6.MyFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ndhzs.task6.R;
import com.ndhzs.task6.RvAdapter.UniversalAdapter;

public class Fragment_recommend extends Fragment {

    private View view;
    private final String[] data = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l"};
    private final String[] colors = {"#FF018786","#757575","#2196F3","#FFC107","#4CAF50","#673AB7",
            "#FF5722","#3F51B5","#8C1010","#77C533","#A58102","#37BFAB"};
    private final String[] heads = {"第一个大标题", "第二个大标题","第三个大标题"};

    @Override
    public String toString() {
        return "推荐";
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_fragment_recommend, container, false);

        initRV();
        return view;
    }

    private void initRV() {
        RecyclerView recyclerView = view.findViewById(R.id.rv_recommend);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //这里我自定义了每项item的占位多少
        GridLayoutManager manager = new GridLayoutManager(getActivity(),2);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position % 5 == 0){
                    return 2;
                }
                return 1;
            }
        });
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new UniversalAdapter(data, colors, heads, 5));
    }
}