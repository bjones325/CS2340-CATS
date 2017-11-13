package edu.gatech.cats.cats_2340.controllers;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import edu.gatech.cats.cats_2340.R;
import edu.gatech.cats.cats_2340.model.RatSighting;

/**
 * The map screen
 */
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private Collection<RatSighting> sightings;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sightings = (Collection<RatSighting>) getIntent().getSerializableExtra("mapsList");
        if (sightings == null) {
            sightings = new ArrayList<>();
            Collections.addAll(sightings, SQLController.getSQLController().getAllSightings());
        }
        setContentView(R.layout.activity_maps);
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

        //Collection<LatLng> latlngTups = new ArrayList<>();
        for (RatSighting rs : sightings) {
            LatLng latlngTuple = new LatLng(rs.getLat(), rs.getLong());
            googleMap.addMarker(new MarkerOptions().position(latlngTuple).title(rs.toString()));
            //latlngTups.add(latlngTuple);
        }

        //Hard-coded NY lat/Long
        googleMap.moveCamera(
                CameraUpdateFactory.newLatLngZoom(new LatLng(40.7128,-74.006), 10.0f));

    }

}
