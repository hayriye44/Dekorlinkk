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
import com.example.hayri.dekorlink.R;
import com.example.hayri.dekorlink.UrunDetay;
import com.squareup.picasso.Picasso;

import java.util.List;

public class KatProdactsAdapter extends RecyclerView.Adapter<KatProdactsAdapter.ViewHolder> {

    List<ProductsItem> list;
    Context context;


    public KatProdactsAdapter(List<ProductsItem> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //Listview için oluşturduğumuz layoutu tanımlama
        View view=LayoutInflater.from(context).inflate(R.layout.katurunlistitem,viewGroup,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final int getir=i;
        //tanımlama atama işlemi
        viewHolder.tvÜrünAdi.setText(list.get(i).getUrunadi().toString());
        viewHolder.tvÜrünFiyati.setText(list.get(i).getFiyat().toString());
        Picasso.get().load(list.get(i).getUrunresmi().toString()).into(viewHolder.ivÜrünResmi);
        viewHolder.btnDetay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,UrunDetay.class);
                intent.putExtra("id",list.get(getir).getId());
                intent.putExtra("adi",list.get(getir).getUrunadi());
                intent.putExtra("fiyat",list.get(getir).getFiyat());
                intent.putExtra("aciklama",list.get(getir).getAciklama());
                intent.putExtra("resim",list.get(getir).getUrunresmi());
                intent.putExtra("kategoriid",list.get(getir).getKategoriId());
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    //view elemanları tanımı için
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivÜrünResmi;
        TextView tvKategori, tvÜrünAdi,tvYemekMiktari, tvÜrünFiyati,tvYemekPuani;
        Button btnDetay;
        public ViewHolder(View itemView) {
            //itemview ile listviewin her elemanı için layout ile oluşturduğumuz viewin tanımlanması işlemi gerçekleşecek
            super(itemView);
            ivÜrünResmi =(ImageView) itemView.findViewById(R.id.ivÜrünResmi);
            // tvKategori=(TextView) itemView.findViewById(R.id.tvKategori);
            tvÜrünAdi =(TextView) itemView.findViewById(R.id.tvÜrünAdi);
            // tvYemekMiktari=(TextView) itemView.findViewById(R.id.tvYemekMiktari);
            tvÜrünFiyati =(TextView) itemView.findViewById(R.id.tvUrünFiyati);
            // tvYemekPuani=(TextView) itemView.findViewById(R.id.tvYemekPuani);
            btnDetay=(Button) itemView.findViewById(R.id.btnDetay);
        }

        //Layout oluşturucaz ıtem tasarımı  için
    }
}
