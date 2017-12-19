package com.example.bruno.desafio.apps;

import android.content.Context;
import android.net.ConnectivityManager;

import com.example.bruno.desafio.entity.AppsEntity;
import com.example.bruno.desafio.entity.AppsListEntity;
import com.example.bruno.desafio.network.api.AppsApi;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Bruno on 17/12/2017.
 */

public class AppsPresenter {

    private AppsView appsView;
    private List<AppsEntity> appsList = new ArrayList<>();
    AppsListEntity appsListEntity;

    AppsPresenter(AppsView appsView){
        this.appsView = appsView;
    }

    //Atualiza a lista com as Ações Sociais
    void updateList(String jsonApps){
            //Caso já tenha dados salvos em memória faz o carregamento
            boolean conexao = appsView.conectado();
            if(jsonApps!= null && !conexao){
                appsView.showMessage("Trabalhando com dados offline!");
                appsListEntity= new Gson().fromJson(jsonApps,AppsListEntity.class);
                appsList=appsListEntity.getApps();
                appsView.updateList(appsList);
            }
            else {
                //Pega a instância da API
                final AppsApi appsApi = AppsApi.getInstance();
                //Mostra a imagem de carregando
                appsView.showLoading();
                //Faz o acesso ao servidor para pegar os dados
                appsApi.getApps().enqueue(new Callback<AppsListEntity>() {
                    @Override
                    public void onResponse(Call<AppsListEntity> call, Response<AppsListEntity> response) {
                        appsListEntity = response.body();
                        appsView.updateList(appsListEntity.getApps());
                        //Desabilita a imagem de carregando
                        appsView.hideLoading();
                    }

                    @Override
                    public void onFailure(Call<AppsListEntity> call, Throwable t) {
                        appsView.hideLoading();
                        appsView.showMessage("Falha no acesso ao servidor");
                    }
                });
            }
    }

    //Salva os dados já baixados na memória
    public void saveApps() {
        String jsonAppsEntity = new Gson().toJson(appsListEntity);
        appsView.saveAppsPreference(jsonAppsEntity);
    }
}
