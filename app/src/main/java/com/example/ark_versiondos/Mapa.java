package com.example.ark_versiondos;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Mapa extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
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
        LatLng SimbrahTexas = new LatLng(31.8160381, -99.5120986);
        mMap.addMarker(new MarkerOptions().position(SimbrahTexas).title("Simbrah").snippet("Orginaria de Texas, EEUU").icon(BitmapDescriptorFactory.fromResource(R.drawable.iconolocation)));

        LatLng PardoAlpesSuizos = new LatLng(46.416667, 10);
        mMap.addMarker(new MarkerOptions().position(PardoAlpesSuizos).title("Pardo Suizo").snippet("Orginaria de Alpes, Suiza").icon(BitmapDescriptorFactory.fromResource(R.drawable.iconolocation)));

        LatLng Brahman = new LatLng(20.5936832, 78.962883);
        mMap.addMarker(new MarkerOptions().position(Brahman).title("Brahman").snippet("Orginaria de la India").icon(BitmapDescriptorFactory.fromResource(R.drawable.iconolocation)));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(SimbrahTexas));
    }
}

