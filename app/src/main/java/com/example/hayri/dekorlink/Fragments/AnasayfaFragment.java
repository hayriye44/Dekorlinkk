package com.example.hayri.dekorlink.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hayri.dekorlink.Adapters.CokSatanlarAdapter;
import com.example.hayri.dekorlink.Adapters.IndirimdekilerAdapter;
import com.example.hayri.dekorlink.Adapters.KatProdactsAdapter;
import com.example.hayri.dekorlink.Adapters.OneCikanlarAdapter;
import com.example.hayri.dekorlink.Api;
import com.example.hayri.dekorlink.Model.ProdactList;
import com.example.hayri.dekorlink.Model.ProductsItem;
import com.example.hayri.dekorlink.Model.SoldprodactList;
import com.example.hayri.dekorlink.Model.SoldprodactsItem;
import com.example.hayri.dekorlink.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.example.hayri.dekorlink.ApiClient;

/**
 * A simple {@link Fragment} subclass.
 */
public class AnasayfaFragment extends Fragment {
    private View view;
    RecyclerView rvÜrünler,rvCokSatanlar,rvOneCikanlar,rvIndirimdekiler;
    TextView cokSatanlar;
    LinearLayout layoutOneCikanlar,layoutIndirimdekiler;
    List<SoldprodactsItem> coksatanlar;
    List<SoldprodactsItem> onecikanlar,indirimdekiler;
    List<ProductsItem> prodactList;
    KatProdactsAdapter ürünAdapter;
    CokSatanlarAdapter cokSatanlarAdapter;
    OneCikanlarAdapter oneCikanlarAdapter;
    IndirimdekilerAdapter indirimdekilerAdapter;
    public AnasayfaFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_anasayfa, container, false);
        tanimla();
        prodactList=new ArrayList<>();
        cokSatanlar.setVisibility(View.VISIBLE);
        rvÜrünler.setVisibility(View.INVISIBLE);
        rvCokSatanlar.setVisibility(View.VISIBLE);
        layoutOneCikanlar.setVisibility(View.VISIBLE);
        layoutIndirimdekiler.setVisibility(View.VISIBLE);
        EnCokSatanGetir();
        OneCikanGetir();
        IndirimdekilerGetir();
        Button anaSayfa = (Button)view.findViewById(R.id.anaSayfa);
        anaSayfa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cokSatanlar.setVisibility(View.VISIBLE);
                rvÜrünler.setVisibility(View.INVISIBLE);
                rvCokSatanlar.setVisibility(View.VISIBLE);
                layoutOneCikanlar.setVisibility(View.VISIBLE);
                layoutIndirimdekiler.setVisibility(View.VISIBLE);
                EnCokSatanGetir();
                OneCikanGetir();
                IndirimdekilerGetir();
            }
        });
        Button mobilya = (Button)view.findViewById(R.id.mobilya);
        mobilya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cokSatanlar.setVisibility(View.INVISIBLE);
                rvÜrünler.setVisibility(View.VISIBLE);
                rvCokSatanlar.setVisibility(View.INVISIBLE);
                layoutOneCikanlar.setVisibility(View.INVISIBLE);
                layoutIndirimdekiler.setVisibility(View.INVISIBLE);
                AllProductList(2);
                showPopupMenuMobilya(v);
            }
        });
        Button mutfak = (Button)view.findViewById(R.id.mutfak);
        mutfak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cokSatanlar.setVisibility(View.INVISIBLE);
                rvÜrünler.setVisibility(View.VISIBLE);
                rvCokSatanlar.setVisibility(View.INVISIBLE);
                layoutOneCikanlar.setVisibility(View.INVISIBLE);
                layoutIndirimdekiler.setVisibility(View.INVISIBLE);
                AllProductList(8);
                showPopupMenuMutfak(v);
            }
        });
        Button evDekorasyon = (Button)view.findViewById(R.id.evDekorasyon);
        evDekorasyon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cokSatanlar.setVisibility(View.INVISIBLE);
                rvÜrünler.setVisibility(View.VISIBLE);
                rvCokSatanlar.setVisibility(View.INVISIBLE);
                layoutOneCikanlar.setVisibility(View.INVISIBLE);
                layoutIndirimdekiler.setVisibility(View.INVISIBLE);
                AllProductList(14);
                showPopupMenuEvDekorasyon(v);
            }
        });
        Button aydınlatma = (Button)view.findViewById(R.id.aydınlatma);
        aydınlatma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cokSatanlar.setVisibility(View.INVISIBLE);
                rvÜrünler.setVisibility(View.VISIBLE);
                rvCokSatanlar.setVisibility(View.INVISIBLE);
                layoutOneCikanlar.setVisibility(View.INVISIBLE);
                layoutIndirimdekiler.setVisibility(View.INVISIBLE);
                AllProductList(20);
                showPopupMenuEvAydınlatma(v);
            }
        });
        return view;
    }
    public  void tanimla()
    {
        rvÜrünler=view.findViewById(R.id.rvKatÜrünler);
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(getContext(),2);
        rvÜrünler.setLayoutManager(layoutManager);

        rvCokSatanlar=view.findViewById(R.id.rvCokSatılanlar);
        //RecyclerView.LayoutManager layoutManager2=new GridLayoutManager(getContext(),2,GridLayoutManager.HORIZONTAL,false);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvCokSatanlar.setLayoutManager(layoutManager2);

        rvOneCikanlar=view.findViewById(R.id.rvOneCikanlar);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvOneCikanlar.setLayoutManager(layoutManager3);

        rvIndirimdekiler=view.findViewById(R.id.rvIndirimdekiler);
        LinearLayoutManager layoutManager4 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvIndirimdekiler.setLayoutManager(layoutManager4);

        cokSatanlar=(TextView)view.findViewById(R.id.tvCokSatan);

        layoutOneCikanlar=view.findViewById(R.id.layoutoneCikanlar);
        layoutIndirimdekiler=view.findViewById(R.id.layoutIndirimdekiler);
    }
    private void showPopupMenuMobilya(View view) {
//      // TODO Auto-generated method stub
        PopupMenu popupmenu = new PopupMenu(getActivity(), view);
        popupmenu.getMenuInflater().inflate(R.menu.mobilya_alt_menu, popupmenu.getMenu());
        popupmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuitem) {
                rvÜrünler.setVisibility(View.VISIBLE);
                // TODO Auto-generated method stub
                switch (menuitem.getItemId()) {
                    case R.id.tvUnite:
                        AllProductList(3);
                        return true;
                    case R.id.sehpa:
                        AllProductList(4);
                        return true;
                    case R.id.kitaplık:
                        AllProductList(5);
                        return true;
                    case R.id.yatakOdası:
                        AllProductList(6);
                        return true;
                    case R.id.oturmaOdası:
                        AllProductList(7);
                        return true;
                    default:
                        return false;
                }
            }
        });
        popupmenu.show();
    }
    private void showPopupMenuMutfak(View view) {
        rvÜrünler.setVisibility(View.VISIBLE);
//      // TODO Auto-generated method stub
        PopupMenu popupmenu = new PopupMenu(getActivity(), view);
        popupmenu.getMenuInflater().inflate(R.menu.mutfak_alt_menu, popupmenu.getMenu());
        popupmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuitem) {
                // TODO Auto-generated method stub
                switch (menuitem.getItemId()) {
                    case R.id.servisUrünleri:
                        AllProductList(9);
                        return true;
                    case R.id.catalKasik:
                        AllProductList(10);
                        return true;
                    case R.id.yemekTakimlari:
                        AllProductList(11);
                        return true;
                    case R.id.bardakSurahi:
                        AllProductList(12);
                        return true;
                    case R.id.saklamaKaplari:
                        AllProductList(13);
                        return true;
                            default:
                                return false;
                }

            }
        });
        popupmenu.show();
    }
    private void showPopupMenuEvDekorasyon(View view) {
        rvÜrünler.setVisibility(View.VISIBLE);
//      // TODO Auto-generated method stub
        PopupMenu popupmenu = new PopupMenu(getActivity(), view);
        popupmenu.getMenuInflater().inflate(R.menu.evdekorasyon_alt_menu, popupmenu.getMenu());
        popupmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuitem) {
                // TODO Auto-generated method stub
                switch (menuitem.getItemId()) {
                    case R.id.tablo:
                        AllProductList(15);
                        return true;
                    case R.id.saat:
                        AllProductList(16);
                        return true;
                    case R.id.dekoratifObje:
                        AllProductList(17);
                        return true;
                    case R.id.kapıAksesuar:
                        AllProductList(18);
                        return true;
                    case R.id.ayna:
                        AllProductList(19);
                        return true;
                            default:
                                return false;
                }
            }
        });
        popupmenu.show();
    }
    private void showPopupMenuEvAydınlatma(View view) {
        rvÜrünler.setVisibility(View.VISIBLE);
//      // TODO Auto-generated method stub
        PopupMenu popupmenu = new PopupMenu(getActivity(), view);
        popupmenu.getMenuInflater().inflate(R.menu.aydinlatma_alt_menu, popupmenu.getMenu());
        popupmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuitem) {
                // TODO Auto-generated method stub
                switch (menuitem.getItemId()) {
                    case R.id.sarkitlar:
                        AllProductList(21);
                        return true;
                    case R.id.avizeler:
                        AllProductList(22);
                        return true;
                    case R.id.ampül_led:
                        AllProductList(23);
                        return true;
                    case R.id.spot:
                        AllProductList(24);
                        return true;
                    case R.id.led:
                        AllProductList(25);
                        return true;
                            default:
                                return false;
                }
            }
        });
        popupmenu.show();
    }
    public void AllProductList(int kategori_id)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();
        Api api = retrofit.create(Api.class);
        Call<ProdactList> call = api.getAllProdacts(kategori_id);
        call.enqueue(new Callback<ProdactList>() {
            @Override
            public void onResponse(Call<ProdactList> call, Response<ProdactList> response) {
                prodactList = response.body().getProducts();
                ürünAdapter=new KatProdactsAdapter(prodactList,getContext());
                rvÜrünler.setAdapter(ürünAdapter);
                Toast.makeText(getContext(),"Sistemde kayıtlı"+prodactList.size()+"ürün var",Toast.LENGTH_LONG).show();

                Log.i("Yemekler",response.body().getProducts().toString());
            }
            @Override
            public void onFailure(Call<ProdactList> call, Throwable t) {
                Toast.makeText(getContext(), "Bu kategoride ürün bulunmamakta", Toast.LENGTH_SHORT).show();
                Log.d("error-------------->",t.getLocalizedMessage());
            }
        });
    }
    public void EnCokSatanGetir(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();
        Api api = retrofit.create(Api.class);
        Call<SoldprodactList> call = api.getCokSatanGetir();
        call.enqueue(new Callback<SoldprodactList>() {
            @Override
            public void onResponse(Call<SoldprodactList> call, Response<SoldprodactList> response) {
                coksatanlar=response.body().getSoldprodacts();
                Log.i("coksatanürünler","Adı"+response.body().getSoldprodacts());
                cokSatanlarAdapter=new CokSatanlarAdapter(coksatanlar,getContext());
                rvCokSatanlar.setAdapter(cokSatanlarAdapter);

               }
            @Override
            public void onFailure(Call<SoldprodactList> call, Throwable t) {
                // Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("error-------------->",t.getLocalizedMessage());
            }
        });
    }
    public void OneCikanGetir(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();
        Api api = retrofit.create(Api.class);
        Call<SoldprodactList> call = api.getOneCikanlarGetir();
        call.enqueue(new Callback<SoldprodactList>() {
            @Override
            public void onResponse(Call<SoldprodactList> call, Response<SoldprodactList> response) {
                onecikanlar=response.body().getSoldprodacts();
                Log.i("onecikanlar","Adı"+response.body().getSoldprodacts());
                oneCikanlarAdapter=new OneCikanlarAdapter(onecikanlar,getContext());
                rvOneCikanlar.setAdapter(oneCikanlarAdapter);
            }
            @Override
            public void onFailure(Call<SoldprodactList> call, Throwable t) {
                // Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("error-------------->",t.getLocalizedMessage());
            }
        });
    }
    public void IndirimdekilerGetir(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();
        Api api = retrofit.create(Api.class);
        Call<SoldprodactList> call = api.getIndirimlilerGetir();
        call.enqueue(new Callback<SoldprodactList>() {
            @Override
            public void onResponse(Call<SoldprodactList> call, Response<SoldprodactList> response) {
                indirimdekiler=response.body().getSoldprodacts();
                Log.i("indirimdekiler","Adı"+response.body().getSoldprodacts());
                indirimdekilerAdapter=new IndirimdekilerAdapter(indirimdekiler,getContext());
                rvIndirimdekiler.setAdapter(indirimdekilerAdapter);
            }
            @Override
            public void onFailure(Call<SoldprodactList> call, Throwable t) {
                // Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("error-------------->",t.getLocalizedMessage());
            }
        });
    }
}
