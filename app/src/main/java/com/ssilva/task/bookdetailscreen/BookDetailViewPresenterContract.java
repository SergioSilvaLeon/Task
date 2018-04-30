package com.ssilva.task.bookdetailscreen;

import com.ssilva.task.common.BaseViewPresenterContract;
import com.ssilva.task.model.Book;

public interface BookDetailViewPresenterContract {

    interface View extends BaseViewPresenterContract.View{

        void initViews();

        String getTitleId();

        void onSuccess(Book book);

        void onError(Throwable throwable);

    }

    interface Presenter extends BaseViewPresenterContract.Presenter{

        void getBookInformation();

        void setView(BookDetailViewPresenterContract.View view);

        void dropView();

    }
}
