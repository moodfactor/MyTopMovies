package com.example.mytopmovies.domain;

import com.example.mytopmovies.data.BaseModel;
import com.example.mytopmovies.data.api_model.Result;
import com.example.mytopmovies.data.repository.Repository;


import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;

public class Interactor implements IInteractor {

    Repository repository;

    @Inject
    public Interactor(Repository repository) {
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
