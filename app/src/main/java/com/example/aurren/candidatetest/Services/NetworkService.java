package com.example.aurren.candidatetest.Services;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.aurren.candidatetest.Constants.BASE_URL;

/**
 * Created by Aurren on 03/01/2017.
 */

public class NetworkService {

    public static API Initialise(){

        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(BASE_URL).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create());
        return builder.build().create(API.class);
    }

}
