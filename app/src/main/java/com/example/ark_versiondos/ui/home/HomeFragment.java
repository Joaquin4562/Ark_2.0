package com.example.ark_versiondos.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.ark_versiondos.R;

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
        return vista;


    }
}