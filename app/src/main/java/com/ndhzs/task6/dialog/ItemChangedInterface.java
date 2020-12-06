package com.ndhzs.task6.dialog;

import androidx.fragment.app.Fragment;

/**
 * 实现该接口就可以改数据位置
 */
public interface ItemChangedInterface {
    void onMove(int currentPosition, int targetPosition);
    void add(Fragment fragment);
}
