package com.example.mytopmovies.di.sub_module;


import com.example.mytopmovies.data.repository.IRepository;
import com.example.mytopmovies.data.repository.Repository;
import com.example.mytopmovies.domain.IInteractor;
import com.example.mytopmovies.domain.Interactor;
import com.example.mytopmovies.presentation.activity.TopMoviesActivity;
import com.example.mytopmovies.presentation.activity.TopMoviesContract;
import com.example.mytopmovies.presentation.activity.TopMoviesPresenter;



import dagger.Binds;
import dagger.Module;

@Module
public abstract class MainModule {

    @Binds
    abstract IInteractor bindTopMoviesInteractor(Interactor interactor);

    @Binds
    abstract TopMoviesContract.View bindTopMoviesActivity(TopMoviesActivity view);

    @Binds
    abstract TopMoviesContract.Presenter bindTopMoviesPresenter(TopMoviesPresenter presenter);

    @Binds
    abstract IRepository bindTopMoviesRepository(Repository repository);



}