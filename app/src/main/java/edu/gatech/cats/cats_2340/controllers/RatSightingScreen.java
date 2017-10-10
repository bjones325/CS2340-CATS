package edu.gatech.cats.cats_2340.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

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
        rs = model.getCurrentRat();
        printData(rs);
    }

    private void printData(RatSighting rs) {
        TextView key = (TextView) findViewById(R.id.date_field);

        key.setText(rs.getKey());
    }
}
