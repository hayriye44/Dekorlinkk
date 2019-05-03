package com.example.hayri.dekorlink;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.hayri.dekorlink.Adapters.FavorilerAdapter;
import com.example.hayri.dekorlink.Adapters.SepettekilerAdapter;
import com.example.hayri.dekorlink.Model.FavorilerItem;
import com.example.hayri.dekorlink.Model.FavorilerList;
import com.example.hayri.dekorlink.Model.SepetListe;
import com.example.hayri.dekorlink.Model.SepettekilerItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Sepetim extends AppCompatActivity {
    private TextView mTextTotal;
    private Button mButtonContinue, mButtonCheckout;
    private RecyclerView recyclerView;
    int sepetID;
    List<SepettekilerItem> sepetList;
    SepettekilerAdapter sepetAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sepetim);
        tanımla();
        SepettekiÜrünlerListele(sepetID);




    }
    public void tanımla(){
        recyclerView = (RecyclerView) findViewById(R.id.cart_container);
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(getApplicationContext(),1);
        recyclerView.setLayoutManager(layoutManager);
        sepetList=new ArrayList<>();

        Bundle extras = getIntent().getExtras();
        int kul_id = extras.getInt("kul_id");
        //bu id nin sepetindekileri listele
        sepetID=SharedPref.getInstance(Sepetim.this).LoggedInUserSepetId();
        Log.i("profilde sepetid",""+sepetID);
        String stringsepet=String.valueOf(sepetID);

    }
    public void SepettekiÜrünlerListele(final int sepet_id)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();
        Api api = retrofit.create(Api.class);
        Call<SepetListe> call = api.getsepetList(sepet_id);
        call.enqueue(new Callback<SepetListe>() {
            @Override
            public void onResponse(Call<SepetListe> call, Response<SepetListe> response) {
                Log.i("sepetteki ürünler",""+response.body().getSepettekiler());
                sepetList = response.body().getSepettekiler();
                sepetAdapter =new SepettekilerAdapter(sepetList,getApplicationContext());
                recyclerView.setAdapter(sepetAdapter);
                //Log.i("Yemekler",response.body().getFoods().toString());
            }
            @Override
            public void onFailure(Call<SepetListe> call, Throwable t) {
                // Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("error-------------->",t.getLocalizedMessage());
            }
        });
    }

/*
    //sepetteki ürün listesi geldiğinde septteki tüm ürünler in ücretleri toplanacak
    private double calculateTotal(List<Item> itemList) {
        double result = 0;
        for (int i = 0; i < itemList.size(); i++) {
            result += itemList.get(i).getPrice() * itemList.get(i).getQuantity();
        }
        return result;
    }

    */
}
