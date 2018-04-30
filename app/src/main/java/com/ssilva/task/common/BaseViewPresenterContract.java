package com.ssilva.task.common;

import io.reactivex.disposables.Disposable;

public interface BaseViewPresenterContract {

    interface View {

        void showProgressBar();

        void dismissProgressBar();
    }

    interface Presenter {

        void subscribe(Disposable subscription);

        void unSubscribe();

    }
}
