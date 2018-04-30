package com.ssilva.task.bookdetailscreen.dagger;

import com.ssilva.task.bookdetailscreen.BookDetailPresenter;
import com.ssilva.task.bookdetailscreen.BookDetailPresenterContract;
import com.ssilva.task.data.IDataRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class BookDetailModule {
    @Singleton
    @Provides
    BookDetailPresenterContract.Presenter provideBookDetailPresenter(IDataRepository dataRepository) {
        return new BookDetailPresenter(dataRepository);
    }
}
