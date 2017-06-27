package com.example.andrew65appstask;

import android.app.Application;

import com.example.andrew65appstask.dagger.AppComponent;
import com.example.andrew65appstask.dagger.DaggerAppComponent;
import com.example.andrew65appstask.dagger.DetailsComponent;
import com.example.andrew65appstask.dagger.EmployeeComponent;
import com.example.andrew65appstask.dagger.SpecialtyComponent;
import com.example.andrew65appstask.dagger.SplashComponent;
import com.example.andrew65appstask.dagger.module.AppModule;
import com.example.andrew65appstask.dagger.module.DatabaseModule;
import com.example.andrew65appstask.dagger.module.OkHttpClientModule;
import com.example.andrew65appstask.dagger.module.RetrofitModule;

public class App extends Application {

    private static App appInstance;
    private static AppComponent appComponent;
    private static SplashComponent splashComponent;
    private static SpecialtyComponent specialtyComponent;
    private static EmployeeComponent employeeComponent;
    private static DetailsComponent detailsComponent;

    private static AppComponent getAppComponent() {
        if (appComponent == null)
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(appInstance))
                    .okHttpClientModule(new OkHttpClientModule())
                    .databaseModule(new DatabaseModule(appInstance))
                    .build();
        return appComponent;
    }

    public static SplashComponent getSplashComponent() {
        if (splashComponent == null)
            splashComponent = getAppComponent().plusSplashComponent(new RetrofitModule());
        return splashComponent;
    }

//    public static void releaseSplashComponent() {
//        splashComponent = null;
//    }

    public static SpecialtyComponent getSpecialtyComponent() {
        if (specialtyComponent == null)
            specialtyComponent = getAppComponent().plusSpecialtyComponent();
        return specialtyComponent;
    }

    public static void releaseSpecialtyComponent() {
        specialtyComponent = null;
    }

    public static EmployeeComponent getEmployeeComponent() {
        if (employeeComponent == null)
            employeeComponent = getAppComponent().plusEmployeeComponent();
        return employeeComponent;
    }

    public static void releaseEmployeeComponent() {
        employeeComponent = null;
    }

    public static DetailsComponent getDetailsComponent() {
        if (detailsComponent == null)
            detailsComponent = getAppComponent().plusDetailsComponent();
        return detailsComponent;
    }

    public static void releaseDetailsComponent() {
        detailsComponent = null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;
    }
}