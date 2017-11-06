package edu.gatech.cats.cats_2340.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;



import edu.gatech.cats.cats_2340.R;
import edu.gatech.cats.cats_2340.model.SearchCriteria;

public class GraphTimeRange extends AppCompatActivity {

    TextView _startDate;
    TextView _endDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_time_range);

        _startDate = (TextView) findViewById(R.id.startGraph);
        _endDate = (TextView) findViewById(R.id.endGraph);
    }

    /**
     *
     * @param view
     */
    public void onSearchPressed(View view) {
        String startDateStr = _startDate.getText().toString();
        String endDateStr = _endDate.getText().toString();
        String[] dates = {startDateStr, endDateStr};

        Intent i = new Intent(getBaseContext(), GraphActivity.class);
        i.putExtra("dates", dates);
        startActivity(i);
    }

    /**
     *
     * @param view
     */
    public void onCancelPressed(View view) {
        startActivity(new Intent(getBaseContext(), ApplicationActivity.class));
    }
}
