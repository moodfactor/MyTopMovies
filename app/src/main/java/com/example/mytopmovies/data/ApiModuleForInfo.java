//package com.example.mytopmovies.data;
//
//import androidx.annotation.NonNull;
//
//import java.io.IOException;
//
//import dagger.Module;
//import dagger.Provides;
//import okhttp3.Interceptor;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//import okhttp3.logging.HttpLoggingInterceptor;
//import retrofit2.Retrofit;
//import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//@Module
//public class ApiModuleForInfo {
//
//    public final String BASE_URL = "http://www.omdbapi.com";
//    public final String API_KEY = "c69ba5e2";
//
//
//    @Provides
//    public OkHttpClient provideClient() {
//
////        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
////        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//
////        return new OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(new Interceptor() {
////            @Override
////            public Response intercept(Chain chain) throws IOException {
////                Request request = chain.request();
////                HttpUrl url = request.url().newBuilder().addQueryParameter(
////                        "apikey",
////                        API_KEY
////                ).build();
////                request = request.newBuilder().url(url).build();
////                return chain.proceed(request);
////            }
////        }).build();
//
//
//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
//        httpClient.addInterceptor(new Interceptor() {
//            @NonNull
//            @Override
//            public Response intercept(@NonNull Interceptor.Chain chain) throws IOException {
//                Request original = chain.request();
//
//                // Request customization: add request headers
//                Request.Builder requestBuilder = original.newBuilder()
//                        .header("Authorization", API_KEY); // <-- this is the important line
//
//                Request request = requestBuilder.build();
//                return chain.proceed(request);
//            }
//        });
//
//
//        httpClient.addNetworkInterceptor(logging);
//
//        return httpClient.build();
//    }
//
//    @Provides
//    public Retrofit provideRetrofit(String baseURL, OkHttpClient client) {
//        return new Retrofit.Builder()
//                .baseUrl(baseURL)
//                .client(client)
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//    }
//
//    @Provides
//    public MoreInfoApiService provideApiService() {
//        return provideRetrofit(BASE_URL, provideClient()).create(MoreInfoApiService.class);
//    }
//
//}
//
