package com.example.hayri.dekorlink.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.MenuItem;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.hayri.dekorlink.Adapters.KatProdactsAdapter;
import com.example.hayri.dekorlink.Api;
import com.example.hayri.dekorlink.Model.ProdactList;
import com.example.hayri.dekorlink.Model.ProductsItem;
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
    RecyclerView rvÜrünler;

    List<ProductsItem> prodactList;
    KatProdactsAdapter ürünAdapter;
    public AnasayfaFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_anasayfa, container, false);
        Button anaSayfa = (Button)view.findViewById(R.id.anaSayfa);
        Button mobilya = (Button)view.findViewById(R.id.mobilya);


        mobilya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllProductList(2);
                showPopupMenuMobilya(v);
            }
        });
        Button mutfak = (Button)view.findViewById(R.id.mutfak);
        mutfak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating the instance of PopupMenu
                showPopupMenuMutfak(v);
            }
        });
        Button evDekorasyon = (Button)view.findViewById(R.id.evDekorasyon);
        evDekorasyon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating the instance of PopupMenu
                showPopupMenuEvDekorasyon(v);
            }
        });
        Button aydınlatma = (Button)view.findViewById(R.id.aydınlatma);
        aydınlatma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating the instance of PopupMenu
                showPopupMenuEvAydınlatma(v);
            }
        });
        // Inflate the layout for this fragment
        rvÜrünler=view.findViewById(R.id.rvKatÜrünler);
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(getContext(),2);
        rvÜrünler.setLayoutManager(layoutManager);
        prodactList=new ArrayList<>();

        return view;
    }
    private void showPopupMenuMobilya(View view) {
//      // TODO Auto-generated method stub
        PopupMenu popupmenu = new PopupMenu(getActivity(), view);
        popupmenu.getMenuInflater().inflate(R.menu.mobilya_alt_menu, popupmenu.getMenu());
        popupmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuitem) {
                // TODO Auto-generated method stub
                switch (menuitem.getItemId()) {
                    case R.id.tvUnite:
                        // do your code
                        return true;
            /*case R.id.upload_item:
                // do your code
                return true;
            case R.id.copy_item:
                // do your code
                return true;
            case R.id.print_item:
                // do your code
                return true;
            case R.id.share_item:
                // do your code
                return true;
            case R.id.bookmark_item:
                // do your code
                return true;*/
                    default:
                        return false;
                }

            }
        });

        popupmenu.show();
    }
    private void showPopupMenuMutfak(View view) {
//      // TODO Auto-generated method stub
        PopupMenu popupmenu = new PopupMenu(getActivity(), view);
        popupmenu.getMenuInflater().inflate(R.menu.mutfak_alt_menu, popupmenu.getMenu());
        popupmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuitem) {
                // TODO Auto-generated method stub
                switch (menuitem.getItemId()) {
                    case R.id.tvUnite:
                        // do your code
                        return true;
            /*case R.id.upload_item:
                // do your code
                return true;
            case R.id.copy_item:
                // do your code
                return true;
            case R.id.print_item:
                // do your code
                return true;
            case R.id.share_item:
                // do your code
                return true;
            case R.id.bookmark_item:
                // do your code
                return true;*/
                    default:
                        return false;
                }

            }
        });

        popupmenu.show();
    }
    private void showPopupMenuEvDekorasyon(View view) {
//      // TODO Auto-generated method stub
        PopupMenu popupmenu = new PopupMenu(getActivity(), view);
        popupmenu.getMenuInflater().inflate(R.menu.evdekorasyon_alt_menu, popupmenu.getMenu());
        popupmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuitem) {
                // TODO Auto-generated method stub
                switch (menuitem.getItemId()) {
                    case R.id.tvUnite:
                        // do your code
                        return true;
            /*case R.id.upload_item:
                // do your code
                return true;
            case R.id.copy_item:
                // do your code
                return true;
            case R.id.print_item:
                // do your code
                return true;
            case R.id.share_item:
                // do your code
                return true;
            case R.id.bookmark_item:
                // do your code
                return true;*/
                    default:
                        return false;
                }

            }
        });

        popupmenu.show();
    }
    private void showPopupMenuEvAydınlatma(View view) {
//      // TODO Auto-generated method stub
        PopupMenu popupmenu = new PopupMenu(getActivity(), view);
        popupmenu.getMenuInflater().inflate(R.menu.aydinlatma_alt_menu, popupmenu.getMenu());
        popupmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuitem) {
                // TODO Auto-generated method stub
                switch (menuitem.getItemId()) {
                    case R.id.tvUnite:
                        // do your code
                        return true;
            /*case R.id.upload_item:
                // do your code
                return true;
            case R.id.copy_item:
                // do your code
                return true;
            case R.id.print_item:
                // do your code
                return true;
            case R.id.share_item:
                // do your code
                return true;
            case R.id.bookmark_item:
                // do your code
                return true;*/
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
                Toast.makeText(getContext(),"Sistemde kayıtlı"+prodactList.size()+"yemek var",Toast.LENGTH_LONG).show();

                Log.i("Yemekler",response.body().getProducts().toString());
            }
            @Override
            public void onFailure(Call<ProdactList> call, Throwable t) {
                Toast.makeText(getContext(), "Bu kategoride ürün bulunmamakta", Toast.LENGTH_SHORT).show();
                Log.d("error-------------->",t.getLocalizedMessage());
            }
        });
    }

}
