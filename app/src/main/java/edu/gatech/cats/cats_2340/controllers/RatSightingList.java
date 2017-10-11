package edu.gatech.cats.cats_2340.controllers;

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
import android.widget.ArrayAdapter;
import android.widget.ListView;

import edu.gatech.cats.cats_2340.R;
import edu.gatech.cats.cats_2340.model.BuroughType;
import edu.gatech.cats.cats_2340.model.LocationType;
import edu.gatech.cats.cats_2340.model.RatSighting;
import edu.gatech.cats.cats_2340.model.BuroughType;
import edu.gatech.cats.cats_2340.model.LocationType;
import edu.gatech.cats.cats_2340.model.Model;
import edu.gatech.cats.cats_2340.model.RatSighting;

public class RatSightingList extends AppCompatActivity {

    List<RatSighting> ratData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rat_sighting_list);

        try {
            readRatData();
        } catch (IOException e) {
            e.printStackTrace();
            Log.w("RatSightingList", "Error in reading a line from data");
        }
    }
        Model model = Model.getInstance();
        // Load in the rat .csv data


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

            ratData.add(sighting);
        }

        ListView listView = (ListView) findViewById(R.id.ratInfoList);

        RatSighting[] ratArray = new RatSighting[2];
        ratArray[0] = new RatSighting(1, 2, LocationType.BUILDING, 23114, "add", "city1", BuroughType.BRONX, 2, 3);
        ratArray[1] = new RatSighting(2, 3, LocationType.COMMERCIAL_BUILDING, 30309, "add2", "cit1", BuroughType.MANHATTAN, 4, 5);

        //@Blake change this sh*t
        ArrayAdapter<RatSighting> adapter = new ArrayAdapter<RatSighting>(this, android.R.layout.simple_list_item_1, ratArray);

        listView.setAdapter(adapter);
    }

    /**
     *
     */
    private void writeRatData() {

    }

}
