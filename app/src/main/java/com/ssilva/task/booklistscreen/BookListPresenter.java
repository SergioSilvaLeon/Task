package com.ssilva.task.booklistscreen;

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
    private String currentQuery;

    public BookListPresenter(IDataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }


    @Override
    public void loadMoreListOfBooks(int startIndex) {

        view.showProgressBar();

        Disposable disposable = dataRepository.getBooksFromApi(startIndex, currentQuery)
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

        view.showProgressBar();

        // TODO: Learn what each mean

        Disposable subscription = query.debounce(300, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .filter(_query ->  !_query.isEmpty())
                .map(__ -> currentQuery  = __)
                .switchMap(search -> dataRepository.getBooksByQuery(search))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    result -> {
                        view.onSuccessQuery(result);
                    },
                        Throwable::printStackTrace
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
