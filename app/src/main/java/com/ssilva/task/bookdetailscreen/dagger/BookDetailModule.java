package com.ssilva.task.bookdetailscreen.dagger;

import com.ssilva.task.bookdetailscreen.BookDetailPresenter;
import com.ssilva.task.bookdetailscreen.BookDetailViewPresenterContract;
import com.ssilva.task.dagger.scopes.ActivityViewScope;
import com.ssilva.task.data.IDataRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class BookDetailModule {

    @ActivityViewScope
    @Provides
    BookDetailViewPresenterContract.Presenter provideBookDetailPresenter(IDataRepository dataRepository) {
        return new BookDetailPresenter(dataRepository);
    }
}
