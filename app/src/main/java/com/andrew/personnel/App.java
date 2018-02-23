package com.andrew.personnel;

import android.app.Application;

import com.andrew.personnel.di.AppComponent;
import com.andrew.personnel.di.DaggerAppComponent;
import com.andrew.personnel.di.DetailsComponent;
import com.andrew.personnel.di.EmployeeComponent;
import com.andrew.personnel.di.SpecialtyComponent;
import com.andrew.personnel.di.SplashComponent;
import com.andrew.personnel.di.module.AppModule;
import com.andrew.personnel.di.module.DatabaseModule;

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
                    .databaseModule(new DatabaseModule(appInstance))
                    .build();
        return appComponent;
    }

    public static SplashComponent getSplashComponent() {
        if (splashComponent == null)
            splashComponent = getAppComponent().plusSplashComponent();
        return splashComponent;
    }

    public static SpecialtyComponent getSpecialtyComponent() {
        if (specialtyComponent == null)
            specialtyComponent = getAppComponent().plusSpecialtyComponent();
        return specialtyComponent;
    }

    public static EmployeeComponent getEmployeeComponent() {
        if (employeeComponent == null)
            employeeComponent = getAppComponent().plusEmployeeComponent();
        return employeeComponent;
    }

    public static DetailsComponent getDetailsComponent() {
        if (detailsComponent == null)
            detailsComponent = getAppComponent().plusDetailsComponent();
        return detailsComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;
    }
}