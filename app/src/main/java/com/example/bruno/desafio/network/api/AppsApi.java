package com.example.bruno.desafio.network.api;

import com.example.bruno.desafio.entity.AppsDetailEntity;
import com.example.bruno.desafio.entity.AppsListEntity;
import com.example.bruno.desafio.network.service.AppService;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AppsApi {
    private static AppsApi instance;
    private AppService appService;
    private String sessionToken;

    //Retorna a instância da API, ou caso não exista cria uma instância e a retorna.
    public static AppsApi getInstance(){
        if(instance == null){
            instance = new AppsApi();
        }

        return instance;
    }

    // Faz a criação do Retrofit passando a URL de onde será pego o arquivo JSON
    private AppsApi(){
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("https://dl.dropboxusercontent.com/s/50vmlj7dhfaibpj/")
                .addConverterFactory(defaultConvertFactory())
                .build();

        this.appService = retrofit.create(AppService.class);

    }


    private Converter.Factory defaultConvertFactory() {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        return GsonConverterFactory.create(gson);
    }

    public Call<AppsListEntity> getApps() {
        return appService.getApps();
    }

}
