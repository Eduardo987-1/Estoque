package com.eduardo.alcas.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.eduardo.alcas.Alcas10;
import com.eduardo.alcas.R;



import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    Context context;
    ArrayList<Alcas10> alcas10ArrayList;

    public Adapter(Context context, ArrayList<Alcas10> alcas10ArrayList) {
        this.context = context;
        this.alcas10ArrayList = alcas10ArrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.adapter_lista,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder, int position) {
        Alcas10 alcas10 = alcas10ArrayList.get(position);
        holder.cor.setText(alcas10.getCor());
        holder.quantidade.setText(alcas10.getQuantidade());


    }

    @Override
    public int getItemCount() {
        return alcas10ArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView cor, quantidade;
        public MyViewHolder(View itemView) {
            super(itemView);
            cor = itemView.findViewById(R.id.cor);
            quantidade = itemView.findViewById(R.id.quantidade);
        }
    }
}


