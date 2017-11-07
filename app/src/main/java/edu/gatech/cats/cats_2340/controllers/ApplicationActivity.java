package edu.gatech.cats.cats_2340.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import edu.gatech.cats.cats_2340.R;
import edu.gatech.cats.cats_2340.model.Model;

/**
 * Class that is what the application looks like right when you open it
 */
public class ApplicationActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_application);
    }

    /**
     * This is hooked up to the log out button. When pressed, it switches the view to the opening
     * activity. It also logs the user out in the model. @Blake what does finish do? Just trying to
     * figure out android
     * @param view The view I'm not sure
     */
    public void onLogoutPressed(View view) {
        startActivity(new Intent(getBaseContext(),OpeningActivity.class));
        Model.getInstance().logout();
        finish();
    }

    /**
     * What fires when you click that you want a rat list
     * @param view The view
     */
    public void onListPressed(View view) {
        startActivity(new Intent(getBaseContext(),RatSightingList.class));
        finish();
    }

    /**
     * What fires when you click on the report button
     * @param view The view
     */
    public void onReportPressed(View view) {
        startActivity(new Intent(getBaseContext(), InputRatSighting.class));
        finish();
    }

    /**
     * What fires when you want the map
     * @param view The view
     */
    public void onMapPressed(View view) {
        startActivity(new Intent(getBaseContext(), MapsActivity.class));
    }

    /**
     * What fires when you want the graph view
     * @param view The view
     */
    public void onGraphPressed(View view) {
        startActivity(new Intent(getBaseContext(), GraphTimeRange.class));
    }

    /**
     * What fires when you want to search the map
     * @param view The view
     */
    public void onSearchPressed(View view) {
        startActivity(new Intent(getBaseContext(), SearchActivity.class));
    }
}
