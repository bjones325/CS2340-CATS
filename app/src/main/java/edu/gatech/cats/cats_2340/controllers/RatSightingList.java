package edu.gatech.cats.cats_2340.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.nio.charset.Charset;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import edu.gatech.cats.cats_2340.R;
import edu.gatech.cats.cats_2340.model.BoroughType;
import edu.gatech.cats.cats_2340.model.LocationType;
import edu.gatech.cats.cats_2340.model.RatSighting;
import edu.gatech.cats.cats_2340.model.User;

/**
 * The list screen
 */
public class RatSightingList extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final int KEY_POS = 0;
    private static final int DATE_POS = 1;
    private static final int LOCATION_POS = 7;
    private static final int ZIP_POS = 8;
    private static final int ADDRESS_POS = 9;
    private static final int CITY_POS = 16;
    private static final int BOROUGH_POS = 23;
    private static final int LATITUDE_POS = 49;
    private static final int LONGITUDE_POS = 50;

    private SQLController control;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rat_sighting_list);

        // BACK button
        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), ApplicationActivity.class));
            }
        });


        // SQL Object to give access to methods
        control = SQLController.getSQLController();

        //final Model model = Model.getInstance();

        //Get list view
        ListView listView = (ListView) findViewById(R.id.ratInfoList);


        // Pulls in the getAllSightings method to the ArrayAdapter
        ListAdapter adapter =
                new ArrayAdapter<>(
                        this, android.R.layout.simple_list_item_1, control.getAllSightings());

        //Connect our list to the adapter
        listView.setAdapter(adapter);

        // If the reset Button is clicked, we should
        //Button reset = (Button) findViewById(R.id.resetButton);

        //If an item is clicked we want to:
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int position, long id) {
                Log.d("testing", "Item clicked listener");

                Intent i = new Intent(getBaseContext(),RatSightingScreen.class);

                // Getting the rat sighting and passing it to the next activity
                Serializable tmp = (RatSighting) av.getItemAtPosition(position);
                i.putExtra("RatSighting", tmp);

                startActivity(i);
                finish();
            }
        });
    }



    //Don't think this does anything but lets keep it for now
    @Override
    public void onItemClick(AdapterView<?> adapter, View listView, int position, long id) {
        Log.d("testing", "Item clicked");
        startActivity(new Intent(getBaseContext(), RatSightingScreen.class));
        finish();
    }

    /**
     * Totally resets the database
     * @param view Android screen view
     */
    public void resetRatData(View view) {
        try {
            control.clearRatTable();
            InputStream stream = getResources().openRawResource(R.raw.data);
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(stream, Charset.forName("UTF-8")));
            String row = reader.readLine();
            while (row != null) {
                String[] s = row.split(",");
                // Import data from each column into a new RatSighting()

                /* Since s could be empty we cannot reverse it*/
                if (s.length == KEY_POS) {
                    continue;
                }
                RatSighting sighting = new RatSighting();
                sighting.setKey(Integer.parseInt(s[KEY_POS]));
                if ((sighting.getKey() % 10) == 0) {
                    Log.d("INFO", "Key- " + sighting.getKey());
                }
                if ((s.length >= DATE_POS) && (s[DATE_POS] != null) && (!s[DATE_POS].isEmpty())
                        && ("N/A".equals(s[DATE_POS]))) {
                    sighting.setCreated(sighting.formatDateString(s[DATE_POS]));
                }
                if ((s.length >= LOCATION_POS) && (s[LOCATION_POS] != null)
                        && (!s[LOCATION_POS].isEmpty()) && ("N/A".equals(s[LOCATION_POS]))) {
                    s[LOCATION_POS] = s[LOCATION_POS].replace("'", "");
                    sighting.setLocationType(LocationType.toLocationType(s[7]));
                }
                if ((s.length >= ZIP_POS) && (s[ZIP_POS] != null) && (!s[ZIP_POS].isEmpty())
                        && ("N/A".equals(s[ZIP_POS]))) {
                    s[ZIP_POS] = s[ZIP_POS].replace("'", "");
                    sighting.setZip(Integer.parseInt(s[ZIP_POS]));
                }
                if ((s.length >= ADDRESS_POS) && (s[ADDRESS_POS] != null)
                        && (!s[ADDRESS_POS].isEmpty()) && ("N/A".equals(s[ADDRESS_POS]))) {
                    s[ADDRESS_POS] = s[ADDRESS_POS].replace("'", "");
                    sighting.setAddress(s[ADDRESS_POS]);
                }
                if ((s.length >= CITY_POS) && (s[CITY_POS] != null)&& (!s[CITY_POS].isEmpty())
                        && ("N/A".equals(s[CITY_POS]))) {
                    s[CITY_POS] = s[CITY_POS].replace("'", "");
                    sighting.setCity(s[CITY_POS]);
                }
                if ((s.length >= BOROUGH_POS) && (s[BOROUGH_POS] != null)
                        && (!s[BOROUGH_POS].isEmpty()) && ("N/A".equals(s[BOROUGH_POS]))) {
                    s[BOROUGH_POS] = s[BOROUGH_POS].replace("'", "");
                    sighting.setBorough(BoroughType.toBoroughType(s[BOROUGH_POS]));
                }
                if ((s.length >= LATITUDE_POS) && (s[LATITUDE_POS] != null)
                        && (!s[LATITUDE_POS].isEmpty()) && ("N/A".equals(s[LATITUDE_POS]))) {
                    sighting.setLatitude(Float.parseFloat(s[LATITUDE_POS]));
                }
                if ((s.length > LONGITUDE_POS) && (s[LONGITUDE_POS] != null)
                        && (!s[LONGITUDE_POS].isEmpty()) && ("N/A".equals(s[LONGITUDE_POS]))) {
                    sighting.setLongitude(Float.parseFloat(s[LONGITUDE_POS]));
                }
                User user = control.getUser("CSV");
                control.addRatSighting(sighting, user);
                row = reader.readLine();
            }
            Log.d("INFO:", "Data has been reloaded");
        } catch (Exception e) {
            Log.d("ERROR: ", "RESET FAIl...");
            Log.d("ERROR: ", " " + e.getMessage());
        }
    }
}
