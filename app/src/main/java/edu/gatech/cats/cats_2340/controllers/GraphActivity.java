package edu.gatech.cats.cats_2340.controllers;

import android.content.Intent;
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
import java.util.Locale;

import edu.gatech.cats.cats_2340.R;
import edu.gatech.cats.cats_2340.model.SearchCriteria;

/**
 * Graph view of the application
 */
public class GraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_graph);
        LineChart chart = (LineChart) findViewById(R.id.chart);

        //Lets get the dates and then use them to set limits in our SQL query

        //Chaining fine because you get the intent once
        Intent intent = getIntent();
        String[] dates = (String[]) intent.getSerializableExtra("dates");

        Log.d("", dates[0]);
        Log.d("", dates[1]);

        //SimpleDateFormat myDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date start = null;
        Date end = null;
        SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        try {
            java.util.Date parsed = dateFmt.parse(dates[0]);
            start = new java.sql.Date(parsed.getTime());
            java.util.Date endParsed = dateFmt.parse(dates[1]);
            end = new java.sql.Date(endParsed.getTime());
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
        //This is the point of sql controller
        Collection<Integer[]> ans = sql.getFilteredCounts(sc);

        final int monthsInYear = 12;

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
