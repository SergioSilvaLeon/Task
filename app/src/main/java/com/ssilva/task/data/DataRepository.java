package com.ssilva.task.data;

import com.ssilva.task.data.models.Book;
import com.ssilva.task.data.models.BookList;
import com.ssilva.task.network.BooksApi;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;


public class DataRepository implements IDataRepository{

    private BooksApi api;

    @Inject
    public DataRepository(BooksApi api) {
        this.api = api;
    }

    @Override
    public Single<BookList> getBooksFromApi(int startIndex, String query) {
        return api.getListOfBooks(startIndex, query)
                .subscribeOn(Schedulers.io())
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
