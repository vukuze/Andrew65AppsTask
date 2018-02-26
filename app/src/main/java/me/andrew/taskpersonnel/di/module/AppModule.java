package me.andrew.taskpersonnel.di.module;

import android.content.Context;
import android.support.annotation.NonNull;

import me.andrew.taskpersonnel.di.scope.AppScope;

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
