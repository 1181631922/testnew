package com.fanyafeng.testnew.testnavigation.refreshAndLoad;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

/**
 * Created by fanyafeng on 2015/11/23,0023.
 */
public class OnRcvScrollListener extends RecyclerView.OnScrollListener implements OnBottomListener {
    private String TAG = getClass().getSimpleName();

    public static enum LAYOUT_MANAGER_TYPE {
        LINEAR,
        GRID,
        STAGGERED_GRID
    }

    /**
     * layoutManger的类型（枚举）
     */
    protected LAYOUT_MANAGER_TYPE layoutManagerType;

    /**
     * 最后一个位置
     */
    private int[] lastposition;

    /**
     * 最后一个可见的item的位置
     */
    private int lastVisableItemPosition;

    /**
     * 是否正在加载
     */
    private boolean isLoadingMore = false;

    /**
     * 当前滑动的状态
     */
    private int currentScrollState = 0;


    public OnRcvScrollListener() {
        super();
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        currentScrollState = newState;
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        if ((visibleItemCount > 0 && currentScrollState == RecyclerView.SCROLL_STATE_IDLE && lastVisableItemPosition >= (totalItemCount - 1))) {
            onBottom();
        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManagerType == null) {
            if (layoutManager instanceof LinearLayoutManager) {
                layoutManagerType = LAYOUT_MANAGER_TYPE.LINEAR;
            } else if (layoutManager instanceof LinearLayoutManager) {
                layoutManagerType = LAYOUT_MANAGER_TYPE.GRID;
            } else if (layoutManager instanceof LinearLayoutManager) {
                layoutManagerType = LAYOUT_MANAGER_TYPE.STAGGERED_GRID;
            } else {
                throw new RuntimeException("Unsupported LayoutManager used.");
            }
        }
        switch (layoutManagerType) {
            case LINEAR:
                lastVisableItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                break;
            case GRID:
                lastVisableItemPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
                break;
            case STAGGERED_GRID:
                StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
                if (lastposition == null)
                    lastposition = new int[staggeredGridLayoutManager.getSpanCount()];
                staggeredGridLayoutManager.findLastVisibleItemPositions(lastposition);
                lastVisableItemPosition = findMax(lastposition);
                break;
        }
    }

    @Override
    public void onBottom() {

    }

    private int findMax(int[] lastposition) {
        int max = lastposition[0];
        for (int value : lastposition) {
            if (value > max)
                max = value;
        }
        return max;
    }
}
