package com.ssilva.task.dagger.applicationmodules;

import com.google.gson.GsonBuilder;
import com.ssilva.task.BuildConfig;
import com.ssilva.task.data.models.AutoValueGsonFactory;
import com.ssilva.task.network.BooksApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    String provideNameBaseUrl() {
        return BuildConfig.Host;
    }

    @Provides
    @Singleton
    Converter.Factory provideGsonConverter () {
        return GsonConverterFactory.create(
                new GsonBuilder().registerTypeAdapterFactory(AutoValueGsonFactory.create())
                        .create());
    }

    @Singleton
    @Provides
    OkHttpClient.Builder provideOkHttpClient() {
        return new OkHttpClient.Builder();
    }


    @Singleton
    @Provides
    Retrofit provideRetrofit(String NAME_BASE_URL, Converter.Factory converterFactory,
                             OkHttpClient.Builder okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(NAME_BASE_URL)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient.build())
                .build();
    }

    @Provides
    @Singleton
    BooksApi provideBooksApi(Retrofit retrofit) {
        return retrofit.create(BooksApi.class);
    }

}
