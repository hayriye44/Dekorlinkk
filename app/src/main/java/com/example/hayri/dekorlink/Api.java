package com.example.hayri.dekorlink;

import com.example.hayri.dekorlink.Model.FavoriIslemler;
import com.example.hayri.dekorlink.Model.Favoriler;
import com.example.hayri.dekorlink.Model.FavorilerList;
import com.example.hayri.dekorlink.Model.LoginModel;
import com.example.hayri.dekorlink.Model.ProdactList;
import com.example.hayri.dekorlink.Model.SepetEkle;
import com.example.hayri.dekorlink.Model.SepetListe;
import com.example.hayri.dekorlink.Model.SepetUpdateCreate;
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

    @GET("OneCikanGoster.php")
    Call<SoldprodactList> getOneCikanlarGetir();

    @GET("IndirimliGoster.php")
    Call<SoldprodactList> getIndirimlilerGetir();

    @POST("favoriler.php")
    @FormUrlEncoded
    Call<Favoriler> favoriKontrol(@Field("uye_id") String uye_id, @Field("urun_id") String urun_id);

    @POST("favoriIslemler.php")
    @FormUrlEncoded
    Call<FavoriIslemler> favoriIslemler(@Field("uye_id") String uye_id, @Field("urun_id") String urun_id);


    @POST("favorilerListesi.php")
    @FormUrlEncoded
    Call<FavorilerList> getfavorilerList(@Field("uye_id") String uye_id);


    @POST("sepetEkle.php")
    @FormUrlEncoded
    Call<SepetEkle> sepetEkle( @Field("uye_id") String uye_id);

    @POST("sepetUrunUpdateCreate.php")
    @FormUrlEncoded
    Call<SepetUpdateCreate> sepetUrunEkleGüncelle(@Field("uye_id") String uye_id , @Field("urun_id") String urun_id, @Field("adet") String adet, @Field("tutar") String tutar, @Field("sepet_id") String sepet_id);

    @POST("sepetListe.php")
    @FormUrlEncoded
    Call<SepetListe> getsepetList(@Field("sepet_id") int sepet_id);
}
