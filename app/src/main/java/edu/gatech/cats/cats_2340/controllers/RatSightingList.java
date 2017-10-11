package edu.gatech.cats.cats_2340.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import edu.gatech.cats.cats_2340.R;
import edu.gatech.cats.cats_2340.model.BuroughType;
import edu.gatech.cats.cats_2340.model.LocationType;
import edu.gatech.cats.cats_2340.model.Model;
import edu.gatech.cats.cats_2340.model.RatSighting;

public class RatSightingList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rat_sighting_list);

        Model model = Model.getInstance();
        // Load in the rat .csv data


        ListView listView = (ListView) findViewById(R.id.ratInfoList);

        RatSighting[] ratArray = new RatSighting[2];
        ratArray[0] = new RatSighting(1, 2, LocationType.BUILDING, 23114, "add", "city1", BuroughType.BRONX, 2, 3);
        ratArray[1] = new RatSighting(2, 3, LocationType.COMMERCIAL_BUILDING, 30309, "add2", "cit1", BuroughType.MANHATTAN, 4, 5);

        //@Blake change this sh*t
        ArrayAdapter<RatSighting> adapter = new ArrayAdapter<RatSighting>(this, android.R.layout.simple_list_item_1, ratArray);

        listView.setAdapter(adapter);
    }
}
