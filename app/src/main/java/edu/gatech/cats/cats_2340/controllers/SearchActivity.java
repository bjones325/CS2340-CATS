package edu.gatech.cats.cats_2340.controllers;

import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import edu.gatech.cats.cats_2340.R;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import edu.gatech.cats.cats_2340.R;
import edu.gatech.cats.cats_2340.model.BuroughType;
import edu.gatech.cats.cats_2340.model.LocationType;
import edu.gatech.cats.cats_2340.model.RatSighting;

/**
 * Created by Ruchi Banerjee on 10/26/2017.
 */

public class SearchActivity extends AppCompatActivity{
    private TextView _startDate;
    private TextView _endDate;
    private Spinner _locationType;
    private Spinner _boroughType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("Here yo");
        setContentView(R.layout.activity_search);

        _startDate = (TextView) findViewById(R.id.startDate);
        _endDate = (TextView) findViewById(R.id.endDate);
        _locationType = (Spinner) findViewById(R.id.locationSpinner);
        _boroughType = (Spinner) findViewById(R.id.boroughSpinner);

        // Set up adapter to display the allowable types inside the location type spinner
        ArrayAdapter<String> adapter1 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, LocationType.type);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        _locationType.setAdapter(adapter1);

        // Set up adapter to display the allowable types inside the borough spinner
        ArrayAdapter<String> adapter2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, BuroughType.type);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        _boroughType.setAdapter(adapter2);
    }

    public void onCancelPressed(View view) {
        startActivity(new Intent(getBaseContext(),ApplicationActivity.class));
    }

    public void onSearchPressed(View view) {
        /*
        String startDateStr= _startDate.getText().toString();
        SimpleDateFormat startdateFormat = new SimpleDateFormat("yyyy-MM-DD");
        Calendar cal = Calendar.getInstance();
        cal.set(0000,00,00);
        Date startConvertedDate = cal.getTime();


        String endDateStr = _endDate.getText().toString();
        SimpleDateFormat enddateFormat = new SimpleDateFormat("yyyy-MM-DD");
        Calendar cal = Calendar.getInstance();
        cal.set(0000,00,00);
        Date endConvertedDate = cal.getTime();

        String startDate="01-02-2013";
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-mm-yyyy");
        java.util.Date date = sdf1.parse(startDate);
        java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
        */
        String locType = _locationType.getSelectedItem().toString();
        String borType = _boroughType.getSelectedItem().toString();


        ArrayList<RatSighting> filteredRats = new ArrayList<>();
        for (RatSighting rs : SQLController.getSQLController().getAllSightings()) {
            System.out.println(rs.getBorough().toString());
            System.out.println(rs.getLocationType().toString());
            if (rs.getBorough() == BuroughType.toBuroughType(borType)
                    && rs.getLocationType() == LocationType.toLocationType(locType)) {
                /*
                if (rs.getCreated() >= startConvertedDate  && rs.getCreated() <= endConvertedDate) {
                    filteredRats.add(rs);
                }*/
                filteredRats.add(rs);
            }
        }
        Intent mapIntent = new Intent(getBaseContext(), MapsActivity.class);
        mapIntent.putExtra("mapsList", filteredRats);
        startActivity(mapIntent);
    }

}
