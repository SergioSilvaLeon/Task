package com.ssilva.task.booklistscreen;

import com.ssilva.task.common.RxBasePresenter;
import com.ssilva.task.data.IDataRepository;

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

        Disposable disposable = dataRepository.getBooksFromApi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        bookList -> {
                            view.onSuccess(bookList);
                            // TODO: Move the command below -> doAfterTerminate
                            view.dismissProgressBar();
                        },
                        error -> view.onError(error)
                );

        subscribe(disposable);
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
