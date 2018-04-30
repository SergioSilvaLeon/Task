package com.ssilva.task.data;

import com.ssilva.task.bookdetailscreen.BookDetailPresenterContract;
import com.ssilva.task.model.Book;
import com.ssilva.task.model.BookList;

public interface IDataRepository {

    public void getBooksFromApi();

    public void getBookById(String id);

    void setView(BookDetailPresenterContract.Presenter presenter);

    void dropView();

}
