package com.ssilva.task.dagger;

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

    BookListComponent provideBookListComponent();

}
