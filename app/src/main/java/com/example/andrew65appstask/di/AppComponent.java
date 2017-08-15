package com.example.andrew65appstask.di;

import com.example.andrew65appstask.di.module.AppModule;
import com.example.andrew65appstask.di.module.DatabaseModule;
import com.example.andrew65appstask.di.module.NavigationModule;
import com.example.andrew65appstask.di.module.OkHttpClientModule;
import com.example.andrew65appstask.di.module.RepositoryModule;
import com.example.andrew65appstask.di.module.RetrofitModule;
import com.example.andrew65appstask.di.module.UseCaseModule;
import com.example.andrew65appstask.di.scope.AppScope;

import dagger.Component;

@AppScope
@Component(modules = {AppModule.class, OkHttpClientModule.class, DatabaseModule.class, NavigationModule.class, RetrofitModule.class, RepositoryModule.class, UseCaseModule.class})
public interface AppComponent {
    SpecialtyComponent plusSpecialtyComponent();

    EmployeeComponent plusEmployeeComponent();

    DetailsComponent plusDetailsComponent();
}
