package me.andrew.taskpersonnel;

import android.app.Application;

import me.andrew.taskpersonnel.di.AppComponent;
import me.andrew.taskpersonnel.di.DaggerAppComponent;
import me.andrew.taskpersonnel.di.DetailsComponent;
import me.andrew.taskpersonnel.di.EmployeeComponent;
import me.andrew.taskpersonnel.di.SpecialtyComponent;
import me.andrew.taskpersonnel.di.SplashComponent;
import me.andrew.taskpersonnel.di.module.AppModule;
import me.andrew.taskpersonnel.di.module.DatabaseModule;

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