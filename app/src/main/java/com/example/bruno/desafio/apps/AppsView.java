package com.example.bruno.desafio.apps;

import com.example.bruno.desafio.entity.AppsEntity;

import java.util.List;

/**
 * Created by Bruno on 17/12/2017.
 */

public interface AppsView {
    void updateList(List<AppsEntity> appsList);

    void showMessage(String msg);
    void showLoading();
    void hideLoading();

    void saveAppsPreference(String jsonApps);
    boolean conectado();
}
