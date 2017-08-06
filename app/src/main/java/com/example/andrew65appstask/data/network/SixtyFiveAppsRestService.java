package com.example.andrew65appstask.data.network;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface SixtyFiveAppsRestService {
    String URL = "http://65apps.com/images/";

    @GET("testTask.json")
    Single<Response> getFile();
}
