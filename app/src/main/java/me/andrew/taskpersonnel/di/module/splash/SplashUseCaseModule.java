package me.andrew.taskpersonnel.di.module.splash;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;
import me.andrew.taskpersonnel.data.Repository;
import me.andrew.taskpersonnel.data.network.SixtyFiveAppsRestService;
import me.andrew.taskpersonnel.di.scope.SplashScope;
import me.andrew.taskpersonnel.domain.UpdateData;

@Module
public class SplashUseCaseModule {
    @Provides
    @SplashScope
    UpdateData provideNetworkData(@NonNull Repository repository, @NonNull SixtyFiveAppsRestService service) {
        return new UpdateData(repository, service);
    }
}
