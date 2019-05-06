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
import android.widget.Toast;

import com.example.hayri.dekorlink.AnaActivity;
import com.example.hayri.dekorlink.Api;
import com.example.hayri.dekorlink.ApiClient;
import com.example.hayri.dekorlink.Model.FavoriIslemler;
import com.example.hayri.dekorlink.Model.SepetUrunSil;
import com.example.hayri.dekorlink.Model.SepettekilerItem;
import com.example.hayri.dekorlink.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SepettekilerAdapter extends  RecyclerView.Adapter<SepettekilerAdapter.ViewHolder> {
    List<SepettekilerItem> list;
    Context context;
    int urun_id;
    int sepet_id;

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


        urun_id = Integer.valueOf(list.get(i).getUrunid());
        sepet_id=Integer.valueOf(list.get(i).getSepetid());
      viewHolder.sepetdencikar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sepetUrunSil(sepet_id, urun_id);
            }
        });

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


    private void sepetUrunSil(final int sepet_id, final int urun_id) {
        Api api = ApiClient.getClient().create(Api.class);
        Call<SepetUrunSil> ad = api.sepetUrunSil(sepet_id,urun_id);

        ad.enqueue(new Callback<SepetUrunSil>() {
            @Override
            public void onResponse(Call<SepetUrunSil> call, Response<SepetUrunSil> response) {

                if(response.body().getIsSuccess()==1)
                {
                    Toast.makeText(context,"Ürün sepetten silindi",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(context,AnaActivity.class);
                    context.startActivity(intent);
                }
                else
                {
                    Toast.makeText(context,"Ürün sepetten silinemedi",Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<SepetUrunSil> call, Throwable t) {
                // Toast.makeText(this,t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();

            }
        });


    }
}
