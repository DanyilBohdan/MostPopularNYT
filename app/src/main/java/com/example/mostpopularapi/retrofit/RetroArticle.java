package com.example.mostpopularapi.retrofit;

import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroArticle {

    private static final String ROOT_URL = "https://api.nytimes.com/svc/mostpopular/v2/";
    private static Retrofit retrofit;

    /**
     * Get Retrofit Instance
     */
    public static Retrofit getRetrofitIntance() {

        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();

            if (retrofit == null) {

                retrofit = new retrofit2.Retrofit.Builder()
                        .baseUrl(ROOT_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(client)
                        .build();
            }
            return retrofit;
        }
}
