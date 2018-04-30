package com.ssilva.task.booklistscreen;


import com.ssilva.task.common.BaseViewPresenterContract;
import com.ssilva.task.model.BookList;

public interface BookListViewPresenterContract {

    interface View extends BaseViewPresenterContract.View {

        void onError(Throwable throwable);

        void onSuccess(BookList listOfBooks);

    }

    interface Presenter extends BaseViewPresenterContract.Presenter {

        void loadListOfBooks ();

        void setView(BookListViewPresenterContract.View view);

        void dropView();
    }
}
