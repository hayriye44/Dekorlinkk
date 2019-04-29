package com.example.hayri.dekorlink;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hayri.dekorlink.Model.FavoriIslemler;
import com.example.hayri.dekorlink.Model.Favoriler;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UrunDetay extends AppCompatActivity {
    private TextView tvÜrünAdi,tvÜrünFiyati,tvÜrünAciklamasi;
    String giriyapanuye_id;
    Button btn_favori;
    ImageView ürünresmi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urun_detay);
        Bundle extras = getIntent().getExtras();
        final String ürün_id = extras.getString("id");
        String adi = extras.getString("adi");
        String fiyat = extras.getString("fiyat");
        String aciklama = extras.getString("aciklama");
        String kategoriid = extras.getString("kategoriid");
        String resim = extras.getString("resim");
        Log.i("ürünıd",""+ürün_id+"ad:"+adi+"aciklama:"+aciklama+"katıd:"+kategoriid);
        Log.i("ürünad",""+adi);
        tanimla();
        tvÜrünAdi.setText(adi);
        tvÜrünFiyati.setText(fiyat);
        tvÜrünAciklamasi.setText(aciklama);
        Picasso.get().load(resim).into(ürünresmi);


        giriyapanuye_id=String.valueOf(SharedPref.getInstance(this).LoggedInUserId());
        Log.i("girişyapanıd",""+giriyapanuye_id+"ürünıd"+ürün_id);
        favoriKontrol(giriyapanuye_id,ürün_id);
        btn_favori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favoriIslemler(giriyapanuye_id,ürün_id);
            }
        });
    }
    public  void tanimla(){
        tvÜrünFiyati=(TextView)findViewById(R.id.tvFiyat);
        tvÜrünAdi=(TextView)findViewById(R.id.tvAd);
        tvÜrünAciklamasi=(TextView)findViewById(R.id.tvAciklama);
        btn_favori=(Button)findViewById(R.id.btn_favori);
        ürünresmi=(ImageView)findViewById(R.id.ürünResim);
    }
    private void favoriKontrol(final String uye_id, final String ürün_id) {
        Api api = ApiClient.getClient().create(Api.class);
        Call<Favoriler> ad = api.favoriKontrol(uye_id,ürün_id);

        ad.enqueue(new Callback<Favoriler>() {
            @Override
            public void onResponse(Call<Favoriler> call, Response<Favoriler> response) {

                if(response.body().getIsSuccess()==0)
                {
                    Log.i("girişyapanıd",""+uye_id+"ürünıd"+ürün_id);
                    Log.i("girişyapanıd",response.body().getMessage());
                    btn_favori.setText(response.body().getMessage());
                }
                else
                {
                    Log.i("girişyapanıd",""+uye_id+"ürünıd"+ürün_id);
                    Log.i("girişyapanıd",response.body().getMessage());
                    btn_favori.setText(response.body().getMessage());

                }
            }

            @Override
            public void onFailure(Call<Favoriler> call, Throwable t) {
                Toast.makeText(UrunDetay.this,t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();

            }
        });


    }
    private void favoriIslemler(final String uye_id, final String urun_id) {
        Api api = ApiClient.getClient().create(Api.class);
        Call<FavoriIslemler> ad = api.favoriIslemler(uye_id,urun_id);

        ad.enqueue(new Callback<FavoriIslemler>() {
            @Override
            public void onResponse(Call<FavoriIslemler> call, Response<FavoriIslemler> response) {

                if(response.body().getIsSuccess()==0)
                {
                    Toast.makeText(UrunDetay.this,response.body().getMessage(),Toast.LENGTH_LONG).show();
                    favoriKontrol(uye_id,urun_id);
                }
                else
                {
                    Toast.makeText(UrunDetay.this,response.body().getMessage(),Toast.LENGTH_LONG).show();
                    favoriKontrol(uye_id,urun_id);
                }
            }

            @Override
            public void onFailure(Call<FavoriIslemler> call, Throwable t) {
                Toast.makeText(UrunDetay.this,t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();

            }
        });


    }


}
