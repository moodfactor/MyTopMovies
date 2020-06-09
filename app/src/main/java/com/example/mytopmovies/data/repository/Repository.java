package com.example.mytopmovies.data.repository;

import android.annotation.SuppressLint;

import com.example.mytopmovies.ConstantsApp;
import com.example.mytopmovies.data.MoreInfoApiService;
import com.example.mytopmovies.data.MovieApiService;
import com.example.mytopmovies.data.api_model.OmdbApi;
import com.example.mytopmovies.data.api_model.Result;
import com.example.mytopmovies.data.api_model.TopRated;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import timber.log.Timber;

public class  Repository implements IRepository {

    private List<String> countries;
    private List<Result> results;
    private long timestamp;

    @Inject
    MovieApiService movieApiService;
    @Inject
    MoreInfoApiService moreInfoApiService;

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
    public Observable<Result> getResultsFromNetwork(int page) {

        Observable<TopRated> topRatedObservable = movieApiService.getTopRatedMovies(page);
        return topRatedObservable.concatMap(new Function<TopRated, Observable<Result>>() {
            @Override
            public Observable<Result> apply(TopRated topRated) {
                return Observable.fromIterable(topRated.results);
            }
        }).doOnNext(new Consumer<Result>() {
            @Override
            public void accept(Result result) {
                results.add(result);

            }
        });
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
    public Observable<String> getCountriesFromNetwork(int page) {
        return getResultsFromNetwork(page).concatMap(new Function<Result, Observable<OmdbApi>>() {
            @Override
            public Observable<OmdbApi> apply(Result result) {
                return moreInfoApiService.getCountry(result.title);
            }
        }).concatMap(new Function<OmdbApi, Observable<String>>() {
            @Override
            public Observable<String> apply(OmdbApi omdbApi) {
                return Observable.just(omdbApi.getCountry());
            }
        }).doOnNext(new Consumer<String>() {
            @Override
            public void accept(String s) {
                countries.add(s);
            }
        });

    }

    @Override
    public Observable<String> getCountryData(int page) {
        return getCountriesFromMemory().switchIfEmpty(getCountriesFromNetwork(page));
    }

    @Override
    public Observable<Result> getResultData(int page) {
        return getResultsFromMemory().switchIfEmpty(getResultsFromNetwork(page));
    }
}

