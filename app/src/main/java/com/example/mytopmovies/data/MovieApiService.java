package com.example.mytopmovies.data;

import com.example.mytopmovies.data.api_model.TopRated;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApiService {

    @GET("top_rated?api_key={api_key}")
    Observable<TopRated> getTopRatedMovies(@Query("api_key") String api_key, @Query("page") Integer page);

}
