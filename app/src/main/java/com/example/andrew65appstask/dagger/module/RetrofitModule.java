package com.example.andrew65appstask.dagger.module;

import com.example.andrew65appstask.network.FakeNetworkInterceptor;
import com.example.andrew65appstask.network.SixtyFiveAppsRestService;
import com.example.andrew65appstask.dagger.scope.SplashScope;
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
    @SplashScope
    FakeNetworkInterceptor provideFakeNetworkInterceptor() {
        return new FakeNetworkInterceptor();
    }

    @Provides
    @SplashScope
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Provides
    @SplashScope
    Retrofit provideRetrofit(OkHttpClient okHttpClient, Gson gson, FakeNetworkInterceptor fakeNetworkInterceptor) {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient.newBuilder().addInterceptor(fakeNetworkInterceptor).build())
                .baseUrl(SixtyFiveAppsRestService.URL)
                .build();
    }

    @Provides
    @SplashScope
    SixtyFiveAppsRestService provideService(Retrofit retrofit) {
        return retrofit.create(SixtyFiveAppsRestService.class);
    }
}
