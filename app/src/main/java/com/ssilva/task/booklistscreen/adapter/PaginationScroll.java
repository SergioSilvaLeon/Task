package com.ssilva.task.booklistscreen.adapter;


import android.support.v7.widget.LinearLayoutManager;

import com.ssilva.task.util.PaginationScrollListener;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class PaginationScroll extends PaginationScrollListener {

    private Subject<Integer> totalSubject = PublishSubject.<Integer>create().toSerialized();

    private boolean isLoading = false;
    private int totalItems = 0;


    public PaginationScroll(LinearLayoutManager layoutManager) {
        super(layoutManager);
    }

    @Override
    protected void loadMoreItems() {
        isLoading = true;
        totalItems += 10;

        totalSubject.onNext(totalItems);
    }

    @Override
    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    public Observable<Integer> getTotalListener() {
        return totalSubject;
    }
}
