package me.andrew.taskpersonnel.di;

import me.andrew.taskpersonnel.di.module.AppModule;
import me.andrew.taskpersonnel.di.module.DatabaseModule;
import me.andrew.taskpersonnel.di.module.NavigationModule;
import me.andrew.taskpersonnel.di.module.OkHttpClientModule;
import me.andrew.taskpersonnel.di.module.RepositoryModule;
import me.andrew.taskpersonnel.di.module.RetrofitModule;
import me.andrew.taskpersonnel.di.module.UseCaseModule;
import me.andrew.taskpersonnel.di.scope.AppScope;

import dagger.Component;

@AppScope
@Component(modules = {AppModule.class, OkHttpClientModule.class, DatabaseModule.class, NavigationModule.class, RetrofitModule.class, RepositoryModule.class, UseCaseModule.class})
public interface AppComponent {
    SplashComponent plusSplashComponent();

    SpecialtyComponent plusSpecialtyComponent();

    EmployeeComponent plusEmployeeComponent();

    DetailsComponent plusDetailsComponent();
}
