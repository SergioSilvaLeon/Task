package com.ssilva.task.dagger;

import com.ssilva.task.booklistscreen.BookListActivity;
import com.ssilva.task.booklistscreen.dagger.BookListModule;
import com.ssilva.task.dagger.scopes.ActivityViewScope;

import dagger.Subcomponent;

@ActivityViewScope
@Subcomponent(modules = {BookListModule.class})
public interface BookListComponent {
    void inject(BookListActivity target);
}
