package edu.gatech.cats.cats_2340.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import edu.gatech.cats.cats_2340.R;
import edu.gatech.cats.cats_2340.model.BuroughType;
import edu.gatech.cats.cats_2340.model.LocationType;
import edu.gatech.cats.cats_2340.model.RatSighting;
import edu.gatech.cats.cats_2340.model.BuroughType;
import edu.gatech.cats.cats_2340.model.LocationType;
import edu.gatech.cats.cats_2340.model.Model;
import edu.gatech.cats.cats_2340.model.RatSighting;
import edu.gatech.cats.cats_2340.model.User;

public class RatSightingList extends AppCompatActivity implements AdapterView.OnItemClickListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rat_sighting_list);

        // BACK button
        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),ApplicationActivity.class));
            }
        });


        // SQL Object to give access to methods
        SQLController control = SQLController.getSQLController();

        final Model model = Model.getInstance();

        //Get list view
        ListView listView = (ListView) findViewById(R.id.ratInfoList);


        // Pulls in the getAllSightings method to the ArrayAdapter
        ArrayAdapter<RatSighting> adapter = new ArrayAdapter<RatSighting>(this, android.R.layout.simple_list_item_1, control.getAllSightings());

        //Connect our list to the adapter
        listView.setAdapter(adapter);

        // If the reset Button is clicked, we should
        Button reset = (Button) findViewById(R.id.resetButton);

        //If an item is clicked we want to:
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int posn, long id) {
                Log.d("testing", "Item clicked listener");

                // Don't think this works
                model.setCurrentRat((RatSighting) av.getItemAtPosition(posn));

                Intent i = new Intent(getBaseContext(),RatSightingScreen.class);

                // Getting the rat sighting and passing it to the next activity
                RatSighting tmp = (RatSighting) av.getItemAtPosition(posn);
                i.putExtra("RatSighting", tmp);

                startActivity(i);
                finish();
            }
        });
    }



    //Don't think this does anything but lets keep it for now
    public void onItemClick(AdapterView<?> adapter, View listView, int position, long id) {
        Log.d("testing", "Item clicked");
        startActivity(new Intent(getBaseContext(),RatSightingScreen.class));
        finish();
    }

    public void resetRatData(View view) {
        try {
            SQLController.getSQLController().clearRatTable();
            InputStream stream = getResources().openRawResource(R.raw.data);
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, Charset.forName("UTF-8")));
            String row = "";
            row = reader.readLine();
            while ((row = reader.readLine()) != null) {
                String[] s = row.split(",");
                // Import data from each column into a new RatSighting()
                //RatSighting sighting = new RatSighting(s[0], s[1], s[7], s[8], s[9], s[16], s[23], s[49], s[50]);
                if (s == null) continue;
                if (s.length == 0) continue;
                RatSighting sighting = new RatSighting();
                sighting.setKey(Integer.parseInt(s[0]));
                if (sighting.getKey() % 10 == 0)
                    Log.d("INFO", "Key- " + sighting.getKey());
                if (s.length > 0 && s[1] != null && s[1].length() != 0 && s[1] != "N/A")
                    sighting.setCreated(sighting.formatDateString(s[1]));
                if (s.length > 6 && s[7] != null && s[7].length() != 0 && s[7] != "N/A")
                    s[7] = s[7].replace("'", "");
                    sighting.setLocationType(LocationType.toLocationType(s[7]));
                if (s.length > 7 && s[8] != null && s[8].length() != 0 && s[8] != "N/A") {
                    s[8] = s[8].replace("'", "");
                    sighting.setZip(Integer.parseInt(s[8]));
                }
                if (s.length > 8 && s[9] != null && s[9].length() != 0 && s[9] != "N/A")
                    s[9] = s[9].replace("'", "");
                    sighting.setAddress(s[9]);
                if (s.length > 15 && s[16] != null && s[16].length() != 0 && s[16] != "N/A")
                    s[16] = s[16].replace("'", "");
                    sighting.setCity(s[16]);
                if (s.length > 22 && s[23] != null && s[23].length() != 0 && s[23] != "N/A")
                    s[23] = s[23].replace("'", "");
                    sighting.setBorough(BuroughType.toBuroughType(s[23]));
                if (s.length > 48 && s[49] != null && s[49].length() != 0 && s[49] != "N/A") {
                    sighting.setLatitude(Float.parseFloat(s[49]));
                }
                if (s.length > 49 && s[50] != null && s[50].length() != 0 && s[50] != "N/A") {
                    sighting.setLongitude(Float.parseFloat(s[50]));
                }
                User csvuser = SQLController.getSQLController().getUser("CSV");
                SQLController.getSQLController().addRatSighting(sighting, csvuser);
            }
            Log.d("INFO:", "Data has been reloaded");
        } catch (Exception e) {
            Log.d("ERROR: ", "RESET FAIl...");
            if (e != null) {
                Log.d("ERROR: ", " " + e.getMessage());
            }
        }
    }
}
