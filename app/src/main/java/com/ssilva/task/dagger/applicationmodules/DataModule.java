package com.ssilva.task.dagger.applicationmodules;

import com.ssilva.task.data.DataRepositoryImpl;
import com.ssilva.task.data.IDataRepository;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class DataModule {

    @Binds
    @Singleton
    abstract IDataRepository provideDataRepository(DataRepositoryImpl dataRepository);
}
