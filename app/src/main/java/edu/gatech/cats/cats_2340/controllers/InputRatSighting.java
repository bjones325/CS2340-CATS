package edu.gatech.cats.cats_2340.controllers;

import android.app.Application;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Date;

import edu.gatech.cats.cats_2340.R;
import edu.gatech.cats.cats_2340.model.BuroughType;
import edu.gatech.cats.cats_2340.model.LocationType;
import edu.gatech.cats.cats_2340.model.Model;
import edu.gatech.cats.cats_2340.model.RatSighting;
import edu.gatech.cats.cats_2340.model.User;

import static edu.gatech.cats.cats_2340.R.id.address;
import static edu.gatech.cats.cats_2340.R.id.borough_spinner;
import static edu.gatech.cats.cats_2340.R.id.city;

public class InputRatSighting extends AppCompatActivity {
    private TextView latText;
    private TextView longText;
    private TextView dateText;
    private TextView timeText;
    private Spinner locationType;
    private TextView zipText;
    private TextView addressText;
    private TextView cityText;
    private Spinner borough;
    private TextView failReportText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_rat_sighting);

        Model model = Model.getInstance();

        latText = (TextView) findViewById(R.id.latitude);
        longText = (TextView) findViewById(R.id.longitude);
        dateText = (TextView) findViewById(R.id.date);
        timeText = (TextView) findViewById(R.id.time);
        locationType = (Spinner) findViewById(R.id.location_type_spinner);
        zipText = (TextView) findViewById(R.id.zip);
        addressText = (TextView) findViewById(address);
        cityText = (TextView) findViewById(city);
        borough = (Spinner) findViewById(borough_spinner);

        failReportText = (TextView) findViewById(R.id.invalidReport);
        failReportText.setVisibility(View.INVISIBLE);

        // Set up adapter to display the allowable types inside the location type spinner
        ArrayAdapter<String> adapter1 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, User.type);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationType.setAdapter(adapter1);

        // Set up adapter to display the allowable types inside the borough spinner
        ArrayAdapter<String> adapter2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, User.type);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        borough.setAdapter(adapter2);

    }

    public void onSubmitPressed(View view) {
        Model model = Model.getInstance();

        String latStr = latText.getText().toString();
        String lonStr = longText.getText().toString();
        String dateStr = dateText.getText().toString();
        String locationStr = locationType.getSelectedItem().toString();
        String timeStr = timeText.getText().toString();
        String zipStr = zipText.getText().toString();
        String addressStr = addressText.getText().toString();
        String cityStr = cityText.getText().toString();
        String boroughStr = borough.getSelectedItem().toString();

        if (latStr.equals("") || lonStr.equals("") || dateStr.equals("") || timeStr.equals("") ||
                zipStr.equals("") || addressStr.equals("") || cityStr.equals("")) {
            failReportText.setVisibility(View.VISIBLE);
            return;
        }

        //@BLAKE: HELP PLEASE :)
        //RatSighting newRatSighting = new RatSighting(0, dateStr, locationStr, zipStr,
                //addressStr, cityStr, boroughStr, latStr, lonStr);
        RatSighting newRatSighting = new RatSighting();
        //
        // **Must Add a key.. is it randomized or the next integer?**
        //
        if (dateStr.length() != 0)
            newRatSighting.setCreated(dateStr);
        if (locationStr.length() != 0)
            newRatSighting.setLocationType(LocationType.toLocationType(locationStr));
        if (zipStr.length() != 0) {
            newRatSighting.setZip(Integer.parseInt(zipStr));
        }
        if (addressStr.length() != 0)
            newRatSighting.setAddress(addressStr);
        if (cityStr.length() != 0)
            newRatSighting.setCity(cityStr);
        if (boroughStr.length() != 0)
            newRatSighting.setBorough(BuroughType.toBuroughType(boroughStr));
        if (latStr.length() != 0) {
            newRatSighting.setLatitude(Float.parseFloat(latStr));
        }
        if (lonStr.length() != 0) {
            newRatSighting.setLongitude(Float.parseFloat(lonStr));
        }

        //model.addReport(newRatSighting);
        startActivity(new Intent(getBaseContext(),ApplicationActivity.class));
        finish();
    }

    public void onCancelPressed(View view) {
        startActivity(new Intent(getBaseContext(),ApplicationActivity.class));
        finish();
    }
}
