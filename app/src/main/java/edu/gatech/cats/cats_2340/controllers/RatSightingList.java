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

public class RatSightingList extends AppCompatActivity implements AdapterView.OnItemClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rat_sighting_list);

        /* try {
         readRatData();
         } catch (IOException e) {
         e.printStackTrace();
         Log.w("RatSightingList", "Error in reading a line from data");
         }*/

        // BACK button
        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),ApplicationActivity.class));
            }
        });


        // SQL Object to give access to methods
        SQLController control = new SQLController();

        final Model model = Model.getInstance();

        //Get list view
        ListView listView = (ListView) findViewById(R.id.ratInfoList);


        // Pulls in the getAllSightings method to the ArrayAdapter
        ArrayAdapter<RatSighting> adapter = new ArrayAdapter<RatSighting>(this, android.R.layout.simple_list_item_1, control.getAllSightings());

        //Connect our list to the adapter
        listView.setAdapter(adapter);

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


    /**
     * Loads the rat sighting data upon creation of the RatSightingList
     * @throws IOException
     */
    private void readRatData() throws IOException {
        InputStream stream = getResources().openRawResource(R.raw.data);
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream, Charset.forName("UTF-8")));

        String row = "";
        row = reader.readLine();
        while ((row = reader.readLine()) != null) {
            String[] s = row.split(",");

            // Import data from each column into a new RatSighting()
            //RatSighting sighting = new RatSighting(s[0], s[1], s[7], s[8], s[9], s[16], s[23], s[49], s[50]);
            RatSighting sighting = new RatSighting();
            sighting.setKey(Integer.parseInt(s[0]));
            sighting.setCreated(s[1]);
            sighting.setLocationType(LocationType.valueOf(s[7]));
            sighting.setZip(Integer.parseInt(s[8]));
            sighting.setAddress(s[9]);
            sighting.setCity(s[16]);
            sighting.setBorough(BuroughType.valueOf(s[23]));
            sighting.setLatitude(Integer.parseInt(s[49]));
            sighting.setLongitude(Integer.parseInt(s[50]));

        }


    }

    /**
     *
     */
    private void writeRatData() {

    }

    //Don't think this does anything but lets keep it for now
    public void onItemClick(AdapterView<?> adapter, View listView, int position, long id) {
        Log.d("testing", "Item clicked");
        startActivity(new Intent(getBaseContext(),RatSightingScreen.class));
        finish();
    }
}
