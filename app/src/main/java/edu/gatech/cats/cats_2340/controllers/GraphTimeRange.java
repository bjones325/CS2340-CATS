package edu.gatech.cats.cats_2340.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import edu.gatech.cats.cats_2340.R;

public class GraphTimeRange extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_time_range);
    }

    public void onSearchPressed(View view) {
        startActivity(new Intent(getBaseContext(), GraphActivity.class));
    }

    public void onCancelPressed(View view) {
        startActivity(new Intent(getBaseContext(), ApplicationActivity.class));
    }
}
