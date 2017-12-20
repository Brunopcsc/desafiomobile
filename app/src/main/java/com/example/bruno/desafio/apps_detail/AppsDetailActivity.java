package com.example.bruno.desafio.apps_detail;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bruno.desafio.R;
import com.example.bruno.desafio.entity.AppsDetailEntity;
import com.example.bruno.desafio.entity.AppsEntity;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppsDetailActivity extends AppCompatActivity {

    @BindView(R.id.image_view_header)
    ImageView imgHeader;

    @BindView(R.id.text_view_description)
    TextView tvDescription;

    @BindView(R.id.site)
    TextView tvSite;

    @BindView(R.id.linear_layout_loading)
    LinearLayout loadingLayout;
    String imagem,nome,descricao,site;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apps_detail);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        //Pega os campos passados pelo putExtra da activity anterior
        Bundle b = intent.getExtras();
        //insere opção Up Action na ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        //Atribui o conteúdo dos campos recebidos em Strings
        if(b!=null)
        {
            imagem =(String) b.get("img");
            nome =(String) b.get("name");
            descricao =(String) b.get("desc");
            site =(String) b.get("site");
        }

        this.showDetails(nome,imagem,descricao,site);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_desc, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }

    //Mostra os detalhes da Ação Social clicada anteriormente
    public void showDetails(String nome,String imagem,String descricao,String site ) {
        showLoading();
        //Seta as Views de acordo com os campos recebidos
        tvDescription.setText(descricao);
        tvSite.setText(tvSite.getText().toString()+ site);
        tvSite.setLinkTextColor(Color.BLUE);
        Picasso.with(this)
                .load(imagem)
                .into(imgHeader);
        setTitle(nome);
        hideLoading();
    }

    public void showLoading() {
        loadingLayout.setVisibility(View.VISIBLE);
    }

    public void hideLoading() {
        loadingLayout.setVisibility(View.GONE);
    }
}
