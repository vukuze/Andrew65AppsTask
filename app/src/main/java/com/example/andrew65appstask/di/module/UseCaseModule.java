package com.example.andrew65appstask.di.module;

import com.example.andrew65appstask.di.scope.SplashScope;
import com.example.andrew65appstask.data.Repository;
import com.example.andrew65appstask.domain.UpdateData;

import dagger.Module;
import dagger.Provides;

@Module
public class UseCaseModule {

    @Provides
    @SplashScope
    UpdateData provideNetworkData(Repository repository) {
        return new UpdateData(repository);
    }

}
