package edu.gatech.cats.cats_2340.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import edu.gatech.cats.cats_2340.R;
import edu.gatech.cats.cats_2340.model.BoroughType;
import edu.gatech.cats.cats_2340.model.LocationType;
import edu.gatech.cats.cats_2340.model.Model;
import edu.gatech.cats.cats_2340.model.RatSighting;

import static edu.gatech.cats.cats_2340.R.id.address;
import static edu.gatech.cats.cats_2340.R.id.borough_spinner;
import static edu.gatech.cats.cats_2340.R.id.city;

/**
 * View for making your own rat sighting
 */
public class InputRatSighting extends AppCompatActivity {
    private TextView latText;
    private TextView longText;
    private TextView dateText;
    private TextView timeText;
    private Spinner locationType;
    private TextView zipText;
    private TextView addressText;
    private TextView cityText;
    private Spinner borough;
    private TextView failReportText;

    SQLController controller = new SQLController();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_rat_sighting);

        //why?
        Model model = Model.getInstance();

        latText = (TextView) findViewById(R.id.latitude);
        longText = (TextView) findViewById(R.id.longitude);
        dateText = (TextView) findViewById(R.id.date);
        timeText = (TextView) findViewById(R.id.time);
        locationType = (Spinner) findViewById(R.id.location_type_spinner);
        zipText = (TextView) findViewById(R.id.zip);
        addressText = (TextView) findViewById(address);
        cityText = (TextView) findViewById(city);
        borough = (Spinner) findViewById(borough_spinner);

        failReportText = (TextView) findViewById(R.id.invalidReport);
        failReportText.setVisibility(View.INVISIBLE);

        // Set up adapter to display the allowable types inside the location type spinner
        ArrayAdapter<String> adapter1 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, LocationType.type);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationType.setAdapter(adapter1);

        // Set up adapter to display the allowable types inside the borough spinner
        ArrayAdapter<String> adapter2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, BoroughType.type);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        borough.setAdapter(adapter2);

    }

    /**
     * Upon clicking the submit button, it first checks if all fields are filled out.
     * If true, then the data is added as a new rat sighting and added to the controller.
     * Moves view to the main application window
     * @param view The view
     */
    public void onSubmitPressed(View view) {
        Model model = Model.getInstance();

        String latStr = latText.getText().toString();
        String lonStr = longText.getText().toString();
        String dateStr = dateText.getText().toString();
        String locationStr = locationType.getSelectedItem().toString();
        String timeStr = timeText.getText().toString();
        String zipStr = zipText.getText().toString();
        String addressStr = addressText.getText().toString();
        String cityStr = cityText.getText().toString();
        String boroughStr = borough.getSelectedItem().toString();

        if ("".equals(latStr) || "".equals(lonStr) || "".equals(dateStr) || "".equals(timeStr) ||
                "".equals(zipStr) || "".equals(addressStr) || "".equals(cityStr)) {
            failReportText.setVisibility(View.VISIBLE);
            return;
        }

        RatSighting newRatSighting = new RatSighting();

        // **Must Add a key.. is it randomized or the next integer?** -Mark
        //
        if (!dateStr.isEmpty()) {
            newRatSighting.setCreated(dateStr);
        }
        if (!locationStr.isEmpty()) {
            newRatSighting.setLocationType(LocationType.toLocationType(locationStr));
        }
        if (!zipStr.isEmpty()) {
            newRatSighting.setZip(Integer.parseInt(zipStr));
        }
        if (!addressStr.isEmpty()) {
            newRatSighting.setAddress(addressStr);
        }
        if (!cityStr.isEmpty()) {
            newRatSighting.setCity(cityStr);
        }
        if (!boroughStr.isEmpty()) {
            newRatSighting.setBorough(BoroughType.toBoroughType(boroughStr));
        }
        if (!latStr.isEmpty()) {
            newRatSighting.setLatitude(Float.parseFloat(latStr));
        }
        if (!lonStr.isEmpty()) {
            newRatSighting.setLongitude(Float.parseFloat(lonStr));
        }

        // adding the rat sighting to the controller
        controller.addRatSighting(newRatSighting, Model.getInstance().getCurrentUser());

        //model.addReport(newRatSighting);
        startActivity(new Intent(getBaseContext(),ApplicationActivity.class));
        finish();
    }

    /**
     * Fires when cancel is pressed
     * @param view The view
     */
    public void onCancelPressed(View view) {
        startActivity(new Intent(getBaseContext(),ApplicationActivity.class));
        finish();
    }
}
