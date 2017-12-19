package com.example.bruno.desafio.network.service;

import com.example.bruno.desafio.entity.AppsListEntity;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AppService {

    // acessa o arquivo json_data.json presente na url passada no Retrofit
    @GET("json_data.json")
    Call<AppsListEntity> getApps();

}
