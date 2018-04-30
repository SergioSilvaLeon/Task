package com.ssilva.task.data;

import com.ssilva.task.bookdetailscreen.BookDetailPresenterContract;
import com.ssilva.task.model.Book;
import com.ssilva.task.network.BooksApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataRepositoryImpl implements IDataRepository{

    private BooksApi api;
    private BookDetailPresenterContract.Presenter bookDetailPresenter = null;

    public DataRepositoryImpl(BooksApi api) {
        this.api = api;
    }

    @Override
    public void getBooksFromApi() {
    }

    @Override
    public void getBookById(String id) {
        api.getBook(id).enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                bookDetailPresenter.onBookResponse(response.body());
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                bookDetailPresenter.onBookResponse(null);
            }
        });
    }

    @Override
    public void setView(BookDetailPresenterContract.Presenter presenter) {
        bookDetailPresenter = presenter;
    }

    @Override
    public void dropView() {
        bookDetailPresenter = null;
    }

}
