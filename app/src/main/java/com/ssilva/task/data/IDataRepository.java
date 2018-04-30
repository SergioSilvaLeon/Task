package com.ssilva.task.data;

import com.ssilva.task.model.Book;
import com.ssilva.task.model.BookList;

import io.reactivex.Single;

public interface IDataRepository {

    public Single<BookList> getBooksFromApi();

    public Single<Book> getBookById(String id);

}
