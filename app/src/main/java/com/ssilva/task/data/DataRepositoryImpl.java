package com.ssilva.task.data;

import com.ssilva.task.model.Book;
import com.ssilva.task.model.BookList;
import com.ssilva.task.network.BooksApi;

import javax.inject.Inject;

import io.reactivex.Single;


public class DataRepositoryImpl implements IDataRepository{

    private BooksApi api;

    @Inject
    public DataRepositoryImpl(BooksApi api) {
        this.api = api;
    }

    @Override
    public Single<BookList> getBooksFromApi() {
        return api.getListOfBooks()
                .map(response -> response.body())
                .doOnError(error -> {throw new RuntimeException(error.getMessage());});
    }

    @Override
    public Single<Book> getBookById(String id) {
        return api.getBookById(id)
                .map(response -> response.body())
                .doOnError(error -> {throw new RuntimeException(error.getMessage());});
    }

}
