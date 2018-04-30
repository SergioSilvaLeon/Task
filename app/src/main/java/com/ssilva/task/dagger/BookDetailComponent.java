package com.ssilva.task.dagger;

import com.ssilva.task.bookdetailscreen.BookDetailActivity;
import com.ssilva.task.bookdetailscreen.dagger.BookDetailModule;

import dagger.Subcomponent;

@Subcomponent(modules = {BookDetailModule.class})
public interface BookDetailComponent {
    void inject(BookDetailActivity target);
}
