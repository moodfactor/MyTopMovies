package com.example.mytopmovies.data;

import com.example.mytopmovies.data.api_model.TopRated;

import io.reactivex.Observable;
import retrofit2.http.GET;

import retrofit2.http.Query;

public interface MovieApiService {

    @GET("top_rated?api_key=c5fde65b68e0722647c44e9699ef00c6")
    Observable<TopRated> getTopRatedMovies( @Query("page") Integer page);

}
