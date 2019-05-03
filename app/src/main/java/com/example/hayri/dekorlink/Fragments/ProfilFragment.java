package com.example.hayri.dekorlink.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.hayri.dekorlink.R;
import com.example.hayri.dekorlink.Sepetim;
import com.example.hayri.dekorlink.SharedPref;

public class ProfilFragment extends Fragment {
    TextView username;
    Button btnLogout,profilsepettekilerigetir_btn;

    int kul_id;
    private View myFragment;
    public ProfilFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myFragment = inflater.inflate(R.layout.fragment_profil, container, false);
        tanimla();
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
                SharedPref.getInstance(getContext()).logout();
            }
        });
        profilsepettekilerigetir_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),Sepetim.class);
                intent.putExtra("kul_id",kul_id);
                startActivity(intent);
            }
        });
        return myFragment;
    }
    public void tanimla()
    {
        username = myFragment.findViewById(R.id.username);
        btnLogout = myFragment.findViewById(R.id.btnLogout);
        String loggedUsename = SharedPref.getInstance(getActivity()).LoggedInUser();
        username.setText("Username : "+loggedUsename);
        kul_id=SharedPref.getInstance(getActivity()).LoggedInUserId();
        Log.i("kulprofilid",""+kul_id);
        profilsepettekilerigetir_btn=myFragment.findViewById(R.id.kulsepettekilerigetir_btn);
    }

}
