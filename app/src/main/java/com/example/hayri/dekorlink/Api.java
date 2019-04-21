package com.example.hayri.dekorlink;

import com.example.hayri.dekorlink.Model.LoginModel;
import com.example.hayri.dekorlink.Model.ProdactList;
import com.example.hayri.dekorlink.Model.SoldprodactList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {
    @POST("register.php")
    @FormUrlEncoded
    Call<LoginModel> register(@Field("ad_soyad") String ad_soyad, @Field("email") String email, @Field("sifre") String sifre, @Field("TelNo") String TelNo);

    @POST("login.php")
    @FormUrlEncoded
    Call<LoginModel> login(@Field("email") String email, @Field("sifre") String sifre);

    @POST("katÜrünler.php")
    @FormUrlEncoded
    Call<ProdactList> getAllProdacts(@Field("kategori_id") int kategori_id);

    @GET("CokSatanGoster.php")
    Call<SoldprodactList> getCokSatanGetir();
}
