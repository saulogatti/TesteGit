package com.example.apigithub;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apigithub.objetos.Item;
import com.example.apigithub.objetos.Items;

public class ListaDados extends RecyclerView.Adapter<ViewHolder> {
    private Items mItems;

    public ListaDados(Items body) {
        mItems = body;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.linha_lista, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = mItems.items.get(position);
        holder.qtdEstrela.setText(String.valueOf(item.stargazers_count));
        holder.nomeRep.setText(item.name);
        holder.nome.setText(item.full_name);
    }

    @Override
    public int getItemCount() {
        return mItems.items.size();
    }


}

class ViewHolder extends RecyclerView.ViewHolder {
    public TextView nome;
    public TextView qtdEstrela;
    public TextView nomeRep;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        nome = itemView.findViewById(R.id.nome);
        nomeRep = itemView.findViewById(R.id.nomeRep);
        qtdEstrela = itemView.findViewById(R.id.qtdEstrela);
    }
}

