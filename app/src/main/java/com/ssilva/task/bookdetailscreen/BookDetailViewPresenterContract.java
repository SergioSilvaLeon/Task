package com.ssilva.task.bookdetailscreen;

import com.ssilva.task.common.BaseViewPresenterContract;
import com.ssilva.task.data.models.Book;

public interface BookDetailViewPresenterContract {

    interface View extends BaseViewPresenterContract.View{

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
