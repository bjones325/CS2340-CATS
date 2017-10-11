package edu.gatech.cats.cats_2340.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import edu.gatech.cats.cats_2340.R;
import edu.gatech.cats.cats_2340.model.Model;
import edu.gatech.cats.cats_2340.model.RatSighting;

public class RatSightingScreen extends AppCompatActivity {
    private RatSighting rs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rat_sighting_screen);

        Model model = Model.getInstance();

        // Get the rat sighting we were passed
        rs = (RatSighting) getIntent().getSerializableExtra("RatSighting");
        printData(rs);
    }

    private void printData(RatSighting rs) {
        TextView date_text = (TextView) findViewById(R.id.date_field);
        TextView locationType_text = (TextView) findViewById(R.id.location_field);
        TextView zip_text = (TextView) findViewById(R.id.zip_field);
        TextView address_text = (TextView) findViewById(R.id.address_field);
        TextView city_text = (TextView) findViewById(R.id.city_field);
        TextView borough_text = (TextView) findViewById(R.id.borough_field);
        TextView latitude_text = (TextView) findViewById(R.id.latitude_field);
        TextView longitude_text = (TextView) findViewById(R.id.longitude_field);

        date_text.setText(Integer.toString( rs.getKey()));
        locationType_text.setText(rs.getLocationType().toString());
        zip_text.setText(Integer.toString(rs.getZip()));
        address_text.setText(rs.getAddr());
        city_text.setText(rs.getCity());
        borough_text.setText(rs.getBorough().toString());
        latitude_text.setText(Float.toString(rs.getLat()));
        longitude_text.setText(Float.toString(rs.getLong()));

    }
}
