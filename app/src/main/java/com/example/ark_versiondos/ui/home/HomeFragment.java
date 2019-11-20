package com.example.ark_versiondos.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.ark_versiondos.Mapa;
import com.example.ark_versiondos.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private View vista;
    WebView wv1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        vista = inflater.inflate(R.layout.fragment_home, container, false);
        wv1 = (WebView) vista.findViewById(R.id.wv1);
        wv1.setWebViewClient(new WebViewClient());
        wv1.loadUrl("https://www.ganaderia.com/destacados/noticias");
        FloatingActionButton floatingActionButton= vista.findViewById(R.id.mapas);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(v.getContext(), Mapa.class);
                v.getContext().startActivity(intent);
            }
        });
        return vista;


    }
}