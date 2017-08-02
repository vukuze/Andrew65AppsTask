package com.example.andrew65appstask.dagger.module;

import com.example.andrew65appstask.dagger.scope.SplashScope;
import com.example.andrew65appstask.domain.GetNetworkData;

import dagger.Module;
import dagger.Provides;

@Module
public class NetworkModule {
    @Provides
    @SplashScope
    GetNetworkData provideNetworkData() {
        return new GetNetworkData();
    }

}
