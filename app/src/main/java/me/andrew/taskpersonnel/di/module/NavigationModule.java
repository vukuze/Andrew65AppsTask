package me.andrew.taskpersonnel.di.module;

import android.util.Log;

import me.andrew.taskpersonnel.di.scope.AppScope;

import dagger.Module;
import dagger.Provides;
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;

@Module
public class NavigationModule {

    private static final String TAG = "NavigationModule";

    private Cicerone<Router> cicerone;

    public NavigationModule() {
        Log.d("NavigationModule", "constructor");
        cicerone = Cicerone.create();
    }

    @Provides
    @AppScope
    Router provideRouter() {
        Log.d(TAG, "provideRouter");
        return cicerone.getRouter();
    }

    @Provides
    @AppScope
    NavigatorHolder provideNavigatorHolder() {
        Log.d(TAG, "provideNavigatorHolder");
        return cicerone.getNavigatorHolder();
    }
}
