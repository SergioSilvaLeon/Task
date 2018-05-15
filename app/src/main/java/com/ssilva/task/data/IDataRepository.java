package com.ssilva.task.data;

import com.ssilva.task.data.models.Book;
import com.ssilva.task.data.models.BookList;

import io.reactivex.Single;

public interface IDataRepository {

    Single<BookList> getBooksFromApi(int startIndex, String query);

    Single<Book> getBookById(String id);

}
