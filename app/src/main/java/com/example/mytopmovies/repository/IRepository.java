package com.example.mytopmovies.repository;

import com.example.mytopmovies.data.api_model.Result;

import io.reactivex.Observable;

public interface IRepository {
    Observable<Result> getResultsFromMemory();

    Observable<Result> getResultsFromNetwork();

    Observable<String> getCountriesFromMemory();

    Observable<String> getCountriesFromNetwork();

    Observable<String> getCountryData();

    Observable<Result> getResultData();
}
