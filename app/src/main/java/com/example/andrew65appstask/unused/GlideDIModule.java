//package com.example.andrew65appstask.employee.details;

//import dagger.Module;

//@Module
//public class GlideDIModule {

//    @Provides
//    @DetailsScope
//    OkHttp3Downloader provideOkHttp3Downloader(OkHttpClient okHttpClient) {
//        OkHttp3Downloader okHttp3Downloader = new OkHttp3Downloader(okHttpClient);
//        return okHttp3Downloader;
//    }

//    @Provides
//    @DetailsScope
//    Glide provideGlide(@NonNull Context context, OkHttp3Downloader okHttp3Downloader) {
//    Glide provideGlide(@NonNull Context context) {
//        return new GlideDIModule().provideGlide(context)
//                .indicatorsEnabled(true)
//                .loggingEnabled(true)
//                .listener((picasso, uri, exception) -> exception.printStackTrace())
//                .build();
//    }

//}
