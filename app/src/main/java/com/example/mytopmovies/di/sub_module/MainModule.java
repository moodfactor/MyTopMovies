package com.example.mytopmovies.di.sub_module;

import com.example.mytopmovies.data.MoreInfoApiService;
import com.example.mytopmovies.data.MovieApiService;
import com.example.mytopmovies.presentation.activity.TopMoviesActivity;
import com.example.mytopmovies.presentation.activity.TopMoviesContract;
import com.example.mytopmovies.presentation.activity.TopMoviesModel;
import com.example.mytopmovies.presentation.activity.TopMoviesPresenter;
import com.example.mytopmovies.repository.IRepository;
import com.example.mytopmovies.repository.Repository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class MainModule {

    @Binds
    abstract TopMoviesContract.Model bindTopMoviesModel(TopMoviesModel model);

    @Binds
    abstract TopMoviesContract.View bindTopMoviesActivity(TopMoviesActivity view);

    @Binds
    abstract TopMoviesContract.Presenter bindTopMoviesPresenter(TopMoviesPresenter presenter);

//    @Binds
//    abstract IRepository bindTopMoviesRepository(MovieApiService movieApiService);



}