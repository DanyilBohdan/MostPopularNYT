package com.example.mostpopularapi.retrofit;


import com.example.mostpopularapi.Example;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {

    String KEY_API = "o5xOYgx3iZFB8H5WYUbCaYGVSTMPBh5t";

    String URL_Emailed = "emailed/30.json?api-key=o5xOYgx3iZFB8H5WYUbCaYGVSTMPBh5t";
    String URL_Shared = "shared/30.json?api-key=o5xOYgx3iZFB8H5WYUbCaYGVSTMPBh5t";
    String URL_Viewed = "viewed/30.json?api-key=o5xOYgx3iZFB8H5WYUbCaYGVSTMPBh5t";

    @GET(URL_Emailed)
    Call<Example> getEmailedJSON();

    @GET(URL_Shared)
    Call<Example> getSharedJSON();

    @GET(URL_Viewed)
    Call<Example> getViewedJSON();
}
