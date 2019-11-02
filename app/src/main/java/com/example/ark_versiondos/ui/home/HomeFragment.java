package com.example.ark_versiondos.ui.home;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.ark_versiondos.R;
import com.google.android.material.tabs.TabLayout;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private View vista;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        vista = inflater.inflate(R.layout.fragment_home, container, false);
        return vista;


    }
}