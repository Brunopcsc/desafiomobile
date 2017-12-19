package com.example.bruno.desafio.apps;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bruno.desafio.R;
import com.example.bruno.desafio.entity.AppsEntity;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;


public class AppsAdapter  extends RecyclerView.Adapter<AppsAdapter.ViewHolder>{

    private List<AppsEntity> appsList;
    OnRecyclerViewSelected onRecyclerViewSelected;
    private Context context;

    AppsAdapter(List<AppsEntity> appsList, Context context) {
        this.appsList = appsList;
        this.context = context;
    }

    //infla o componente view
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.apps_item_list, parent, false);
        return new ViewHolder(v);
    }

    //seta os dados nas views
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AppsEntity appsEntity = appsList.get(position);
        holder.txAppName.setText(appsEntity.getName());
        Picasso.with(context)
                .load(appsEntity.getImage())
                .centerCrop()
                .fit()
                .into(holder.imgBackgroud);
    }

    //retorna o tamanho da lista
    @Override
    public int getItemCount() {
        return appsList.size();
    }

    //mapeamento dos componentes da view
    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.app_name)
        TextView txAppName;

        @BindView(R.id.image_view_background)
        ImageView imgBackgroud;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.container)
        void onItemClick(View view){
            if(onRecyclerViewSelected != null)
                onRecyclerViewSelected.onClick(view, getAdapterPosition());

        }

    }

    public void setOnRecyclerViewSelected(OnRecyclerViewSelected onRecyclerViewSelected){
        this.onRecyclerViewSelected = onRecyclerViewSelected;
    }
}
