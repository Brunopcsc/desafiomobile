package com.example.bruno.desafio.apps;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.bruno.desafio.R;
import com.example.bruno.desafio.apps_detail.AppsDetailActivity;
import com.example.bruno.desafio.entity.AppsEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppsActivity extends AppCompatActivity implements AppsView {

    // Binding do recycler view
    @BindView(R.id.rv_apps)
    RecyclerView recyclerAcoes;

    @BindView(R.id.linear_layout_loading)
    LinearLayout loadingLayout;

    AppsPresenter appsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apps);

        ButterKnife.bind(this);
        //Cria o presenter
        SharedPreferences shared = getSharedPreferences("apps_json",Context.MODE_PRIVATE);
        String jsonApps = (shared.getString("apps_entity_json", ""));
        appsPresenter = new AppsPresenter(this);
        //Chama o presenter para atualizar o Recycler View com as Acoes Sociais
        appsPresenter.updateList(jsonApps);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_download, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.action_download:
                //salvar as informações dos filmes nas SharedPreferences
                appsPresenter.saveApps();

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean conectado(){
        ConnectivityManager cm =(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    @Override
    public void updateList(final List<AppsEntity> appsList) {

        //seta o adapter
        AppsAdapter moviesAdapter = new AppsAdapter(appsList, this);
        moviesAdapter.setOnRecyclerViewSelected(new OnRecyclerViewSelected() {
            //Ao se clicar em algum item do Recycler abre a activity para exibir os detalhes
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent
                        (AppsActivity.this,
                                AppsDetailActivity.class);
                //Passa para a activy a ser aberta as informações do Item clicado.
                intent.putExtra("desc", appsList.get(position).getDescription());
                intent.putExtra("img", appsList.get(position).getImage());
                intent.putExtra("name", appsList.get(position).getName());
                intent.putExtra("site", appsList.get(position).getSite());
                startActivity(intent);

            }

        });

        recyclerAcoes.setAdapter(moviesAdapter);
        // criação do gerenciador de layouts
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(this, layoutManager.getOrientation());
        recyclerAcoes.setLayoutManager(layoutManager);
        recyclerAcoes.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoading() {
        loadingLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadingLayout.setVisibility(View.GONE);
    }

    //Recebe o Json em forma de string e faz o salvamento para utilização no caso em que não haja conexão.
    @Override
    public void saveAppsPreference(String jsonAppsEntity) {
        SharedPreferences sharedPref = getSharedPreferences("apps_json",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("apps_entity_json", jsonAppsEntity);
        editor.apply();
        showMessage("Informações salvas com sucesso");

    }
}
