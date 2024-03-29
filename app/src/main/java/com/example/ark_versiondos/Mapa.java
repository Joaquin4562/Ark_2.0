package com.example.ark_versiondos;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Marker;
public class Mapa extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap, googleM;
    private Marker markp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_mapa);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng pardo = new LatLng(46.7985624, 8.2319736);
        mMap.addMarker(new MarkerOptions().position(pardo).title("Pardo Suizo").snippet("Gran docilidad, lo que facilita su manejo"));

        LatLng brahman = new LatLng(39.7837304, -100.4458825);
        mMap.addMarker(new MarkerOptions().position(brahman).title("Brahman").snippet("UTILIZADA COMO HERRAMIENTA PARA PRODUCIR CARNE"));


        LatLng simbrah = new LatLng(27.96902519476781, -100.08389412633177);
        mMap.addMarker(new MarkerOptions().position(simbrah).title("Simbrah").snippet("100% de adaptación a cualquier tipo de clima debido a su rusticidad."));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(simbrah, 4));


    }

}

