package edu.gatech.cats.cats_2340.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;



import edu.gatech.cats.cats_2340.R;

/**
 * Searching screen of the time range of the graph
 */
public class GraphTimeRange extends AppCompatActivity {
    // Start and end dates from the field
    private TextView _startDate;
    private TextView _endDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_time_range);

        //Get the text
        _startDate = (TextView) findViewById(R.id.startGraph);
        _endDate = (TextView) findViewById(R.id.endGraph);
    }

    /**
     * Fires the search and grabs the counts for the specified date range
     * @param view The view
     */
    public void onSearchPressed(View view) {

        //Get the text, put in in an array, and give it to the next activity
        String startDateStr = _startDate.getText().toString();
        String endDateStr = _endDate.getText().toString();
        String[] dates = {startDateStr, endDateStr};

        Intent i = new Intent(getBaseContext(), GraphActivity.class);
        i.putExtra("dates", dates);
        startActivity(i);
    }

    /**
     * What fires when cancel is pressed
     * @param view The view
     */
    public void onCancelPressed(View view) {
        startActivity(new Intent(getBaseContext(), ApplicationActivity.class));
    }
}
