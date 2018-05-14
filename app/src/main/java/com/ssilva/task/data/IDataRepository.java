package com.ssilva.task.data;

import com.ssilva.task.data.models.Book;
import com.ssilva.task.data.models.BookList;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface IDataRepository {

    Observable<BookList> getBooksByQuery(String query);

    Single<BookList> getBooksFromApi(int startIndex, String query);

    Single<Book> getBookById(String id);

}
