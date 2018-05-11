package com.ssilva.task.data;

import com.ssilva.task.data.models.Book;
import com.ssilva.task.data.models.BookList;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface IDataRepository {

    public Observable<BookList> getBooksByQuery(String query);

    public Single<BookList> getBooksFromApi(int startIndex);

    public Single<Book> getBookById(String id);

}
