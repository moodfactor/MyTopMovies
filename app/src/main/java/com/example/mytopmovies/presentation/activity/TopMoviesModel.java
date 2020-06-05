package com.example.mytopmovies.presentation.activity;

import com.example.mytopmovies.data.BaseModel;
import com.example.mytopmovies.data.api_model.Result;
import com.example.mytopmovies.repository.Repository;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;

public class TopMoviesModel implements TopMoviesContract.Model {

    private Repository repository;

    @Inject
    public TopMoviesModel(Repository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<BaseModel> result() {
        return Observable.zip(
                repository.getResultData(),
                repository.getCountryData(),
                new BiFunction<Result, String, BaseModel>() {
                    @Override
                    public BaseModel apply(Result result, String s) {
                        return new BaseModel(result.title, s);
                    }
                }
        );
    }

}

