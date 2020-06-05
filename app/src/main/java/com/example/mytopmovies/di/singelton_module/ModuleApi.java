package com.example.mytopmovies.di.singelton_module;

import androidx.databinding.library.baseAdapters.BuildConfig;

import com.example.mytopmovies.data.ApiService;
import com.example.mytopmovies.data.NewApiService;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public abstract class ModuleApi {

    @Singleton
    @Provides
    static OkHttpClient provideOkHttpClient(){
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);
        }
        builder.connectTimeout(60 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(60 * 1000, TimeUnit.MILLISECONDS);
        return builder.build();
    }


    @Singleton
    @Provides
    static Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder
                .setLenient()
                .create();
    }

    @Singleton
    @Provides
    static ApiService provideApiService(Gson gson, OkHttpClient okHttpClient) {
        return createApi(ApiService.class, okHttpClient, GsonConverterFactory.create(gson));
    }

    @Singleton
    @Provides
    static NewApiService provideNewApiService(Gson gson, OkHttpClient okHttpClient) {
        return createApi(NewApiService.class, okHttpClient, GsonConverterFactory.create(gson));
    }

    private static <T> T createApi(Class<T> serviceApi, OkHttpClient okHttpClient, Converter.Factory factoryConverter) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.client(okHttpClient)
                .baseUrl("")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(factoryConverter);
        return builder.build().create(serviceApi);
    }


}

