package com.example.hayri.dekorlink;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.hayri.dekorlink.Fragments.AnasayfaFragment;
import com.example.hayri.dekorlink.Fragments.FavorilerFragment;
import com.example.hayri.dekorlink.Fragments.ProfilFragment;

public class AnaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana);
        //check if user is logged in
        if (!SharedPref.getInstance(this).isLoggedIn()) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
        //Bottom menüde mebülere tıklanınca ortadaki alanı değiştime  ama menünün altta kalması
        BottomNavigationView navigationView=findViewById(R.id.bottom_nav);

        final AnasayfaFragment anasayfaFragment=new AnasayfaFragment();
        final FavorilerFragment favorilerFragment=new FavorilerFragment();
        final ProfilFragment profilFragment =new ProfilFragment();
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id=menuItem.getItemId();
                if(id==R.id.anasayfa)
                {
                    setFragment(anasayfaFragment);
                    return  true;
                }
                else if(id==R.id.favoriler)
                {
                    setFragment(favorilerFragment);
                    return true;
                }
                else if(id==R.id.profil)
                {
                    setFragment(profilFragment);
                    return  true;
                }
                return false;
            }
        });
        navigationView.setSelectedItemId(R.id.anasayfa);


    }



    private void setFragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction =getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment);
        fragmentTransaction.commit();
    }
}
