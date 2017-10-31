package edu.gatech.cats.cats_2340.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.EntryXComparator;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.gatech.cats.cats_2340.R;
import edu.gatech.cats.cats_2340.model.BuroughType;
import edu.gatech.cats.cats_2340.model.LocationType;
import edu.gatech.cats.cats_2340.model.RatSighting;

public class GraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        LineChart chart = (LineChart) findViewById(R.id.chart);

        RatSighting[] dummy = new RatSighting[2];

        List<Entry> entries = new ArrayList<>();

        SQLController sql = SQLController.getSQLController();

        List<int[]> ans = sql.getRatCount();

        for(int[] r : ans) {
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
