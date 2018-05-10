package com.ssilva.task.booklistscreen.dagger;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.ssilva.task.booklistscreen.BookListPresenter;
import com.ssilva.task.booklistscreen.BookListViewPresenterContract;
import com.ssilva.task.booklistscreen.adapter.PaginationScroll;
import com.ssilva.task.booklistscreen.adapter.RxSearch;
import com.ssilva.task.dagger.scopes.ActivityViewScope;
import com.ssilva.task.data.IDataRepository;


import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class BookListModule {

    private String welcomeMessage;
    private Context context;

    public BookListModule(String message, Context context) {
        welcomeMessage = message;
        this.context = context;
    }

    @ActivityViewScope
    @Provides
    BookListViewPresenterContract.Presenter provideBookListPresenter(IDataRepository dataRepository){
        return new BookListPresenter(dataRepository);
    }


    @ActivityViewScope
    @Provides
    @Named("welcomeMessage")
    String provideMessage() {
        return welcomeMessage;
    }

    @ActivityViewScope
    @Provides
    LinearLayoutManager provideLinearLayoutManager(Context context) {
        return new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
    }

    @ActivityViewScope
    @Provides
    PaginationScroll provideScrollListener(LinearLayoutManager linearLayoutManager) {
        return new PaginationScroll(linearLayoutManager);
    }

    @ActivityViewScope
    @Provides
    RxSearch provideRxSearch() {
        return new RxSearch();
    }

}
