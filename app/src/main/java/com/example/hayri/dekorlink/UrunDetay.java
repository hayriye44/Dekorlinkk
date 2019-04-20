package com.example.hayri.dekorlink;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class UrunDetay extends AppCompatActivity {
    private TextView tvÜrünAdi,tvÜrünFiyati,tvÜrünAciklamasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urun_detay);
        Bundle extras = getIntent().getExtras();
        String ürün_id = extras.getString("id");
        String adi = extras.getString("adi");
        String fiyat = extras.getString("fiyat");
        String aciklama = extras.getString("aciklama");
        String kategoriid = extras.getString("kategoriid");
        Log.i("ürünıd",""+ürün_id+"ad:"+adi+"aciklama:"+aciklama+"katıd:"+kategoriid);
        Log.i("ürünad",""+adi);
        tvÜrünFiyati=(TextView)findViewById(R.id.tvFiyat);
        tvÜrünAdi=(TextView)findViewById(R.id.tvAd);
        tvÜrünAciklamasi=(TextView)findViewById(R.id.tvAciklama);
        tvÜrünAdi.setText(adi);
        tvÜrünFiyati.setText(fiyat);
        tvÜrünAciklamasi.setText(aciklama);
    }
    public  void tanimla(){


    }
}
