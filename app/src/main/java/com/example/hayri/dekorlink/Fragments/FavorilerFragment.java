package com.example.hayri.dekorlink.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hayri.dekorlink.Adapters.FavorilerAdapter;
import com.example.hayri.dekorlink.Api;
import com.example.hayri.dekorlink.ApiClient;
import com.example.hayri.dekorlink.Model.FavorilerItem;
import com.example.hayri.dekorlink.Model.FavorilerList;
import com.example.hayri.dekorlink.R;
import com.example.hayri.dekorlink.SharedPref;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavorilerFragment extends Fragment {

    RecyclerView rvUrunler;
    List<FavorilerItem> urunList;
    FavorilerAdapter urunAdapter;
    private View myFragment;

    public FavorilerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myFragment = inflater.inflate(R.layout.fragment_favoriler, container, false);
        rvUrunler =myFragment.findViewById(R.id.rvUrunler);
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(myFragment.getContext(),1);
        rvUrunler.setLayoutManager(layoutManager);
        urunList=new ArrayList<>();

        String girisyapankul_id;
        girisyapankul_id=String.valueOf(SharedPref.getInstance(getContext()).LoggedInUserId());
        FavoriYemeklerListele(girisyapankul_id);
        return myFragment;
    }
    public void FavoriYemeklerListele(final String uye_id)
    {
        Log.i("favorilerlistesiuyeıd",""+uye_id);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();
        Api api = retrofit.create(Api.class);
        Call<FavorilerList> call = api.getfavorilerList(uye_id);
        call.enqueue(new Callback<FavorilerList>() {
            @Override
            public void onResponse(Call<FavorilerList> call, Response<FavorilerList> response) {
                Log.i("ürünler",""+response.body().getFavoriler());
                urunList = response.body().getFavoriler();
                urunAdapter =new FavorilerAdapter(urunList,getContext(),uye_id);
                rvUrunler.setAdapter(urunAdapter);
                //Log.i("Yemekler",response.body().getFoods().toString());
            }
            @Override
            public void onFailure(Call<FavorilerList> call, Throwable t) {
                // Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("error-------------->",t.getLocalizedMessage());
            }
        });
    }

}