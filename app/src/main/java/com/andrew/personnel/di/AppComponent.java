package com.andrew.personnel.di;

import com.andrew.personnel.di.module.AppModule;
import com.andrew.personnel.di.module.DatabaseModule;
import com.andrew.personnel.di.module.NavigationModule;
import com.andrew.personnel.di.module.OkHttpClientModule;
import com.andrew.personnel.di.module.RepositoryModule;
import com.andrew.personnel.di.module.RetrofitModule;
import com.andrew.personnel.di.module.UseCaseModule;
import com.andrew.personnel.di.scope.AppScope;

import dagger.Component;

@AppScope
@Component(modules = {AppModule.class, OkHttpClientModule.class, DatabaseModule.class, NavigationModule.class, RetrofitModule.class, RepositoryModule.class, UseCaseModule.class})
public interface AppComponent {
    SplashComponent plusSplashComponent();

    SpecialtyComponent plusSpecialtyComponent();

    EmployeeComponent plusEmployeeComponent();

    DetailsComponent plusDetailsComponent();
}
