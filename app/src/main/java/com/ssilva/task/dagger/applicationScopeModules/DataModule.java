package com.ssilva.task.dagger.applicationScopeModules;

import com.ssilva.task.data.DataRepositoryImpl;
import com.ssilva.task.data.IDataRepository;
import com.ssilva.task.network.BooksApi;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class DataModule {

    @Binds
    @Singleton
    abstract IDataRepository provideDataRepository(DataRepositoryImpl dataRepository);
}
