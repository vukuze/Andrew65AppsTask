package me.andrew.taskpersonnel.di.module.splash;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.File;

import dagger.Module;
import dagger.Provides;
import me.andrew.taskpersonnel.di.scope.SplashScope;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

@Module
public class OkHttpClientModule {

    private final String TAG = "OkHttpClientModule";

    @Provides
    @SplashScope
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(message ->
                Log.i(TAG, message));
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        return httpLoggingInterceptor;
    }

    @Provides
    @SplashScope
    File provideFile(@NonNull Context context) {
        return new File(context.getCacheDir(), "ok_http_cache");
    }

    @Provides
    @SplashScope
    Cache provideCache(File file) {
        return new Cache(file, 10 * 1000 * 1000); // 10MB cache
    }

    @Provides
    @SplashScope
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor, Cache cache) {
        return new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }
}
