package com.ssilva.task.bookdetailscreen;

import com.ssilva.task.model.Book;

public interface BookDetailPresenterContract {

    interface View {

        void initViews();

        String getTitleId();

        void onSuccess(Book book);

        void onError(Throwable throwable);

    }

    interface Presenter {

        void getBookInfomation();

        void onBookResponse(Book book);

        void setView(BookDetailPresenterContract.View view);

        void dropView();


    }
}
