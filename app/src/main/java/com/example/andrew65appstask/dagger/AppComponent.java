package com.example.andrew65appstask.dagger;

import com.example.andrew65appstask.dagger.module.AppModule;
import com.example.andrew65appstask.dagger.module.DatabaseModule;
import com.example.andrew65appstask.dagger.module.NavigationModule;
import com.example.andrew65appstask.dagger.module.OkHttpClientModule;
import com.example.andrew65appstask.dagger.module.RetrofitModule;
import com.example.andrew65appstask.dagger.scope.AppScope;

import dagger.Component;

@AppScope
@Component(modules = {AppModule.class, OkHttpClientModule.class, DatabaseModule.class, NavigationModule.class})
public interface AppComponent {
    SplashComponent plusSplashComponent(RetrofitModule retrofitModule);

    SpecialtyComponent plusSpecialtyComponent();

    EmployeeComponent plusEmployeeComponent();

    DetailsComponent plusDetailsComponent();
}
