package edu.gatech.cats.cats_2340.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import edu.gatech.cats.cats_2340.R;
import edu.gatech.cats.cats_2340.model.Model;

//Placeholder for rest of application
public class ApplicationActivity extends AppCompatActivity {

    /**
     * Overriden on-create sets the view to the application view
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);
    }

    /**
     * This is hooked up to the log out button. When pressed, it switches the view to the opening
     * activity. It also logs the user out in the model. @Blake what does finish do? Just trying to
     * figure out android
     * @param view
     */
    public void onLogoutPressed(View view) {
        startActivity(new Intent(getBaseContext(),OpeningActivity.class));
        Model.getInstance().logout();
        finish();
    }

    public void onListPressed(View view) {
        startActivity(new Intent(getBaseContext(),RatSightingScreen.class));
        finish();
    }
}
