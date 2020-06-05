package com.example.mytopmovies.di;

import com.example.mytopmovies.App;
import com.example.mytopmovies.di.singelton_module.ModuleApi;
import com.example.mytopmovies.di.singelton_module.ModuleApp;
import com.example.mytopmovies.di.sub_component.BuildersModuleActivity;

import javax.inject.Singleton;

import dagger.Component;

import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;


@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ModuleApi.class,
        ModuleApp.class,
        BuildersModuleActivity.class
})
public interface AppComponent extends AndroidInjector<App> {
    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<App> {
    }
}
