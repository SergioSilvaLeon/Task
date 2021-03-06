package com.ssilva.task.dagger;

import com.ssilva.task.bookdetailscreen.dagger.BookDetailComponent;
import com.ssilva.task.booklistscreen.dagger.BookListComponent;
import com.ssilva.task.dagger.applicationmodules.AppModule;
import com.ssilva.task.dagger.applicationmodules.DataModule;
import com.ssilva.task.dagger.applicationmodules.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
        NetworkModule.class,
        DataModule.class
})
public interface AppComponent {

    BookDetailComponent provideBookDetailComponent();

    BookListComponent.Builder provideBookListComponentBuilder();

}
