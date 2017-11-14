package edu.gatech.cats.cats_2340.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.EntryXComparator;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import edu.gatech.cats.cats_2340.R;
import edu.gatech.cats.cats_2340.model.SearchCriteria;

/**
 * Graph view of the application
 */
public class GraphActivity extends AppCompatActivity {

    int monthsInYear = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_graph);
        LineChart chart = (LineChart) findViewById(R.id.chart);

        //Lets get the dates and then use them to set limits in our SQL query
        String[] dates = (String[]) getIntent().getSerializableExtra("dates");

        Log.d("", dates[0]);
        Log.d("", dates[1]);

        SimpleDateFormat myDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date start = null;
        Date end = null;

        try {
            start = new java.sql.Date(
                    ( new SimpleDateFormat("yyyy-MM-dd").parse(dates[0])).getTime());
            end = new java.sql.Date( new SimpleDateFormat("yyyy-MM-dd").parse(dates[1]).getTime());
        } catch (Exception e) {
            Log.d("date error", "couldn't convert date");
        }

        if ((start != null) && (end != null)) {
            Log.d("", start.toString());
            Log.d("", end.toString());
        }

        SearchCriteria sc = new SearchCriteria(null, null, start, end);
        List<Entry> entries = new ArrayList<>();
        SQLController sql = SQLController.getSQLController();
        Collection<Integer[]> ans = sql.getFilteredCounts(sc);

        //Add each row
        for(Integer[] r : ans) {
            entries.add(new Entry((r[0] * monthsInYear) + r[1], r[2]));
        }

        Collections.sort(entries, new EntryXComparator());

        LineDataSet ds = new LineDataSet(entries, "Sample");
        LineData ld = new LineData(ds);

        chart.setData(ld);
        chart.invalidate();

    }

}
