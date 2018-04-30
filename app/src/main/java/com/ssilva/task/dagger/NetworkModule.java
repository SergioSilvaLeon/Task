package com.ssilva.task.dagger;

import com.ssilva.task.network.BooksApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    private static final String NAMEBASEURL = "https://www.googleapis.com";

    @Provides
    @Singleton
    String provideNameBaseUrl() {
        return NAMEBASEURL;
    }

    @Provides
    @Singleton
    Converter.Factory provideGsonConverter () {
        return GsonConverterFactory.create();
    }

    @Singleton
    @Provides
    OkHttpClient.Builder provideOkHttpClient() {
        return new OkHttpClient.Builder();
    }


    @Singleton
    @Provides
    Retrofit provideRetrofit(String NAME_BASE_URL, Converter.Factory factory,
                             OkHttpClient.Builder okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(NAME_BASE_URL)
                .addConverterFactory(factory)
                .client(okHttpClient.build())
                .build();
    }

    @Provides
    @Singleton
    BooksApi provideBooksApi(Retrofit retrofit) {
        return retrofit.create(BooksApi.class);
    }

}
