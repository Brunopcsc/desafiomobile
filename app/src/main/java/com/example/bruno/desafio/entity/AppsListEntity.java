package com.example.bruno.desafio.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Bruno on 14/12/2017.
 */

public class AppsListEntity {
    @SerializedName("acoes_sociais")
    @Expose
    private List<AppsEntity> apps = null;
    public List<AppsEntity> getApps(){
        return apps;
    }
}
