package edu.gatech.cats.cats_2340.controllers;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

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
        //Fairly obvious chaining
        Intent currentIntent = getIntent();
        sightings = (Collection<RatSighting>) currentIntent.getSerializableExtra("mapsList");
        if (sightings == null) {
            sightings = new ArrayList<>();
            SQLController sql = SQLController.getSQLController();
            Collections.addAll(sightings, sql.getAllSightings());
        }
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        FragmentManager sfm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) sfm
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
            LatLng latlngTuple = rs.getLatLong();
            MarkerOptions mOptions= new MarkerOptions();
            mOptions.position(latlngTuple);
            mOptions.title(rs.toString());
            googleMap.addMarker(mOptions);
            //latlngTups.add(latlngTuple);
        }

        final double newYorkLat = 40.7128;
        final double newYorkLong = -74.006;
        final float defaultZoom = 10.0f;
        //Hard-coded NY lat/Long
        googleMap.moveCamera(
                CameraUpdateFactory.newLatLngZoom(new LatLng(newYorkLat,newYorkLong), defaultZoom));

    }

}
