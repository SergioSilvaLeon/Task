package com.ssilva.task.booklistscreen;

import com.ssilva.task.common.RxBasePresenter;
import com.ssilva.task.data.IDataRepository;

import java.util.concurrent.TimeUnit;

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
    public void loadMoreListOfBooks(int startIndex) {

        view.showProgressBar();


        Disposable disposable = view.getScrollObservable()
                .flatMap(index -> view.getQueryObservable()
                        .flatMapSingle(query -> dataRepository.getBooksFromApi(startIndex, query))
                )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        bookList -> view.onFetchSuccess(bookList),
                        error -> view.onError(error)
                );

        subscribe(disposable);
    }

    @Override
    public void loadBooksByQuery() {

        Disposable subscription = view.getQueryObservable()
                .debounce(300, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .filter(que -> !que.isEmpty())
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
