package com.ndhzs.task6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.ndhzs.task6.MyFragment.Fragment_beauty;
import com.ndhzs.task6.MyFragment.Fragment_fiction;
import com.ndhzs.task6.MyFragment.Fragment_funny;
import com.ndhzs.task6.MyFragment.Fragment_joke;
import com.ndhzs.task6.MyFragment.Fragment_recommend;
import com.ndhzs.task6.MyFragment.Fragment_picture;
import com.ndhzs.task6.MyFragment.Fragment_science;
import com.ndhzs.task6.dialog.ClosedListenerInterface;
import com.ndhzs.task6.dialog.TabDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * 我用的ViewPager2来实现，由于时间原因，就只写了三个界面
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TabLayout tabLayout;
    private Button butDialog;
    private ViewPager2 viewPager2;
    private Dialog tabDialog;

    private final List<Fragment> fragments = new ArrayList<>();
    private final List<Fragment> otherFragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragments();
        initView();
        initData();
        initEvent();
    }

    private void initView() {
        tabLayout = findViewById(R.id.tabLayout);
        butDialog = findViewById(R.id.btn_tabDialog);
        viewPager2 = findViewById(R.id.viewPager2);
        butDialog.setOnClickListener(this);
    }

    private void initData() {

        viewPager2.setAdapter(new FragmentStateAdapter(this) {

            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return fragments.get(position);
            }

            @Override
            public int getItemCount() {
                return fragments.size();
            }
        });

        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(fragments.get(position).toString());
            }
        }).attach();
    }

    private void initEvent() {

    }

    private void initFragments() {

        fragments.add(new Fragment_recommend());
        fragments.add(new Fragment_picture());
        fragments.add(new Fragment_joke());
        fragments.add(new Fragment_fiction());

        otherFragments.add(new Fragment_funny());
        otherFragments.add(new Fragment_science());
        otherFragments.add(new Fragment_beauty());
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_tabDialog:
                tabDialog = new TabDialog(this, fragments, otherFragments, new ClosedListenerInterface() {
                    @Override
                    public void Closed() {
                        //最开始我想的不是直接刷新adapter，最开始用的RV的adapter的notifyItemMoved方法
                        //但出现了Fragment already added的问题，查了文档，因为ViewPager2与Fragment关联的那个adapter
                        //每次都要传入一个新的Fragment(就在上面的第64行)，没有办法，就只能这样了
                        initData();
                    }
                });
                tabDialog.show();
        }
    }
}