package com.example.andrew65appstask.di.module;

import com.example.andrew65appstask.di.scope.AppScope;
import com.example.andrew65appstask.data.network.FakeNetworkInterceptor;
import com.example.andrew65appstask.data.network.SixtyFiveAppsRestService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule {

    @Provides
    @AppScope
    FakeNetworkInterceptor provideFakeNetworkInterceptor() {
        return new FakeNetworkInterceptor();
    }

    @Provides
    @AppScope
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Provides
    @AppScope
    Retrofit provideRetrofit(OkHttpClient okHttpClient, Gson gson, FakeNetworkInterceptor fakeNetworkInterceptor) {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient.newBuilder().addInterceptor(fakeNetworkInterceptor).build())
                .baseUrl(SixtyFiveAppsRestService.URL)
                .build();
    }

    @Provides
    @AppScope
    SixtyFiveAppsRestService provideService(Retrofit retrofit) {
        return retrofit.create(SixtyFiveAppsRestService.class);
    }
}
