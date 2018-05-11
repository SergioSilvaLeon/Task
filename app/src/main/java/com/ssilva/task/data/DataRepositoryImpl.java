package com.ssilva.task.data;

import com.ssilva.task.data.models.Book;
import com.ssilva.task.data.models.BookList;
import com.ssilva.task.network.BooksApi;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;


public class DataRepositoryImpl implements IDataRepository{

    private BooksApi api;

    @Inject
    public DataRepositoryImpl(BooksApi api) {
        this.api = api;
    }

    @Override
    public Observable<BookList> getBooksByQuery(String query) {
        return api.getListOfBooksByQuery(query)
                .subscribeOn(Schedulers.io())
                .map(response -> response.body())
                .doOnError(error -> {throw new RuntimeException(error.getMessage());});
    }

    @Override
    public Single<BookList> getBooksFromApi(int startIndex) {
        return api.getListOfBooks(startIndex)
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
