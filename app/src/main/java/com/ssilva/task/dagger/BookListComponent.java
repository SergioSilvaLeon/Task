package com.ssilva.task.dagger;

import com.ssilva.task.booklistscreen.BookListActivity;
import com.ssilva.task.booklistscreen.dagger.BookListModule;

import dagger.Subcomponent;

@Subcomponent(modules = {BookListModule.class})
public interface BookListComponent {
    void inject(BookListActivity target);
}
