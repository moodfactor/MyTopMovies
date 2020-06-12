package com.example.mytopmovies.domain;

import com.example.mytopmovies.data.BaseModel;
import com.example.mytopmovies.data.api_model.Result;
import com.example.mytopmovies.data.repository.Repository;


import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;

public class Interactor implements IInteractor {

    private Repository repository;
    private int currentPage = 1;

    @Inject
    public Interactor(Repository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<BaseModel> result() {
        return Observable.zip(
                repository.getResultData(1),
                repository.getCountryData(1),
                new BiFunction<Result, String, BaseModel>() {
                    @Override
                    public BaseModel apply(Result result, String s) {
                        return new BaseModel(result.title, s,result.id);
                    }
                }
        );
    }

    @Override
    public Observable<BaseModel> loadNextPage() {
        currentPage++;
        return Observable.zip(
                repository.getResultData(currentPage),
                repository.getCountryData(currentPage),
                new BiFunction<Result, String, BaseModel>() {
                    @Override
                    public BaseModel apply(Result result, String s) {
                        return new BaseModel(result.title, s,result.id);

                    }
                }
        );
    }

}
