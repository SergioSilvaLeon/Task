package com.ssilva.task.dagger;

import com.ssilva.task.data.DataRepositoryImpl;
import com.ssilva.task.data.IDataRepository;
import com.ssilva.task.network.BooksApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

    @Provides
    @Singleton
    IDataRepository provideDataRepository(BooksApi api) {
        return new DataRepositoryImpl(api);
    }


}
