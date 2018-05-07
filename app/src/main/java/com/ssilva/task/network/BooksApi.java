package com.ssilva.task.network;

import com.ssilva.task.data.models.Book;
import com.ssilva.task.data.models.BookList;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BooksApi {

    String baseUrl = "/books/v1/volumes";


    @GET(baseUrl + "?q=android&startIndex=0&maxResults=10")
    Single<Response<BookList>> getListOfBooks();

    @GET(baseUrl + "/{volumeId}")
    Single<Response<Book>> getBookById(@Path("volumeId") String id);
}
