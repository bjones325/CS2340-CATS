package edu.gatech.cats.cats_2340.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;

import java.sql.Date;

import edu.gatech.cats.cats_2340.R;
import edu.gatech.cats.cats_2340.model.BuroughType;
import edu.gatech.cats.cats_2340.model.LocationType;
import edu.gatech.cats.cats_2340.model.RatSighting;

public class GraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
    }

    LineChart chart = (LineChart) findViewById(R.id.chart);

    RatSighting[] dummy = new RatSighting[2];
    //dummy[0] = new RatSighting(1, null, LocationType.BUILDING, 23114, "my house", "atlanta", BuroughType.BRONX, 69, 420);

}
