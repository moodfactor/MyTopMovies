package com.example.mytopmovies.data;

import com.example.mytopmovies.data.api_model.OmdbApi;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoreInfoApiService {

    @GET("/?apikey=46f911d7&")
    Observable<OmdbApi> getCountry(@Query("t") String title);

}
