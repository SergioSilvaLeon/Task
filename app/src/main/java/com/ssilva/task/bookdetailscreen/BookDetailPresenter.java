package com.ssilva.task.bookdetailscreen;

import com.ssilva.task.common.RxBasePresenter;
import com.ssilva.task.data.IDataRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BookDetailPresenter extends RxBasePresenter implements BookDetailViewPresenterContract.Presenter {

    private IDataRepository dataRepository;
    private BookDetailViewPresenterContract.View bookDetailView = null;

    public BookDetailPresenter(IDataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @Override
    public void getBookInformation() {

        bookDetailView.showProgressBar();

        Disposable disposable = dataRepository.getBookById(bookDetailView.getTitleId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable1 -> bookDetailView.dismissProgressBar())
                .subscribe(
                        book -> bookDetailView.onSuccess(book),
                        error -> bookDetailView.onError(error));

        subscribe(disposable);
    }


    @Override
    public void setView(BookDetailViewPresenterContract.View view) {
        bookDetailView = view;
    }

    @Override
    public void dropView() {
        bookDetailView = null;
    }
}
