package com.example.ark_versiondos.ui.home;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ark_versiondos.R;

public class Comparar extends Fragment {

    private CompararViewModel mViewModel;
    private RecyclerView recyclerview1;
    private RecyclerView recyclerview2;

    public static Comparar newInstance() {
        return new Comparar();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.comparar_fragment, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(CompararViewModel.class);
        // TODO: Use the ViewModel
    }

}
