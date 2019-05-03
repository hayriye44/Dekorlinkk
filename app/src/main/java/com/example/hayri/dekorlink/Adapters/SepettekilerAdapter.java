package com.example.hayri.dekorlink.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hayri.dekorlink.Model.SepettekilerItem;
import com.example.hayri.dekorlink.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SepettekilerAdapter extends  RecyclerView.Adapter<SepettekilerAdapter.ViewHolder> {
    List<SepettekilerItem> list;
    Context context;

    public SepettekilerAdapter(List<SepettekilerItem> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public SepettekilerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //Listview için oluşturduğumuz layoutu tanımlama
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_cart_item, viewGroup, false);
        return new SepettekilerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SepettekilerAdapter.ViewHolder viewHolder, int i) {
        final int getir = i;
        //tanımlama atama işlemi
        viewHolder.tvÜrünAdi.setText(list.get(i).getUrunadi().toString());
        viewHolder.tvBirimFiyat.setText(list.get(i).getBirimfiyat().toString());
        viewHolder.tvAdet.setText(list.get(i).getAdet().toString());
        viewHolder.tvToplamÜcret.setText(list.get(i).getToplamfiyat().toString());
        viewHolder.tvÜrünDurum.setText(list.get(i).getDurum().toString());
        Picasso.get().load(list.get(i).getUrunresmi()).into(viewHolder.ivÜrünResmi);
        //urun_id = list.get(i).getId();
     /*   viewHolder.favoridencikar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favoriIslemler(uye_id, urun_id);
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //view elemanları tanımı için
    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivÜrünResmi;
        TextView tvÜrünAdi, tvAdet, tvBirimFiyat,tvToplamÜcret,tvÜrünDurum;
        ImageView sepetdencikar;

        public ViewHolder(View itemView) {
            //itemview ile listviewin her elemanı için layout ile oluşturduğumuz viewin tanımlanması işlemi gerçekleşecek
            super(itemView);
            ivÜrünResmi = (ImageView) itemView.findViewById(R.id.ürünResmi);
            // tvKategori=(TextView) itemView.findViewById(R.id.tvKategori);
            tvÜrünAdi = (TextView) itemView.findViewById(R.id.sepetürünAd);
            tvBirimFiyat = (TextView) itemView.findViewById(R.id.adet_fiyati);
            tvAdet = (TextView) itemView.findViewById(R.id.adet);
            tvToplamÜcret = (TextView) itemView.findViewById(R.id.toplamÜcret);
            tvÜrünDurum = (TextView) itemView.findViewById(R.id.sepetürünDurum);
            sepetdencikar=(ImageView)itemView.findViewById(R.id.sepetürünsil);
        }


    }
}
