package com.example.ark_versiondos;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class cerrar  extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= super.onCreateView(inflater, container, savedInstanceState);
        Intent intent = new Intent(v.getContext(),Login.class);
        startActivity(intent);

        return  v;
    }
}
