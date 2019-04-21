package com.example.hayri.dekorlink.Adapters;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hayri.dekorlink.Model.ProductsItem;
import com.example.hayri.dekorlink.Model.SoldprodactsItem;
import com.example.hayri.dekorlink.R;
import com.example.hayri.dekorlink.UrunDetay;
import com.squareup.picasso.Picasso;

import java.util.List;
public class CokSatanlarAdapter  extends RecyclerView.Adapter<CokSatanlarAdapter.ViewHolder>{
    List<SoldprodactsItem> list;
    Context context;


    public CokSatanlarAdapter(List<SoldprodactsItem> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @NonNull
    @Override
    public CokSatanlarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //Listview için oluşturduğumuz layoutu tanımlama
        View view=LayoutInflater.from(context).inflate(R.layout.katurunlistitem,viewGroup,false);
        return new CokSatanlarAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final int getir=i;
        //tanımlama atama işlemi
        viewHolder.tvÜrünAdi.setText(list.get(i).getUrunadi().toString());
        Picasso.get().load(list.get(i).getUrunresmi()).into(viewHolder.ivÜrünResmi);
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    //view elemanları tanımı için
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivÜrünResmi;
        TextView tvÜrünAdi;
        public ViewHolder(View itemView) {
            //itemview ile listviewin her elemanı için layout ile oluşturduğumuz viewin tanımlanması işlemi gerçekleşecek
            super(itemView);
            ivÜrünResmi =(ImageView) itemView.findViewById(R.id.ivÜrünResmi);
            tvÜrünAdi =(TextView) itemView.findViewById(R.id.tvÜrünAdi);
        }

        //Layout oluşturucaz ıtem tasarımı  için
    }

}
