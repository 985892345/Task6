package com.ndhzs.task6.MyFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ndhzs.task6.R;
import com.ndhzs.task6.RvAdapter.UniversalAdapter;

public class Fragment_picture extends Fragment {

    private View view;
    private final String[] data = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
    private final String[] colors = {"#FF018786","#757575","#2196F3","#FFC107","#4CAF50","#673AB7",
            "#FF5722","#3F51B5","#8C1010","#77C533","#A58102","#37BFAB"};
    private final String[] heads = {"第一", "第二","第三"};

    @Override
    public String toString() {
        return "图片";
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_fragment_picture, container, false);

        initRV();
        return view;
    }

    private void initRV() {
        RecyclerView recyclerView = view.findViewById(R.id.rv_picture);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new UniversalAdapter(data, colors, heads, 5));
    }
}
