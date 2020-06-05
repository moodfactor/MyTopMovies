package com.example.mytopmovies.data.repository;

import com.example.mytopmovies.data.api_model.Result;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class  Repository implements IRepository {

    private List<String> countries;
    private List<Result> results;
    private long timestamp;

    private static final long STALE_MS = 20 * 1000; // Data is stale after 20 seconds


    @Inject
    public Repository() {
        this.timestamp = System.currentTimeMillis();
        countries = new ArrayList<>();
        results = new ArrayList<>();
    }

    public boolean isUpToDate() {
        return System.currentTimeMillis() - timestamp < STALE_MS;
    }

    @Override
    public Observable<Result> getResultsFromMemory() {
        if (isUpToDate()) {
            return Observable.fromIterable(results);
        } else {
            timestamp = System.currentTimeMillis();
            results.clear();
            return Observable.empty();
        }
    }

    @Override
    public Observable<Result> getResultsFromNetwork() {

//        Observable<TopRated> topRatedObservable = movieApiService.getTopRatedMovies("c5fde65b68e0722647c44e9699ef00c6", 1).concatWith(movieApiService.getTopRatedMovies("c5fde65b68e0722647c44e9699ef00c6", 2)).concatWith(movieApiService.getTopRatedMovies("c5fde65b68e0722647c44e9699ef00c6", 3));
//
//        return topRatedObservable.concatMap(new Function<TopRated, Observable<Result>>() {
//            @Override
//            public Observable<Result> apply(TopRated topRated) {
//                return Observable.fromIterable(topRated.results);
//            }
//        }).doOnNext(new Consumer<Result>() {
//            @Override
//            public void accept(Result result) {
//                results.add(result);
//            }
//        });
        return null;
    }

    @Override
    public Observable<String> getCountriesFromMemory() {
        if (isUpToDate()) {
            return Observable.fromIterable(countries);
        } else {
            timestamp = System.currentTimeMillis();
            countries.clear();
            return Observable.empty();
        }
    }

    @Override
    public Observable<String> getCountriesFromNetwork() {
//        return getResultsFromNetwork().concatMap(new Function<Result, Observable<OmdbApi>>() {
//            @Override
//            public Observable<OmdbApi> apply(Result result) {
//                return moreInfoApiService.getCountry(result.title);
//            }
//        }).concatMap(new Function<OmdbApi, Observable<String>>() {
//            @Override
//            public Observable<String> apply(OmdbApi omdbApi) {
//                return Observable.just(omdbApi.getCountry());
//            }
//        }).doOnNext(new Consumer<String>() {
//            @Override
//            public void accept(String s) {
//                countries.add(s);
//            }
//        });
        return null;
    }

    @Override
    public Observable<String> getCountryData() {
        return getCountriesFromMemory().switchIfEmpty(getCountriesFromNetwork());
    }

    @Override
    public Observable<Result> getResultData() {
        return getResultsFromMemory().switchIfEmpty(getResultsFromNetwork());
    }
}
