package com.ssilva.task.booklistscreen.dagger;

import com.ssilva.task.booklistscreen.BookListPresenter;
import com.ssilva.task.booklistscreen.BookListViewPresenterContract;
import com.ssilva.task.dagger.scopes.ActivityViewScope;
import com.ssilva.task.data.IDataRepository;


import dagger.Module;
import dagger.Provides;

@Module
public class BookListModule {

    @ActivityViewScope
    @Provides
    BookListViewPresenterContract.Presenter provideBookListPresenter(IDataRepository dataRepository){
        return new BookListPresenter(dataRepository);
    }
}
