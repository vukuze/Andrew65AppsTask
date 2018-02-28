package me.andrew.taskpersonnel.di;

import dagger.Component;
import me.andrew.taskpersonnel.di.module.AppModule;
import me.andrew.taskpersonnel.di.module.DatabaseModule;
import me.andrew.taskpersonnel.di.module.NavigationModule;
import me.andrew.taskpersonnel.di.module.OkHttpClientModule;
import me.andrew.taskpersonnel.di.module.RepositoryModule;
import me.andrew.taskpersonnel.di.module.RetrofitModule;
import me.andrew.taskpersonnel.di.module.UseCaseModule;
import me.andrew.taskpersonnel.di.scope.AppScope;

@AppScope
@Component(modules = {AppModule.class, DatabaseModule.class, NavigationModule.class, OkHttpClientModule.class, RetrofitModule.class, RepositoryModule.class, UseCaseModule.class})
public interface AppComponent {
    SplashComponent plusSplashComponent();

    SpecialtyComponent plusSpecialtyComponent();

    EmployeeComponent plusEmployeeComponent();

    DetailsComponent plusDetailsComponent();
}
