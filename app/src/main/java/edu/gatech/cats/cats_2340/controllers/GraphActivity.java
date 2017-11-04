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
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import edu.gatech.cats.cats_2340.R;
import edu.gatech.cats.cats_2340.model.BuroughType;
import edu.gatech.cats.cats_2340.model.LocationType;
import edu.gatech.cats.cats_2340.model.RatSighting;
import edu.gatech.cats.cats_2340.model.SearchCriteria;

public class GraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        LineChart chart = (LineChart) findViewById(R.id.chart);

        String[] dates = (String[]) getIntent().getSerializableExtra("dates");

        Log.d("", dates[0].toString());
        Log.d("", dates[1].toString());

        SimpleDateFormat myDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date start = null;
        Date end = null;

        try {
            start = new java.sql.Date( new SimpleDateFormat("yyyy-MM-dd").parse(dates[0]).getTime());
            end = new java.sql.Date( new SimpleDateFormat("yyyy-MM-dd").parse(dates[1]).getTime());
        } catch (Exception e) {
            Log.d("dateerror", "coudlnt convert date");
        }

        Log.d("", start.toString());
        Log.d("", end.toString());

        SearchCriteria sc = new SearchCriteria(null, null, start, end);

        List<Entry> entries = new ArrayList<>();

        SQLController sql = SQLController.getSQLController();

        List<Integer[]> ans = sql.getFilteredCounts(sc);

        for(Integer[] r : ans) {
            entries.add(new Entry(r[0] * 12 + r[1], r[2]));
        }

        Collections.sort(entries, new EntryXComparator());

        LineDataSet ds = new LineDataSet(entries, "Sample");
        LineData ld = new LineData(ds);

        chart.setData(ld);
        chart.invalidate();

    }

    //LineChart chart = (LineChart) findViewById(R.id.chart);

    //RatSighting[] dummy = new RatSighting[2];
    //dummy[0] = new RatSighting(1, null, LocationType.BUILDING, 23114, "my house", "atlanta", BuroughType.BRONX, 69, 420);

}
