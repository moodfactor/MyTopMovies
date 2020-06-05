package com.example.mytopmovies.di.sub_component;

import com.example.mytopmovies.di.sub_module.MainModule;
import com.example.mytopmovies.presentation.activity.TopMoviesActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BuildersModuleActivity {

    @ContributesAndroidInjector(modules = {MainModule.class})
    abstract TopMoviesActivity bindMainActivity();



}
