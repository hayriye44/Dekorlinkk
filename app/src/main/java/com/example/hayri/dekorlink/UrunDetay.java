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
import com.example.hayri.dekorlink.Model.SepetEkle;
import com.example.hayri.dekorlink.Model.SepetUpdateCreate;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UrunDetay extends AppCompatActivity {
    private TextView tvÜrünAdi,tvÜrünFiyati,tvÜrünAciklamasi,cart_item_number;
    String giriyapanuye_id;
    Button btn_favori,btn_sepetEkle;
    ImageView ürünresmi,cart_quant_minus,cart_quant_add;
    int aktifSepet;
    int adet;
    String ürün_id;
    String fiyat;

    int sepetID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urun_detay);
        Bundle extras = getIntent().getExtras();
        ürün_id = extras.getString("id");
        String adi = extras.getString("adi");
        fiyat = extras.getString("fiyat");
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

        String sadet=cart_item_number.getText().toString();
        adet=Integer.valueOf(sadet);
        AdetDüzenleme();
        btn_sepetEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sepeteEkle(giriyapanuye_id);
                sepetID=SharedPref.getInstance(UrunDetay.this).LoggedInUserSepetId();
                String stringsepet=String.valueOf(sepetID);
                sepetUrunEkleGüncelle(giriyapanuye_id,ürün_id,cart_item_number.getText().toString(),fiyat,stringsepet);
            }
        });


    }
    public  void tanimla(){
        tvÜrünFiyati=(TextView)findViewById(R.id.tvFiyat);
        tvÜrünAdi=(TextView)findViewById(R.id.tvAd);
        tvÜrünAciklamasi=(TextView)findViewById(R.id.tvAciklama);
        btn_favori=(Button)findViewById(R.id.btn_favori);
        btn_sepetEkle=(Button)findViewById(R.id.ilanDetaySepeteEkle);
        ürünresmi=(ImageView)findViewById(R.id.ürünResim);
        cart_quant_minus=(ImageView)findViewById(R.id.cart_quant_minus);
        cart_quant_add=(ImageView)findViewById(R.id.cart_quant_add);
        cart_item_number=(TextView)findViewById(R.id.cart_item_number);
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

    private  void sepeteEkle(final String uye_id)
    {
        aktifSepet = SharedPref.getInstance(this).LoggedInUserSepetId();
        Log.i("AktifSepet",""+aktifSepet);
        //yoksa sepet oluşturacak servisi çağır ve shared preferencese kaydet
        if(aktifSepet==0)
        {
            Api api = ApiClient.getClient().create(Api.class);
            Call<SepetEkle> sepetEkleCall = api.sepetEkle(uye_id);
            sepetEkleCall.enqueue(new Callback<SepetEkle>() {
                @Override
                public void onResponse(Call<SepetEkle> call, Response<SepetEkle> response) {
                    //sepet id eklendikten sonra gelen sepetid olacakBurdan gelen sepet ıd ye gore SepetÜrünler tablosuna eklenecek ve yeni sepet ıd kaydedilecek kaydedilecek
                    SharedPref.getInstance(UrunDetay.this).storeUserSepet(Integer.valueOf(response.body().getSepetId()));
                    Log.i("Yeni aktif sepet",""+  SharedPref.getInstance(UrunDetay.this).LoggedInUserSepetId());
                    Toast.makeText(UrunDetay.this,response.body().getMessage(),Toast.LENGTH_LONG).show();

                }
                @Override
                public void onFailure(Call<SepetEkle> call, Throwable t) {
                    Toast.makeText(UrunDetay.this,t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                }
            });




        }
    }
    private  void sepetUrunEkleGüncelle(final String uye_id,String ürün_id,String adet,String fiyat,String sepet_id){

        Api api = ApiClient.getClient().create(Api.class);
        Call<SepetUpdateCreate> Call = api.sepetUrunEkleGüncelle(uye_id,ürün_id,adet,fiyat,sepet_id);
        Call.enqueue(new Callback<SepetUpdateCreate>() {
            @Override
            public void onResponse(Call<SepetUpdateCreate> call, Response<SepetUpdateCreate> response) {
                Log.i("Yeni aktif sepet",""+  SharedPref.getInstance(UrunDetay.this).LoggedInUserSepetId());
                Toast.makeText(UrunDetay.this,response.body().getMessage(),Toast.LENGTH_LONG).show();

            }
            @Override
            public void onFailure(Call<SepetUpdateCreate> call, Throwable t) {
                Toast.makeText(UrunDetay.this,t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            }
        });



    }
    public void AdetDüzenleme(){

        cart_quant_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(adet!=1)
                {
                    adet--;
                    String sadet=String.valueOf(adet);
                    cart_item_number.setText(sadet);
                }

            }
        });
        cart_quant_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adet++;
                String sadet=String.valueOf(adet);
                cart_item_number.setText(sadet);
            }
        });
    }
}
