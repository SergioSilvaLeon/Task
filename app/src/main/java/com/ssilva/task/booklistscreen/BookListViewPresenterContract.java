package com.ssilva.task.booklistscreen;


import com.ssilva.task.common.BaseViewPresenterContract;
import com.ssilva.task.data.models.BookList;

import io.reactivex.Observable;

public interface BookListViewPresenterContract {

    interface View extends BaseViewPresenterContract.View {

        void onError(Throwable throwable);

        void onSuccess(BookList listOfBooks);

        void onFetchSuccess(BookList listOfBooks);

        void onSuccessQuery(BookList books);

    }

    interface Presenter extends BaseViewPresenterContract.Presenter {

        void loadListOfBooks ();

        void loadMoreListOfBooks (int startIndex);

        void loadBooksByQuery(Observable<String> query);

        void setView(BookListViewPresenterContract.View view);

        void dropView();
    }
}
