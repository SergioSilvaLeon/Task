package com.ssilva.task.booklistscreen.adapter;


import android.support.v7.widget.LinearLayoutManager;

import com.ssilva.task.util.PaginationScrollListener;

public class PaginationScrollingListener extends PaginationScrollListener{

    private boolean isLoading = false;
    private int totalItems = 0;

    PaginationCallback callback;

    public PaginationScrollingListener(LinearLayoutManager layoutManager, PaginationCallback callback) {
        super(layoutManager);
        this.callback = callback;
    }

    @Override
    protected void loadMoreItems() {
        isLoading = true;
        totalItems += 10;

        callback.loadNextItems(totalItems);
    }

    @Override
    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }
}
