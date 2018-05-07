package com.ssilva.task.bookdetailscreen.dagger;

import com.ssilva.task.bookdetailscreen.BookDetailActivity;
import com.ssilva.task.bookdetailscreen.dagger.BookDetailModule;
import com.ssilva.task.dagger.scopes.ActivityViewScope;

import dagger.Subcomponent;

@ActivityViewScope
@Subcomponent(modules = {BookDetailModule.class})
public interface BookDetailComponent {
    void inject(BookDetailActivity target);
}
