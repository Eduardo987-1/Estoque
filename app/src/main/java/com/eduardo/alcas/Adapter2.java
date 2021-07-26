package com.eduardo.alcas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eduardo.alcas.adapter.Adapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class Adapter2 extends RecyclerView.Adapter<Adapter2.MyViewHolder2> {
    Context context;
    ArrayList<Alcas13> alcas13ArrayList;

    public Adapter2(Context context, ArrayList<Alcas13> alcas13ArrayList) {
        this.context = context;
        this.alcas13ArrayList = alcas13ArrayList;
    }
    @Override
    public MyViewHolder2 onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.adapter_lista2,parent,false);
       return new MyViewHolder2(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewHolder2 holder, int position) {
        Alcas13 alcas13 = alcas13ArrayList.get(position);
        holder.cor2.setText(alcas13.getCor());
        holder.quantidade2.setText(alcas13.getQuantidade());

    }

    @Override
    public int getItemCount() {
        return alcas13ArrayList.size();
    }
    public static  class MyViewHolder2 extends RecyclerView.ViewHolder{
        TextView cor2, quantidade2;

        public MyViewHolder2(@NonNull @NotNull View itemView) {
            super(itemView);
            cor2 = itemView.findViewById(R.id.cor2);
            quantidade2 = itemView.findViewById(R.id.quantidade2);

        }
    }
}
