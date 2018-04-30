package com.ssilva.task.dagger;

import com.ssilva.task.bookdetailscreen.BookDetailPresenter;
import com.ssilva.task.bookdetailscreen.BookDetailPresenterContract;
import com.ssilva.task.data.IDataRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class BookDetailModule {
   @Provides
    BookDetailPresenterContract.Presenter provideBookDetailPresenter(IDataRepository dataRepository) {
       return new BookDetailPresenter(dataRepository);
   }
}
