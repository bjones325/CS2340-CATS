package edu.gatech.cats.cats_2340.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Date;
import java.util.Locale;

import edu.gatech.cats.cats_2340.R;
import edu.gatech.cats.cats_2340.model.BoroughType;
import edu.gatech.cats.cats_2340.model.LocationType;
import edu.gatech.cats.cats_2340.model.RatSighting;


/**
 * Screen for a specific rat sighting
 */
public class RatSightingScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rat_sighting_screen);

        //Model model = Model.getInstance();

        // Get the rat sighting we were passed
        Intent inte = getIntent();
        RatSighting rs = (RatSighting) inte.getSerializableExtra("RatSighting");
        printData(rs);


        // BACK Button
        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), RatSightingList.class));
            }
        });
    }

    /**
     * Manually displays individual rat information to the rat_sighting_screen for the RatSighting
     * to be printed and viewed by the user/admin
     * @param rs instance of RatSighting
     */
    private void printData(RatSighting rs) {
        TextView key_text = (TextView) findViewById(R.id.key_field);
        TextView date_text = (TextView) findViewById(R.id.date_field);
        TextView locationType_text = (TextView) findViewById(R.id.location_field);
        TextView zip_text = (TextView) findViewById(R.id.zip_field);
        TextView address_text = (TextView) findViewById(R.id.address_field);
        TextView city_text = (TextView) findViewById(R.id.city_field);
        TextView borough_text = (TextView) findViewById(R.id.borough_field);
        TextView latitude_text = (TextView) findViewById(R.id.latitude_field);
        TextView longitude_text = (TextView) findViewById(R.id.longitude_field);

        key_text.setText(String.format(Locale.US, "%d", rs.getKey()));
        Date created = rs.getCreated();
        date_text.setText(created.toString());
        LocationType loc = rs.getLocationType();
        locationType_text.setText(loc.toString());
        zip_text.setText(String.format(Locale.US, "%d", rs.getZip()));
        address_text.setText(rs.getAddr());
        city_text.setText(rs.getCity());
        BoroughType bt = rs.getBorough();
        borough_text.setText(bt.toString());
        latitude_text.setText(String.format(Locale.US, "%f", rs.getLat()));
        longitude_text.setText(String.format(Locale.US, "%f", rs.getLong()));

    }
}
