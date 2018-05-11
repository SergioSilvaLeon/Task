package com.ssilva.task.network;

import com.ssilva.task.data.models.Book;
import com.ssilva.task.data.models.BookList;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BooksApi {

    String baseUrl = "/books/v1/volumes";

    // ?q=android&startIndex=0&maxResults=10"

    @GET(baseUrl + "?startIndex=0&maxResults=10")
    Observable<Response<BookList>> getListOfBooksByQuery(@Query("q") String query);

    @GET(baseUrl + "?q=android&maxResults=10")
    Single<Response<BookList>> getListOfBooks(@Query("startIndex") int startIndex);

    @GET(baseUrl + "/{volumeId}")
    Single<Response<Book>> getBookById(@Path("volumeId") String id);
}
