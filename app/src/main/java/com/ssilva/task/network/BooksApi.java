package com.ssilva.task.network;

import com.ssilva.task.model.Book;
import com.ssilva.task.model.BookList;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BooksApi {

    @GET("/books/v1/volumes?q=android&startIndex=0&maxResults=10")
    Call<BookList> getBookList();

    @GET("/books/v1/volumes?q=android&startIndex=0&maxResults=10")
    Single<Response<BookList>> getListOfBooks();

    @GET("/books/v1/volumes/{volumeId}")
    Single<Response<Book>> getBookById(@Path("volumeId") String id);
}
