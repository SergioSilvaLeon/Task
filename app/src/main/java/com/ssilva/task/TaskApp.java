package com.ssilva.task;

import android.app.Application;

import com.ssilva.task.dagger.AppComponent;
import com.ssilva.task.dagger.DaggerAppComponent;


public class TaskApp extends Application {
    public static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerAppComponent
                .builder()
                .build();

    }
}
