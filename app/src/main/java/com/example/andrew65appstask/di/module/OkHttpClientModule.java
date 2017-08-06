package com.example.andrew65appstask.di.module;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.andrew65appstask.di.scope.AppScope;

import java.io.File;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

@Module
public class OkHttpClientModule {

    private final String TAG = "OkHttpClientModule";

    @Provides
    @AppScope
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(message ->
                Log.i(TAG, message));
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        return httpLoggingInterceptor;
    }

    @Provides
    @AppScope
    File provideFile(@NonNull Context context){
        return new File(context.getCacheDir(), "ok_http_cache");
    }

    @Provides
    @AppScope
    Cache provideCache(File file) {
        return new Cache(file, 10 * 1000 * 1000); // 10MB cache
    }

    @Provides
    @AppScope
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor, Cache cache) {
        return new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }
}
