package com.ssilva.task.booklistscreen;


import com.ssilva.task.common.BaseViewPresenterContract;
import com.ssilva.task.data.models.BookList;

import io.reactivex.Observable;

public interface BookListViewPresenterContract {

    interface View extends BaseViewPresenterContract.View {

        void onError(Throwable throwable);

        void onFetchSuccess(BookList listOfBooks);

        void onItemSelected(String id);

        void onSuccessQuery(BookList books);

        Observable<String> getQueryObservable();

        Observable<Integer> getScrollObservable();

        Observable<String> getItemIdObservable();

    }

    interface Presenter extends BaseViewPresenterContract.Presenter {

        void loadMoreListOfBooks();

        void loadBooksByQuery();

        void loadItemSelected();

        void setView(BookListViewPresenterContract.View view);

        void dropView();
    }
}
