package com.app.cellstudio.holidayplanner.presentation.util;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_DRAGGING;

/**
 * Created by coyan on 02/10/2018.
 */

public class AutomatedOnScrollListener extends RecyclerView.OnScrollListener {

    public interface OnSnappedListener {
        void onSnapped(int position);
        void onDragging();
    }

    private OnSnappedListener onSnappedListener;

    public void setOnSnappedListener(OnSnappedListener onSnappedListener) {
        this.onSnappedListener = onSnappedListener;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        switch (newState){
            case SCROLL_STATE_DRAGGING:
                if (onSnappedListener != null) {
                    onSnappedListener.onDragging();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        int position = layoutManager.findFirstCompletelyVisibleItemPosition();
        if (position >= 0) {
            if (onSnappedListener != null) {
                onSnappedListener.onSnapped(position);
            }
        }
    }
}