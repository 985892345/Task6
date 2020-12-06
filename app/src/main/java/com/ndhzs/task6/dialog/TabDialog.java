package com.ndhzs.task6.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.ndhzs.task6.MainActivity;
import com.ndhzs.task6.R;

import java.util.List;

public class TabDialog extends Dialog {

    private final MainActivity activity;
    private final List<Fragment> fragments;
    private final List<Fragment> otherFragments;
    private final ClosedListenerInterface listener;
    private RecyclerView topRecycler;
    private RecyclerView bottomRecycler;
    private TopRvAdapter topAdapter;
    private BottomRvAdapter bottomAdapter;

    public TabDialog(@NonNull MainActivity context, List<Fragment> fragments, List<Fragment> otherFragments , ClosedListenerInterface listener) {
        super(context, R.style.Theme_Tab_dialog);
        this.activity = context;
        this.fragments = fragments;
        this.otherFragments = otherFragments;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        initView();
        initEvent();
    }

    @Override
    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
        listener.Closed();
        return super.onKeyDown(keyCode, event);
    }

    private void initView() {
        View layout = LayoutInflater.from(activity).inflate(R.layout.main_tab_dialog, null);
        setContentView(layout);

        topRecycler = layout.findViewById(R.id.rv_dlg_top);
        topAdapter = new TopRvAdapter(fragments);

        bottomRecycler = layout.findViewById(R.id.rv_dlg_bottom);
        bottomAdapter = new BottomRvAdapter(otherFragments, topAdapter);

        topAdapter.setChangedInterface(bottomAdapter);

        //设置窗口
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = activity.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 0.8); // 高度设置为屏幕的0.6
        lp.height = (int) (d.heightPixels * 0.6); // 高度设置为屏幕的0.6
        dialogWindow.setAttributes(lp);
        setCanceledOnTouchOutside(false);
        setCancelable(true);//点击物理返回键
    }

    private void initEvent() {

        topRecycler.setLayoutManager(new GridLayoutManager(activity, 3));
        topRecycler.setAdapter(topAdapter);

        bottomRecycler.setLayoutManager(new GridLayoutManager(activity, 3));
        bottomRecycler.setAdapter(bottomAdapter);

        //关联ItemTouchHelper
        TabDialogItemCallback callback = new TabDialogItemCallback(topAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(topRecycler);
    }
}
