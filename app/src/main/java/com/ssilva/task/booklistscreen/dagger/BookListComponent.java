package com.ssilva.task.booklistscreen.dagger;

import com.ssilva.task.booklistscreen.BookListActivity;
import com.ssilva.task.booklistscreen.dagger.BookListModule;
import com.ssilva.task.dagger.scopes.ActivityViewScope;

import dagger.Subcomponent;

@ActivityViewScope
@Subcomponent(modules = {BookListModule.class})
public interface BookListComponent {

    @Subcomponent.Builder
    interface Builder {
        BookListComponent.Builder bookDetailComponentBuilder(BookListModule module);
        BookListComponent build();
    }

    void inject(BookListActivity target);
}
