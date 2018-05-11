package com.ssilva.task.booklistscreen;

import android.util.Log;

import com.ssilva.task.common.RxBasePresenter;
import com.ssilva.task.data.IDataRepository;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BookListPresenter extends RxBasePresenter implements BookListViewPresenterContract.Presenter {

    private IDataRepository dataRepository;
    private BookListViewPresenterContract.View view = null;

    public BookListPresenter(IDataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }


    @Override
    public void loadListOfBooks() {
        view.showProgressBar();

        Disposable disposable = dataRepository.getBooksFromApi(0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        bookList -> view.onSuccess(bookList),
                        error -> view.onError(error)
                );

        subscribe(disposable);
    }

    @Override
    public void loadMoreListOfBooks(int startIndex) {
        Disposable disposable = dataRepository.getBooksFromApi(startIndex)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        bookList -> view.onFetchSuccess(bookList),
                        error -> view.onError(error)
                );

        subscribe(disposable);
    }

    @Override
    public void loadBooksByQuery(Observable<String> query) {
        Disposable subscription = query.debounce(300, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .filter(_query ->  !_query.isEmpty())
                .switchMap(search -> dataRepository.getBooksByQuery(search))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    result -> Log.i("query onNext", result.getBooks().toString()),
                    error -> Log.i("query onError", error.getMessage())
                );

        subscribe(subscription);

    }

    @Override
    public void setView(BookListViewPresenterContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
        view = null;
    }
}
