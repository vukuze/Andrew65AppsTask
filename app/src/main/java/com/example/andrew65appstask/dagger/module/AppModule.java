package com.example.andrew65appstask.dagger.module;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.andrew65appstask.dagger.scope.AppScope;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final Context appContext;

    public AppModule(@NonNull Context context) {
        appContext = context;
    }

    @Provides
    @AppScope
    Context provideContext() {
        return appContext;
    }
}
