package com.ndhzs.task6.dialog;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class TabDialogItemCallback extends ItemTouchHelper.Callback {

    private final ItemChangedInterface changedInterface;

    public TabDialogItemCallback(ItemChangedInterface changedInterface) {
        this.changedInterface = changedInterface;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        //拖拽
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT;
        //滑动
        int swipeFlags = 0;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        int currentPosition = viewHolder.getAdapterPosition();
        int targetPosition = target.getAdapterPosition();
        changedInterface.onMove(currentPosition, targetPosition);
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

    }
}
