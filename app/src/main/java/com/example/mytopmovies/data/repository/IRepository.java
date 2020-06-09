package com.example.mytopmovies.data.repository;

import com.example.mytopmovies.data.api_model.Result;

import io.reactivex.Observable;

public interface IRepository {

    Observable<Result> getResultsFromMemory();

    Observable<Result> getResultsFromNetwork(int page);

    Observable<String> getCountriesFromMemory();

    Observable<String> getCountriesFromNetwork(int page);

    Observable<String> getCountryData(int page);

    Observable<Result> getResultData(int page);
}
