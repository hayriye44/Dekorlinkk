package com.example.hayri.dekorlink.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import android.view.MenuItem;

import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.hayri.dekorlink.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AnasayfaFragment extends Fragment {
    private View view;
    RecyclerView rvYemekler;
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
        evDekorasyon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating the instance of PopupMenu

                showPopupMenuEvAydınlatma(v);
            }
        });
        // Inflate the layout for this fragment

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

}
