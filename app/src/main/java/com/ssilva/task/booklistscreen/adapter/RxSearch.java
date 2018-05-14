package com.ssilva.task.booklistscreen.adapter;

import android.support.v7.widget.SearchView;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

public class RxSearch implements SearchView.OnQueryTextListener {

    private Subject<String> querySubject = BehaviorSubject.<String>create().toSerialized();

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (!newText.isEmpty()) {
            querySubject.onNext(newText);
        }
        return true;
    }

    public Observable<String> getQueryObservable() {
        return querySubject;
    }
}
