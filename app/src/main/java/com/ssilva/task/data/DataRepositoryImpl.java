package com.ssilva.task.data;

import com.ssilva.task.model.Book;
import com.ssilva.task.model.BookList;
import com.ssilva.task.network.BooksApi;

import io.reactivex.Single;


public class DataRepositoryImpl implements IDataRepository{

    private BooksApi api;

    public DataRepositoryImpl(BooksApi api) {
        this.api = api;
    }

    @Override
    public Single<BookList> getBooksFromApi() {
        return null;
    }

    @Override
    public Single<Book> getBookById(String id) {
        return api.getBookById(id)
                .map(response -> response.body())
                .doOnError(error -> {throw new RuntimeException(error.getMessage());});
    }

}
