package com.example.hayri.dekorlink.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.hayri.dekorlink.R;
import com.example.hayri.dekorlink.SharedPref;

public class ProfilFragment extends Fragment {
    TextView username;
    Button btnLogout;

    int kul_id;
    private View myFragment;
    public ProfilFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        myFragment = inflater.inflate(R.layout.fragment_profil, container, false);
        // Inflate the layout for this fragment
        username = myFragment.findViewById(R.id.username);
        btnLogout = myFragment.findViewById(R.id.btnLogout);
        //getting logged in user name
        String loggedUsename = SharedPref.getInstance(getActivity()).LoggedInUser();
      //  kul_id=SharedPref.getInstance(getActivity()).LoggedInUserId();
        username.setText("Username : "+loggedUsename);
        //logging out
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
                SharedPref.getInstance(getContext()).logout();
            }
        });
        return myFragment;
    }

}
