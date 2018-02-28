package me.andrew.taskpersonnel.di;

import dagger.Component;
import me.andrew.taskpersonnel.di.module.AppModule;
import me.andrew.taskpersonnel.di.module.DatabaseModule;
import me.andrew.taskpersonnel.di.module.NavigationModule;
import me.andrew.taskpersonnel.di.module.splash.RepositoryModule;
import me.andrew.taskpersonnel.di.scope.AppScope;

@AppScope
@Component(modules = {AppModule.class, DatabaseModule.class, NavigationModule.class, RepositoryModule.class})
public interface AppComponent {
    SplashComponent plusSplashComponent();

    SpecialtyComponent plusSpecialtyComponent();

    EmployeeComponent plusEmployeeComponent();

    DetailsComponent plusDetailsComponent();
}
